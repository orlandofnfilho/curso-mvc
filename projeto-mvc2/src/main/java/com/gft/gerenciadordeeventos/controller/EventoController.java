package com.gft.gerenciadordeeventos.controller;

import com.gft.gerenciadordeeventos.model.Evento;
import com.gft.gerenciadordeeventos.service.CasaDeShowService;
import com.gft.gerenciadordeeventos.service.EventoService;
import com.gft.gerenciadordeeventos.service.GeneroMusicalService;
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
@RequestMapping("eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @Autowired
    private CasaDeShowService casaDeShowService;

    @Autowired
    private GeneroMusicalService generoMusicalService;

    @GetMapping("novo")
    public ModelAndView novoEvento(Evento evento){
        ModelAndView mv = new ModelAndView("evento/form-evento");
        mv.addObject("listageneromusical", generoMusicalService.listarTodosGenerosMusicais());
        mv.addObject("listaCasasDeShow", casaDeShowService.listarTodasCasasDeShow());
        return mv;
    }

    @PostMapping("novo")
    public ModelAndView salvarEvento(@Valid Evento evento, BindingResult bindingResult, RedirectAttributes attributes){

        if (bindingResult.hasErrors()){
            return novoEvento(evento);
        }
        try {
            eventoService.adicionar(evento);
        }catch (RuntimeException e){
            bindingResult.rejectValue("nome", e.getMessage(), e.getMessage());
            bindingResult.rejectValue("dataEvento", null, null);

            return novoEvento(evento);
        }

        attributes.addFlashAttribute("message", "Evento Salvo com sucesso.");

        return new ModelAndView("redirect:/eventos/novo");
    }

    @GetMapping
    public ModelAndView listarEventos(String nome){
        ModelAndView mv = new ModelAndView("evento/lista-eventos");
        mv.addObject("lista", eventoService.listarEventos(nome));

        return mv;
    }

    @GetMapping("/editar")
    public ModelAndView editarEvento(@RequestParam Long id){
        ModelAndView mv = new ModelAndView("evento/form-evento");
        Evento evento;
        try {
            evento = eventoService.buscarPorId(id);
        }catch (Exception e){
            evento = new Evento();
            mv.addObject("message", evento);
        }
        mv.addObject("evento", evento);
        mv.addObject("listageneromusical", generoMusicalService.listarTodosGenerosMusicais());
        mv.addObject("listaCasasDeShow", casaDeShowService.listarTodasCasasDeShow());
        return mv;
    }

    @GetMapping("/excluir")
    public ModelAndView excluirEvento(@RequestParam Long id, RedirectAttributes redirectAttributes){
        ModelAndView mv = new ModelAndView("redirect:/eventos");
        try {
            eventoService.deletar(id);
            redirectAttributes.addFlashAttribute("message", "Registro excluído com sucesso");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("messageError", "Erro ao excluir o registro.");
        }
        return mv;
    }
}
