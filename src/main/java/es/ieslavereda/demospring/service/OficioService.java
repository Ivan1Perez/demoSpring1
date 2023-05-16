package es.ieslavereda.demospring.service;

import es.ieslavereda.demospring.repository.IOficioRepository;
import es.ieslavereda.demospring.repository.model.Oficio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class OficioService {

    @Autowired
    private IOficioRepository repository;

    public List<Oficio> getOficios(int id) throws SQLException{
        return repository.getOficios(id);
    }

    public byte[] getImageById(int id){
        return repository.getImageById(id);
    }


}
