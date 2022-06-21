package com.gft.gerenciadordeeventos.config;

import com.gft.gerenciadordeeventos.model.*;
import com.gft.gerenciadordeeventos.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
public class CarregaDadosIniciais {

    @Autowired
    GeneroMusicalRepostiroy generoMusicalRepostiroy;

    @Autowired
    CasaDeShowRepository casaDeShowRepository;

    @Autowired
    EventoRepository eventoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PermissaoRepository permissaoRepository;

    @PostConstruct
    public void CriaEvento(){

        Permissao permissaoAdmin = new Permissao(null, "ROLE_ADMIN");
        Permissao permissaoUser = new Permissao(null, "ROLE_USER");
        permissaoRepository.saveAll(Arrays.asList(permissaoAdmin, permissaoUser));

        Usuario usuario = new Usuario();
        usuario.setNome("admin");
        usuario.setEmail("admin@eticket.com.br");
        usuario.setSenha(new BCryptPasswordEncoder().encode("123"));
        usuario.setPermissoes(Arrays.asList(permissaoAdmin));
        usuarioRepository.save(usuario);

        CasaDeShow casaDeShow = new CasaDeShow(null, "Ibiza", "Barcelona");
        casaDeShowRepository.save(casaDeShow);

        GeneroMusical generoMusical = new GeneroMusical(null,"Eletronico", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQBJV_UXUvCDTWTDfhq6-N-vNWsVuuWpFwc8g&usqp=CAU");
        generoMusicalRepostiroy.save(generoMusical);

        Evento evento = new Evento(
                null, "Show Alok", 25000, new BigDecimal("90"), LocalDate.of(2022, 8, 30),
                "02:00", casaDeShow, generoMusical);
        eventoRepository.save(evento);
    }
}

