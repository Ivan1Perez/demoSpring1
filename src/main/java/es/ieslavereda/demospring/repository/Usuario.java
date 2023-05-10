package es.ieslavereda.demospring.repository;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario {

    private int idUsuario;
    private String nombre;
    private String apellidos;

}
