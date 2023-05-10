package es.ieslavereda.demospring.controller;

import es.ieslavereda.demospring.repository.model.Usuario;
import es.ieslavereda.demospring.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping("/usuarios")
    public List<Usuario> getUsuarios() {
        return service.getUsers();
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id) {
        Usuario u = service.getUserById(id);

        if (u != null)
            return new ResponseEntity<>(u, HttpStatus.OK);
        else
            return new ResponseEntity<>("El usuario no existe.", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") int id) {

        Usuario usuario = service.deleteUserById(id);

        if (usuario != null)
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        else
            return new ResponseEntity<>("El usuario no existe.", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/usuarios")
    public ResponseEntity<?> addUser(@RequestBody Usuario usuario) {
        Usuario u = service.addUser(usuario);

        if (u == null)
            return new ResponseEntity<>("No se ha podido añadir el usuario", HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(u, HttpStatus.OK);
    }

    @PutMapping("/usuarios")
    public ResponseEntity<?> updateUser(@RequestBody Usuario usuario) {
        Usuario u = service.updateUser(usuario);

        if (u == null)
            return new ResponseEntity<>("No se ha podido actualizar el usuario", HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(u, HttpStatus.OK);
    }

}
