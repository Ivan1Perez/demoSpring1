package es.ieslavereda.demospring.repository;

import es.ieslavereda.demospring.repository.model.MyDataSource;
import es.ieslavereda.demospring.repository.model.Oficio;
import es.ieslavereda.demospring.repository.model.Usuario;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Types.NULL;


@Repository
public class OficioRepositoryDB implements IOficioRepository{

    public Oficio getOficio(int id){

        Oficio oficio = null;

        String sql = "{call obtener_oficios(?)}";

        try(
                Connection c = MyDataSource.getMySQLDataSource().getConnection();
                CallableStatement cs = c.prepareCall(sql);
        ){

            int pos = 0;

            cs.setInt(++pos,id);

            ResultSet rs = cs.executeQuery();

            if (rs.next()) {
                oficio = new Oficio(rs.getInt("idOficio"),rs.getString("descripcion"),
                        rs.getBytes("image"),rs.getString("imageurl"));
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        return oficio;
    }

    public List<Oficio> getOficios(){

        List<Oficio> oficioList = new ArrayList<>();

        String sql = "{call obtener_oficios(?)}";

        try(
                Connection c = MyDataSource.getMySQLDataSource().getConnection();
                CallableStatement cs = c.prepareCall(sql);
        ){

            int pos = 0;

            cs.setNull(++pos, NULL);

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
    public Blob getImageById(int id) {

        String sql = "{call obtener_image_oficio(?,?)}";

        try(Connection c = MyDataSource.getMySQLDataSource().getConnection();
            CallableStatement cs = c.prepareCall(sql)){

            int pos = 0;

            cs.registerOutParameter(++pos, Types.BLOB);
            cs.setInt(++pos,id);

            cs.execute();

            return cs.getBlob(1);

        } catch (SQLException e) {
            throw new RuntimeException("Error al ejecutar la sentencia: " + sql, e);
        }

    }
}
