package com.gft.gerenciadordeeventos.controller;

import com.gft.gerenciadordeeventos.model.GeneroMusical;
import com.gft.gerenciadordeeventos.model.Usuario;
import com.gft.gerenciadordeeventos.service.PermissaoService;
import com.gft.gerenciadordeeventos.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PermissaoService permissaoService;

    @GetMapping("novo")
    public ModelAndView novoUsuario(Usuario usuario){
        ModelAndView mv = new ModelAndView("usuario/form-usuario");
        mv.addObject("listaPermissoes", permissaoService.listaPermissao());
        return mv;
    }

    @PostMapping("novo")
    public ModelAndView salvarUsuario(@Valid Usuario usuario, BindingResult bindingResult, RedirectAttributes attributes){

        if (bindingResult.hasErrors()){
            return novoUsuario(usuario);
        }
        try {
            usuarioService.adicionar(usuario);
        }catch (RuntimeException e){
            bindingResult.rejectValue("nome", e.getMessage(), e.getMessage());

            return novoUsuario(usuario);
        }

        attributes.addFlashAttribute("message", "Usuario Salvo com sucesso.");

        return new ModelAndView("redirect:/usuarios/novo");
    }

    @GetMapping
    public ModelAndView listarUsuarios(String nome){
        ModelAndView mv = new ModelAndView("usuario/listar-usuario");
        mv.addObject("lista", usuarioService.listarUsuarios(nome));
        return mv;
    }

    @GetMapping("/editar")
    public ModelAndView editarUsuario(@RequestParam Long id){
        ModelAndView mv = new ModelAndView("usuario/form-usuario");
        Usuario usuario;
        try {
            usuario = usuarioService.buscarPorId(id);
        }catch (Exception e){
            usuario = new Usuario();
            mv.addObject("message", usuario);
        }
        mv.addObject("usuario", usuario);
        mv.addObject("listaPermissoes", permissaoService.listaPermissao());
        return mv;
    }

    @GetMapping("/excluir")
    public ModelAndView deletarUsuario(@RequestParam Long id, RedirectAttributes redirectAttributes){
        ModelAndView mv = new ModelAndView("redirect:/usuarios");
        try {
            usuarioService.deletar(id);
            redirectAttributes.addFlashAttribute("message", "Registro excluído com sucesso");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("messageError", "Usuário não pode ser excluido");
        }
        return mv;
    }
}
