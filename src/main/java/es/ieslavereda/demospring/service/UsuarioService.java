package es.ieslavereda.demospring.service;

import es.ieslavereda.demospring.repository.IUsuarioRepository;
import es.ieslavereda.demospring.repository.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private IUsuarioRepository repository;

    public List<Usuario> getUsers(){
        return repository.getUsuarios();
    }

    public Usuario getUserById(int id){
        return  repository.getUsuario(id);
    }

    public Usuario deleteUserById(int id) {
        return repository.deleteUser(id);
    }

    public Usuario addUser(Usuario usuario) {
        return repository.addUser(usuario);
    }

    public Usuario updateUser(Usuario usuario) {
        return repository.updateUser(usuario);
    }
}
