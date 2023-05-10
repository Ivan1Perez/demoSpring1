package es.ieslavereda.demospring.repository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class UsuarioRepository {

    private List<Usuario> usuarioList;

    public UsuarioRepository() {
        usuarioList = new ArrayList<>();
        usuarioList.add(new Usuario(1,"Joaquin","Alonso"));
        usuarioList.add(new Usuario(2,"Pedro","Soler"));
        usuarioList.add(new Usuario(3,"Luis","Martinez"));
        usuarioList.add(new Usuario(4,"Tomas","Lopez"));
    }

    public List<Usuario> getUsuarios(){
        return usuarioList;
    }
}
