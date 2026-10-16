package mx.edu.utez.proyecto1E.model.persona;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.proyecto1E.model.curso.Curso;
import org.hibernate.annotations.AnyDiscriminatorImplicitValues;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "personas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombres;

    @NotBlank(message = "El apellido es obligatorio")
    private String primerApellido;

    private String segundoApellido;

    @NotBlank(message = "El CURP es obligatorio")
    private String curp;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo no es válido")
    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;

// TABLA DE INTERSECCION
//    @ManyToMany
//    @JoinTable (
//            name = "personas_cursos",
//            joinColumns = @JoinColumn (name = "persona_id"),
//            inverseJoinColumns = @JoinColumn (name = "curso_id")
//    )
//    private List<Curso> cursos;





}
