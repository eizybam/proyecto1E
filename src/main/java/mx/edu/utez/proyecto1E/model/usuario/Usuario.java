package mx.edu.utez.proyecto1E.model.usuario;

import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.proyecto1E.model.persona.Persona;

@Entity
@Table (name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "username1", nullable = false, unique = true)
    private String username;

    private String password;
    private boolean is_enable;

    @Column (columnDefinition = "TEXT")
    private String descripcion;

//    @Transient
//    private String pruebaColumna;

    @Enumerated(EnumType.STRING)
    private Roles rol;


    // Relacion 1:1 con tabla persona
    @OneToOne
    @JoinColumn (name = "persoma_id")
    private Persona persona;

}
