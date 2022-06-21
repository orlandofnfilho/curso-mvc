package com.gft.gerenciadordeeventos.service;

import com.gft.gerenciadordeeventos.model.Permissao;
import com.gft.gerenciadordeeventos.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissaoService {

    @Autowired
    private PermissaoRepository permissaoRepository;

    public List<Permissao> listaPermissao(){
        return permissaoRepository.findAll();
    }
}
