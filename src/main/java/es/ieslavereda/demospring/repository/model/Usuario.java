package es.ieslavereda.demospring.repository.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Usuario {

    private int idUsuario;
    private String nombre;
    private String apellidos;

    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof Usuario))
            return false;

        Usuario u = (Usuario) obj;
        return u.getIdUsuario()==getIdUsuario();
    }

}
