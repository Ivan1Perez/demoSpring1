package es.ieslavereda.demospring.repository;

import es.ieslavereda.demospring.repository.model.MyDataSource;
import es.ieslavereda.demospring.repository.model.Usuario;
import lombok.ToString;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioRepositoryDB implements IUsuarioRepository{

    public List<Usuario> getUsuarios(){

        List<Usuario> usuarioList = new ArrayList<>();

        String sql = "{call obtener_usuarios() }";

        try(
                Connection c = MyDataSource.getMySQLDataSource().getConnection();
                CallableStatement cs = c.prepareCall(sql);
                ResultSet rs = cs.executeQuery();
        ){

            while (rs.next()) {
                usuarioList.add(
                        Usuario.builder()
                        .idUsuario(rs.getInt("idUsuario"))
                        .nombre(rs.getString("nombre"))
                        .apellidos(rs.getString("apellidos"))
                        .idOficio(rs.getInt("Oficio_idOficio"))
                        .build());
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return usuarioList;
    }

    @Override
    public Usuario getUsuario(int id) {
        Usuario u = null;
        String sql = "{call obtener_usuario(?)}";

        try(Connection c = MyDataSource.getMySQLDataSource().getConnection();
            CallableStatement cs = c.prepareCall(sql)){

            int pos = 0;

            cs.setInt(++pos,id);

            ResultSet rs = cs.executeQuery();

            if (rs.next()) {
                u = new Usuario(rs.getInt("idUsuario"), rs.getString("nombre"),
                        rs.getString("apellidos"), rs.getInt("Oficio_idOficio"));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al ejecutar la sentencia: " + sql, e);
        }

        return u;
    }

    @Override
    public Usuario deleteUser(int id) {
        Usuario usuario = null;
        String sqlDeleteUser = "{call eliminar_usuario(?)}";
        String sqlObtenerUsuario = "{call obtener_usuario(?)}";

        try(Connection c = MyDataSource.getMySQLDataSource().getConnection();
            CallableStatement cs = c.prepareCall(sqlObtenerUsuario)){

            int pos = 0;

            cs.setInt(++pos,id);

            ResultSet rs = cs.executeQuery();

            if (rs.next()) {
                usuario = new Usuario(rs.getInt("idUsuario"), rs.getString("nombre"),
                        rs.getString("apellidos"), rs.getInt("Oficio_idOficio"));


                cs.executeUpdate(sqlDeleteUser);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al ejecutar la sentencia: " + sqlObtenerUsuario, e);
        }

        return usuario;
    }

    @Override
    public Usuario addUser(Usuario usuario) {

        String sql = "{call crear_usuario(?,?,?,?)}";

        try(Connection c = MyDataSource.getMySQLDataSource().getConnection();
            CallableStatement cs = c.prepareCall(sql)){

            int pos = 0;

            cs.setInt(++pos,usuario.getIdUsuario());
            cs.setString(++pos,usuario.getNombre());
            cs.setString(++pos,usuario.getApellidos());
            cs.setInt(++pos,usuario.getIdOficio());

            cs.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al ejecutar la sentencia: " + sql, e);
        }
        return usuario;
    }

    @Override
    public Usuario updateUser(Usuario usuario) {
        Usuario u = null;
        String sql = "{call actualizar_usuario(?,?,?,?)}";

        try(Connection c = MyDataSource.getMySQLDataSource().getConnection();
            CallableStatement cs = c.prepareCall(sql)){

            int pos = 0;

            cs.setInt(++pos,usuario.getIdUsuario());
            cs.setString(++pos,usuario.getNombre());
            cs.setString(++pos,usuario.getApellidos());
            cs.setInt(++pos,usuario.getIdOficio());

            if(cs.executeUpdate()!=1){
                throw new RuntimeException("Error al crear el usuario");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al ejecutar la sentencia: " + sql, e);
        }

        return u;
    }
}
