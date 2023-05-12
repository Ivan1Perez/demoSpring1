package es.ieslavereda.demospring.controller;

import es.ieslavereda.demospring.repository.model.Usuario;
import es.ieslavereda.demospring.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping("/usuarios")
    public ResponseEntity<?> getUsuarios() throws SQLException {

        try{
            return new ResponseEntity<>(service.getUsers(),HttpStatus.OK);
        }catch (SQLException sqlException){
            Map<String, Object> response = new HashMap<>();
            response.put("code",sqlException.getErrorCode());
            response.put("message",sqlException.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
