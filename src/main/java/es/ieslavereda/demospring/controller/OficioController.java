package es.ieslavereda.demospring.controller;

import es.ieslavereda.demospring.repository.model.Oficio;
import es.ieslavereda.demospring.service.OficioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
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
    public ResponseEntity<?> getOficios()  {
        LOGGER.log(Level.INFO, "Getting jobs");

        try{
            return new ResponseEntity<>(service.getOficios(), HttpStatus.OK);
        }catch (SQLException sqlException){
            Map<String, Object> response = new HashMap<>();
            response.put("code",sqlException.getErrorCode());
            response.put("message",sqlException.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/oficio/{id}")
    public ResponseEntity<?> getOficio(@PathVariable("id") int id) throws SQLException {
        LOGGER.log(Level.INFO, "Getting jobs");

        Oficio o = service.getOficio(id);

        if (o != null)
            return new ResponseEntity<>(o, HttpStatus.OK);
        else
            return new ResponseEntity<>("El oficio no existe.", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/oficios/{id}/image")
    public ResponseEntity<?> getImage(@PathVariable("id") int id) throws SQLException {
        LOGGER.log(Level.INFO, "Getting image " + id);

        try {
            Map<String, Object> response = new HashMap<>();
            byte[] image = service.getImageById(id);
            String output = new String(image, StandardCharsets.ISO_8859_1);
            response.put("encoding","StandardCharsets.ISO_8859_1");
            response.put("image", output);
            return new ResponseEntity<>(response, HttpStatus.OK);
//            image = service.getImageById(id);
        } catch (SQLException e) {

            return new ResponseEntity<>("La imagen no existe.", HttpStatus.NOT_FOUND);

        }

//        if (image != null)
//            return new ResponseEntity<>(image, HttpStatus.OK);
//        else
//            return new ResponseEntity<>("La imagen no existe.", HttpStatus.NOT_FOUND);
    }

}
