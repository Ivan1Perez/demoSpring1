package es.ieslavereda.demospring.repository;

import es.ieslavereda.demospring.repository.model.MyDataSource;
import es.ieslavereda.demospring.repository.model.Usuario;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioRepositoryDB implements IUsuarioRepository{
    public List<Usuario> getUsuarios() throws SQLException {

        List<Usuario> usuarioList = new ArrayList<>();

        String sql = "SELECT * FROM USERS";

        try(Connection c = MyDataSource.getMySQLDataSource().getConnection();
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){

            while (rs.next())
                usuarioList.add(Usuario.builder()
                        .idUsuario(rs.getInt("IDUSUARIO"))
                        .nombre(rs.getString("NOMBRE"))
                        .apellidos(rs.getString("APELLIDOS"))
                        .build());

        }
        return usuarioList;
    }

    @Override
    public Usuario getUsuario(int id) {
        return null;
    }

    @Override
    public Usuario deleteUser(int id) {
        return null;
    }

    @Override
    public Usuario addUser(Usuario usuario) {
        return null;
    }

    @Override
    public Usuario updateUser(Usuario usuario) {
        return null;
    }
}
