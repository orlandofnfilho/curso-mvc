package com.gft.gerenciadordeeventos.service;

import com.gft.gerenciadordeeventos.model.Evento;
import com.gft.gerenciadordeeventos.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    @Autowired
    EventoRepository eventoRepository;

    public Evento adicionar(Evento evento){
        evento.setPrecoIngresso(new BigDecimal(String.valueOf(evento.getPrecoIngresso())));
        var eventoExistente = eventoRepository.findByNomeAndDataEventoAndCasaDeShow(evento.getNome(), evento.getDataEvento(), evento.getCasaDeShow());
        if (eventoExistente.isPresent()){
            throw new RuntimeException("Evento já cadastrado nessa data e casa de show.");
        }

        return eventoRepository.save(evento);
    }

    public List<Evento> listarEventos(String nome){
        if(nome != null && nome != ""){
            return listarEventoPorNome(nome);
        }
        else{
            return listarTodosEventos();
        }
    }
    private List<Evento> listarEventoPorNome(String nome){
        Optional<List<Evento>> lista = eventoRepository.findByNomeContains(nome);
        return lista.get();
    }

    public List<Evento> listarTodosEventos(){
        return eventoRepository.findAll();
    }

    public Evento buscarPorId(Long id){
       Evento evento = eventoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Evento não localzado."));
        return evento;
    }

    public void deletar(Long id){
        Optional<Evento> evento = eventoRepository.findById(id);
        if (evento.isPresent()){
            eventoRepository.delete(evento.get());
        }
        new Exception("Ocorreu um problema na exclusão");
    }
}
