package es.ieslavereda.demospring.controller;

import es.ieslavereda.demospring.repository.model.Oficio;
import es.ieslavereda.demospring.repository.model.Usuario;
import es.ieslavereda.demospring.service.OficioService;
import es.ieslavereda.demospring.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class OficioController {

    private final Logger LOGGER = Logger.getLogger(getClass().getCanonicalName());

    @Autowired
    private OficioService service;

    @GetMapping("/oficios")
    public ResponseEntity<?> getOficios() {
        LOGGER.log(Level.INFO, "Getting jobs");

        try{
            return new ResponseEntity<>(service.getOficios(id), HttpStatus.OK);
        }catch (SQLException sqlException){
            Map<String, Object> response = new HashMap<>();
            response.put("code",sqlException.getErrorCode());
            response.put("message",sqlException.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/oficios/{id}")
    public ResponseEntity<?> getOficios(@PathVariable("id") int id) {
        LOGGER.log(Level.INFO, "Getting jobs");

        try{
            return new ResponseEntity<>(service.getOficios(id), HttpStatus.OK);
        }catch (SQLException sqlException){
            Map<String, Object> response = new HashMap<>();
            response.put("code",sqlException.getErrorCode());
            response.put("message",sqlException.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/oficios/{id}/image")
    public ResponseEntity<?> getImage(@PathVariable("id") int id){
        LOGGER.log(Level.INFO, "Getting image " + id);

        byte[] bytes = service.getImageById(id);

        if (bytes != null)
            return new ResponseEntity<>(bytes, HttpStatus.OK);
        else
            return new ResponseEntity<>("La imagen no existe.", HttpStatus.NOT_FOUND);
    }

}
