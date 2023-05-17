package es.ieslavereda.demospring.repository;

import es.ieslavereda.demospring.repository.model.Oficio;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

public interface IOficioRepository {

    public Oficio getOficio(int id) throws SQLException;

    public List<Oficio> getOficios() throws SQLException;

    public Blob getImageById(int id);

}
