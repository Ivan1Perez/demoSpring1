package es.ieslavereda.demospring.service;

import es.ieslavereda.demospring.repository.Usuario;
import es.ieslavereda.demospring.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;
    
    public List<Usuario> getUsers(){
        return repository.getUsuarios();
    }
}
