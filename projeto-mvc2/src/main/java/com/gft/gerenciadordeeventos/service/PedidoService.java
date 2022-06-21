package com.gft.gerenciadordeeventos.service;

import com.gft.gerenciadordeeventos.model.Evento;
import com.gft.gerenciadordeeventos.model.Pedido;
import com.gft.gerenciadordeeventos.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EventoService eventoService;

    @Autowired
    EmailService emailService;


    public Pedido adicionar(Pedido pedido, Evento evento){
        String nomeUsuario = SecurityContextHolder.getContext().getAuthentication().getName();
        pedido.setUsuario(usuarioService.buscarPorNome(nomeUsuario));
        pedido.setEvento(evento);
        pedido.setDataPedido(LocalDate.now());
        pedido.calcularTotalPedido(pedido.getQuantidade(), pedido.getEvento().getPrecoIngresso());
        evento.atualizaCapacidade(pedido.getQuantidade());
        Pedido pedidoSalvo = pedidoRepository.save(pedido);
        emailService.enviarEmail(pedidoSalvo);

        return  pedidoSalvo;
    }

    public List<Pedido> historicoPedidos(String nomeUsuario){
        var usuario = usuarioService.buscarPorNome(nomeUsuario);
        return pedidoRepository.findAllPedidoByUser(usuario.getId());
    }

    public boolean validaPedido(Pedido pedido, Evento evento){
        return pedido.getQuantidade() != null && pedido.getQuantidade() > evento.getCapacidade();
    }
}
