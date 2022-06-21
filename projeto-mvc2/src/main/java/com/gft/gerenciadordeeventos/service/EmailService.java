package com.gft.gerenciadordeeventos.service;

import com.gft.gerenciadordeeventos.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void enviarEmail(Pedido pedido){
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("atendimento@eticket.com.br");
            message.setTo(pedido.getUsuario().getEmail());
            message.setSubject("Compra de Ingresso no portal E-Ticket");
            message.setText("Nº da compra: "+pedido.getId()+"\n Cliente: "+pedido.getUsuario().getNome()+"\n Evento: "+pedido.getEvento().getNome()
            +"\n Data do evento: "+pedido.getEvento().getDataEvento()+"\n Quantidade Comprada: "+pedido.getQuantidade()+"\n Valor Total: "+pedido.getValorTotalPedido());
            emailSender.send(message);
        }catch (MailException e){
            System.out.println("Erro no envio do e-mail: "+e.getMessage());
        }
    }



}
