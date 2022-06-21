package com.gft.gerenciadordeeventos.controller;

import com.gft.gerenciadordeeventos.model.GeneroMusical;
import com.gft.gerenciadordeeventos.service.GeneroMusicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("generoMusical")
public class GeneroMusicalController {

    @Autowired
    GeneroMusicalService generoMusicalService;

    @GetMapping("novo")
    public ModelAndView novoGeneroMuscial(GeneroMusical generoMusical){
        ModelAndView mv = new ModelAndView("genero-musical/form-genero-musical");
        return mv;
    }

    @PostMapping("novo")
    public ModelAndView salvarGeneroMusical(@Valid GeneroMusical generoMusical, BindingResult bindingResult, RedirectAttributes attributes){

        if (bindingResult.hasErrors()){
            return novoGeneroMuscial(generoMusical);
        }
        try {
            GeneroMusical generoMusicalSalvo = generoMusicalService.adicionar(generoMusical);
        }catch (RuntimeException e){
            bindingResult.rejectValue("nome", e.getMessage(), e.getMessage());
            return novoGeneroMuscial(generoMusical);
        }

        attributes.addFlashAttribute("message", "Genero Musical Salvo com sucesso.");

        return new ModelAndView("redirect:/generoMusical/novo");
    }

    @GetMapping
    public ModelAndView listarGeneros(String nome){
        ModelAndView mv = new ModelAndView("genero-musical/listar-genero-musical");
        mv.addObject("lista", generoMusicalService.listarGeneroMusical(nome));
        return mv;
    }

    @GetMapping("/editar")
    public ModelAndView editarGeneroMusical(@RequestParam Long id){
        ModelAndView mv = new ModelAndView("genero-musical/form-genero-musical");
        GeneroMusical generoMusical;
        try {
            generoMusical = generoMusicalService.buscarPorId(id);
        }catch (Exception e){
           generoMusical = new GeneroMusical();
           mv.addObject("message", generoMusical);
        }
        mv.addObject("generoMusical", generoMusical);
        return mv;
    }

    @GetMapping("/excluir")
    public ModelAndView excluirGeneroMusical(@RequestParam Long id, RedirectAttributes redirectAttributes){
        ModelAndView mv = new ModelAndView("redirect:/generoMusical");
        try {
            generoMusicalService.deletar(id);
            redirectAttributes.addFlashAttribute("message", "Registro excluído com sucesso");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("messageError", "Erro ao excluir o registro.");
        }
        return mv;
    }
}
