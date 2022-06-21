package com.gft.gerenciadordeeventos.service;

import com.gft.gerenciadordeeventos.model.CasaDeShow;
import com.gft.gerenciadordeeventos.repository.CasaDeShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CasaDeShowService {

    @Autowired
    private CasaDeShowRepository casaDeShowRepository;

    public CasaDeShow adicionar(CasaDeShow casaDeShow){
        var casaDeShowExistente = casaDeShowRepository.findByNomeAndEndereco(casaDeShow.getNome(), casaDeShow.getEndereco());
        if (casaDeShowExistente.isPresent()){
            throw new RuntimeException("Casa de show já cadastrada.");
        }
        return casaDeShowRepository.save(casaDeShow);
    }

    public List<CasaDeShow> listarCasaDeShow(String nome){
        if(nome != null && nome != ""){
            return listarCasaDeShowPorNome(nome);
        }
        else{
            return listarTodasCasasDeShow();
        }
    }
    private List<CasaDeShow> listarCasaDeShowPorNome(String nome){
        return casaDeShowRepository.findByNomeContainsIgnoreCase(nome);
    }

    public CasaDeShow buscarPorId(Long id){
        CasaDeShow casaDeShow = casaDeShowRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Casa de show não localzada."));
        return casaDeShow;
    }

    public List<CasaDeShow> listarTodasCasasDeShow(){
        return casaDeShowRepository.findAll();
    }

    public void deletar(Long id){
        Optional<CasaDeShow> casaDeShow = casaDeShowRepository.findById(id);
        if (casaDeShow.isPresent()){
            casaDeShowRepository.delete(casaDeShow.get());
        }
        new Exception("Ocorreu um problema na exclusão");
    }
}
