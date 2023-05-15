package es.ieslavereda.demospring.repository.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Oficio {

    private int idOficio;
    private String descripcion;
    private byte[] imagen;
    private String imageurl;

    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof Oficio))
            return false;

        Oficio o = (Oficio) obj;
        return o.getIdOficio()==getIdOficio();
    }


}
