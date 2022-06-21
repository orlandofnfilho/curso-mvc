package com.gft.gerenciadordeeventos.service;

import com.gft.gerenciadordeeventos.model.GeneroMusical;
import com.gft.gerenciadordeeventos.repository.GeneroMusicalRepostiroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GeneroMusicalService {

    @Autowired
    GeneroMusicalRepostiroy generoMusicalRepostiroy;

    public GeneroMusical adicionar(GeneroMusical generoMusical){
        var generoMusicalExistente = generoMusicalRepostiroy.findByNome(generoMusical.getNome());
        if (generoMusicalExistente.isPresent()){
            throw new RuntimeException("Genero musical já cadastrado.");
        }
        return generoMusicalRepostiroy.save(generoMusical);
    }
    public List<GeneroMusical> listarGeneroMusical(String nome){
        if(nome != null && nome != ""){
            return listarGeneroMusicalPorNome(nome);
        }
        else{
            return listarTodosGenerosMusicais();
        }
    }
    private List<GeneroMusical> listarGeneroMusicalPorNome(String nome){
        Optional<List<GeneroMusical>> lista = generoMusicalRepostiroy.findByNomeContains(nome);
        return lista.get();
    }
    public List<GeneroMusical> listarTodosGenerosMusicais(){
        return generoMusicalRepostiroy.findAll();
    }

    public GeneroMusical buscarPorId(Long id){
        return generoMusicalRepostiroy.findById(id).get();
    }

    public void deletar(Long id){
        Optional<GeneroMusical> generoMusical = generoMusicalRepostiroy.findById(id);
        if (generoMusical.isPresent()){
            generoMusicalRepostiroy.delete(generoMusical.get());
        }
        new Exception("Ocorreu um problema na exclusão");
    }
}
