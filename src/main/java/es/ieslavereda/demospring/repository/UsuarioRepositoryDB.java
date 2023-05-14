package es.ieslavereda.demospring.repository;

import es.ieslavereda.demospring.repository.model.MyDataSource;
import es.ieslavereda.demospring.repository.model.Usuario;
import lombok.ToString;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioRepositoryDB implements IUsuarioRepository{

    public List<Usuario> getUsuarios(){

        List<Usuario> usuarioList = new ArrayList<>();

        String sql = "SELECT * FROM USUARIO";

        try(
            Connection c = MyDataSource.getMySQLDataSource().getConnection();
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(sql)
        ){

            while (rs.next()) {
                usuarioList.add(
                        Usuario.builder()
                        .idUsuario(rs.getInt("IDUSUARIO"))
                        .nombre(rs.getString("NOMBRE"))
                        .apellidos(rs.getString("APELLIDOS"))
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
        String sql = "SELECT * FROM USUARIO WHERE IDUSUARIO = " + id;

        try(Connection c = MyDataSource.getMySQLDataSource().getConnection();
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){

            if (rs.next()) {
                u = new Usuario(rs.getInt("IDUSUARIO"), rs.getString("NOMBRE"), rs.getString("APELLIDOS"));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al ejecutar la sentencia: " + sql, e);
        }

        return u;
    }

    @Override
    public Usuario deleteUser(int id) {
        Usuario usuario = null;
        String sqlDeleteUser = "DELETE FROM USUARIO WHERE IDUSUARIO = " + id;
        String obtenerUsuario = "SELECT * FROM USUARIO WHERE IDUSUARIO = " + id;
        int rowsAffected = 0;

        try(Connection c = MyDataSource.getMySQLDataSource().getConnection();
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(obtenerUsuario)){

            if (rs.next()) {
                usuario = new Usuario(rs.getInt("IDUSUARIO"), rs.getString("NOMBRE"), rs.getString("APELLIDOS"));
                rowsAffected = stmt.executeUpdate(sqlDeleteUser);

                if(rowsAffected!=1){
                    throw new RuntimeException("Error al eliminar el usuario");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al ejecutar la sentencia: " + obtenerUsuario, e);
        }

        return usuario;
    }

    @Override
    public Usuario addUser(Usuario usuario) {

        String sql = "INSERT INTO USUARIO " +
                "VALUES(" +
                usuario.getIdUsuario() + ",'" +
                usuario.getNombre() + "','" +
                usuario.getApellidos() +
                "')";

        int rowsAffected = 0;

        try(Connection c = MyDataSource.getMySQLDataSource().getConnection();
            Statement stmt = c.createStatement();
        ){

            rowsAffected = stmt.executeUpdate(sql);
            if (rowsAffected == 0) {
                throw new RuntimeException("No se pudo insertar el usuario");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al ejecutar la sentencia: " + sql, e);
        }
        return usuario;
    }

    @Override
    public Usuario updateUser(Usuario usuario) {
        Usuario u = null;
        String sqlSelectUsuario = "SELECT * FROM USUARIO WHERE IDUSUARIO = " + usuario.getIdUsuario();
        String sqlUpdateUsuario = "UPDATE USUARIO " +
                "SET " +
                "NOMBRE='" + usuario.getNombre() + "'," +
                "APELLIDOS='" + usuario.getApellidos() + "'" +
                "WHERE IDUSUARIO=" + usuario.getIdUsuario();

        int rowsAffected = 0;

        try(Connection c = MyDataSource.getMySQLDataSource().getConnection();
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(sqlSelectUsuario)){

            if (rs.next()) {
                rowsAffected =  stmt.executeUpdate(sqlUpdateUsuario);

                if(rowsAffected!=1){
                    throw new RuntimeException("No se ha podido actualizar el usuario.");
                }else{
                    u = new Usuario(usuario.getIdUsuario(),usuario.getNombre(),usuario.getApellidos());
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al ejecutar la sentencia: " + sqlSelectUsuario, e);
        }

        return u;
    }
}
