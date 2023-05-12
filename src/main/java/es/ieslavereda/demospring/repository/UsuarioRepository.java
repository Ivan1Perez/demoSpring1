//package es.ieslavereda.demospring.repository;
//
//import es.ieslavereda.demospring.repository.model.MyDataSource;
//import es.ieslavereda.demospring.repository.model.Usuario;
//import org.springframework.stereotype.Repository;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
////@Repository
//public class UsuarioRepository implements IUsuarioRepository {
//
//    private List<Usuario> usuarioList;
//    public UsuarioRepository(){
//        usuarioList = new ArrayList<>();
//        usuarioList.add(new Usuario(1,"Joaquin","Alonso"));
//    }
//    public List<Usuario> getUsuarios(){
//        return usuarioList;
//    }
//
//    public Usuario getUsuario(int id){
//        Optional<Usuario> optional = usuarioList.stream()
//                .filter(u->u.getIdUsuario()==id)
//                .findFirst();
//
//        if(optional.isPresent())
//            return optional.get();
//         else
//            return null;
//    }
//    public Usuario deleteUser(int id) {
//        Usuario u = getUsuario(id);
//        if(u!=null)
//            usuarioList.remove(u);
//
//        return u;
//    }
//
//    public Usuario addUser(Usuario usuario) {
//        if(!usuarioList.contains(usuario)) {
//            usuarioList.add(usuario);
//            return usuario;
//        }
//        return null;
//    }
//
//    public Usuario updateUser(Usuario usuario) {
//        Usuario u = getUsuario(usuario.getIdUsuario());
//        if(u!=null){
//            u.setNombre(usuario.getNombre());
//            u.setApellidos(usuario.getApellidos());
//        }
//
//        return u;
//    }
//}
