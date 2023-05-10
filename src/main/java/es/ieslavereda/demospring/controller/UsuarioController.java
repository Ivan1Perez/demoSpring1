package es.ieslavereda.demospring.controller;

import es.ieslavereda.demospring.repository.Usuario;
import es.ieslavereda.demospring.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping("/api/usuarios")
    public List<Usuario> getUsuarios(){
        return service.getUsers();
    }
}
