package com.gft.gerenciadordeeventos.service;

import com.gft.gerenciadordeeventos.model.Permissao;
import com.gft.gerenciadordeeventos.model.Usuario;
import com.gft.gerenciadordeeventos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public Usuario buscarPorNome(String nome){
       return usuarioRepository.findByNome(nome)
                .orElseThrow(()-> new RuntimeException("Usuario não localizado."));
    }

    public Usuario adicionar(Usuario usuario){
        if (usuario.getId() == null){
            var usuarioExistente = usuarioRepository.findByNome(usuario.getNome());
            if(usuarioExistente.isPresent()){
                throw new RuntimeException("Usuário já cadastrado.");
            }
        }
        if(usuario.getPermissoes().isEmpty()){
            usuario.setPermissoes(Collections.singletonList(new Permissao(2L, "ROLE_USER")));
        }
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));

        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarUsuarios(String nome){
        if (nome != null && !nome.isBlank()){
            return usuarioRepository.findByNomeIgnoreCaseContains(nome);
        }
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorId(Long id){
        return usuarioRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Usuario não localizado."));
    }

    public void deletar(Long id){
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(()-> new RuntimeException("Usuário não localizado."));
        usuarioRepository.delete(usuario);
    }
}
