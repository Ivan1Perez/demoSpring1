package es.ieslavereda.demospring.repository;

import es.ieslavereda.demospring.repository.model.Usuario;

import java.util.List;


public interface IUsuarioRepository {
    public List<Usuario> getUsuarios();
    public Usuario getUsuario(int id);
    public Usuario deleteUser(int id);
    public Usuario addUser(Usuario usuario);
    public Usuario updateUser(Usuario usuario);
}
