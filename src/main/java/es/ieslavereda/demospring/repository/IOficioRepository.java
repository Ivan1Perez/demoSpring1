package es.ieslavereda.demospring.repository;

import es.ieslavereda.demospring.repository.model.Oficio;

import java.sql.SQLException;
import java.util.List;

public interface IOficioRepository {

    public List<Oficio> getOficios(int id) throws SQLException;

    public byte[] getImageById(int id);

}
