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

    private Usuario executeSqlAndGetUser(String sql) {
        Usuario usuario;

        try(Connection c = MyDataSource.getMySQLDataSource().getConnection();
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){

            if (rs.next()) {
                usuario = new Usuario(rs.getInt("IDUSUARIO"), rs.getString("NOMBRE"), rs.getString("APELLIDOS"));
            }

            if(usuario!=null){

            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al ejecutar la sentencia: " + sql, e);
        }
        return usuario;
    }


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
                        .apellidos(rs.getString("APELLIDO"))
                        .build());
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return usuarioList;
    }

    @Override
    public Usuario getUsuario(int id) {
        String sql = "SELECT * FROM USUARIO WHERE IDUSUARIO = " + id;
        return executeSqlAndGetUser(sql);
    }

    @Override
    public Usuario deleteUser(int id) {
        Usuario usuario;
        String sql = "DELETE FROM USUARIO WHERE IDUSUARIO = " + id;
        String obtenerUsuario = "SELECT * FROM USUARIO WHERE IDUSUARIO = " + id;

        try(Connection c = MyDataSource.getMySQLDataSource().getConnection();
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){

            if (rs.next()) {
                usuario = new Usuario(rs.getInt("IDUSUARIO"), rs.getString("NOMBRE"), rs.getString("APELLIDOS"));
            }

            if(usuario!=null){

            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al ejecutar la sentencia: " + sql, e);
        }

        return usuario;
    }

    @Override
    public Usuario addUser(Usuario usuario) {

        String sql = "INSERT INTO USUARIO " +
                "VALUES(" +
                usuario.getIdUsuario() + "," +
                usuario.getNombre() + "," +
                usuario.getApellidos() +
                ")";

        try(Connection c = MyDataSource.getMySQLDataSource().getConnection();
            Statement stmt = c.createStatement()
        ){

            int rowsAffected = stmt.executeUpdate(sql);
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
        return null;
    }
}
