package es.ieslavereda.demospring.repository;

import es.ieslavereda.demospring.repository.model.Oficio;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public class OficioRepositoryDB implements IOficioRepository{


    @Override
    public List<Oficio> getOficios() throws SQLException {
        return null;
    }

    @Override
    public byte[] getImageById(int id) {
        return new byte[0];
    }
}
