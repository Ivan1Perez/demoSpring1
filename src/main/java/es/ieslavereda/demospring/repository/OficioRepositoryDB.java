package es.ieslavereda.demospring.repository;

import es.ieslavereda.demospring.repository.model.MyDataSource;
import es.ieslavereda.demospring.repository.model.Oficio;
import es.ieslavereda.demospring.repository.model.Usuario;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OficioRepositoryDB implements IOficioRepository{

    public List<Oficio> getOficios(int id){

        List<Oficio> oficioList = new ArrayList<>();

        String sql = "{call obtener_oficios(?)}";

        try(
                Connection c = MyDataSource.getMySQLDataSource().getConnection();
                CallableStatement cs = c.prepareCall(sql);
        ){

            int pos = 0;

            cs.setInt(++pos, id);
            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                oficioList.add(
                        Oficio.builder()
                                .idOficio(rs.getInt("idOficio"))
                                .descripcion(rs.getString("descripcion"))
                                .imagen(rs.getBytes("image"))
                                .imageurl(rs.getString("imageurl"))
                                .build());
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        return oficioList;
    }

    @Override
    public byte[] getImageById(int id) {
//        byte[] imagen;
//        String sql = "{call obtener_usuario(?)}";
//
//        try(Connection c = MyDataSource.getMySQLDataSource().getConnection();
//            CallableStatement cs = c.prepareCall(sql)){
//
//            int pos = 0;
//
//            cs.setInt(++pos,id);
//
//            ResultSet rs = cs.executeQuery();
//
//            if (rs.next()) {
//                u = new Usuario(rs.getInt("idUsuario"), rs.getString("nombre"),
//                        rs.getString("apellidos"), rs.getInt("Oficio_idOficio"));
//            }
//
//        } catch (SQLException e) {
//            throw new RuntimeException("Error al ejecutar la sentencia: " + sql, e);
//        }

        return new byte[0];
    }
}
