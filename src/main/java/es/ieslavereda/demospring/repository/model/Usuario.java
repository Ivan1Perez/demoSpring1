package es.ieslavereda.demospring.repository.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario {

    private Integer idUsuario;
    private String nombre;
    private String apellidos;
    private int idOficio;

    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof Usuario))
            return false;

        Usuario u = (Usuario) obj;
        return u.getIdUsuario()==getIdUsuario();
    }

}
