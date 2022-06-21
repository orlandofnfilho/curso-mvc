package com.gft.gerenciadordeeventos.controller;

import com.gft.gerenciadordeeventos.model.CasaDeShow;
import com.gft.gerenciadordeeventos.service.CasaDeShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("casaDeShow")
public class CasaDeShowController {

    @Autowired
    private CasaDeShowService casaDeShowService;

    @GetMapping("novo")
    public ModelAndView novaCasaDeShow(CasaDeShow casaDeShow){
        ModelAndView mv = new ModelAndView("casa-de-show/form-casa-de-show");
        return mv;
    }

    @PostMapping("novo")
    public ModelAndView salvarCasaDeShow(@Valid CasaDeShow casaDeShow, BindingResult bindingResult, RedirectAttributes attributes){

        if (bindingResult.hasErrors()){
            return novaCasaDeShow(casaDeShow);
        }
        try {
            casaDeShowService.adicionar(casaDeShow);
        }catch (RuntimeException e){
            bindingResult.rejectValue("nome", e.getMessage(), e.getMessage());
            return novaCasaDeShow(casaDeShow);
        }

        attributes.addFlashAttribute("message", "Casa de show Salva com sucesso.");

        return new ModelAndView("redirect:/casaDeShow/novo");
    }

    @GetMapping
    public ModelAndView listarCasaDeShow(String nome){
        ModelAndView mv = new ModelAndView("casa-de-show/listar-casa-de-show");
        mv.addObject("lista", casaDeShowService.listarCasaDeShow(nome));
        return mv;
    }

    @GetMapping("/editar")
    public ModelAndView editarCasaDeShow(@RequestParam Long id){
        ModelAndView mv = new ModelAndView("casa-de-show/form-casa-de-show");
        CasaDeShow casaDeShow;
        try {
            casaDeShow = casaDeShowService.buscarPorId(id);
        }catch (Exception e){
            casaDeShow = new CasaDeShow();
            mv.addObject("message", casaDeShow);
        }
        mv.addObject("casaDeShow", casaDeShow);
        return mv;
    }

    @GetMapping("/excluir")
    public ModelAndView excluirCasaDeShow(@RequestParam Long id, RedirectAttributes redirectAttributes){
        ModelAndView mv = new ModelAndView("redirect:/casaDeShow");
        try {
            casaDeShowService.deletar(id);
            redirectAttributes.addFlashAttribute("message", "Registro excluído com sucesso");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("messageError", "Erro ao excluir o registro.");
        }
        return mv;
    }

}
