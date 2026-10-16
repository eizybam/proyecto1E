package mx.edu.utez.proyecto1E.service.usuario;


import mx.edu.utez.proyecto1E.controller.usuario.dto.RequestCreateUserDto;
import mx.edu.utez.proyecto1E.exception.ErrorHandler;
import mx.edu.utez.proyecto1E.exception.custom.CustomBadRequestException;
import mx.edu.utez.proyecto1E.model.persona.Persona;
import mx.edu.utez.proyecto1E.model.usuario.Roles;
import mx.edu.utez.proyecto1E.model.usuario.Usuario;
import mx.edu.utez.proyecto1E.repository.persona.PersonaRepository;
import mx.edu.utez.proyecto1E.repository.usuario.UsuarioRpository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import mx.edu.utez.proyecto1E.controller.usuario.dto.RequestUpdateUserDto;
import mx.edu.utez.proyecto1E.exception.custom.CustomNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    // Inyeccion de dependencias
    private final UsuarioRpository usuarioRpository;
    private final PersonaRepository personaRepository;

    public UsuarioService(UsuarioRpository usuarioRpository, PersonaRepository personaRepository) {
        this.usuarioRpository = usuarioRpository;
        this.personaRepository = personaRepository;
    }

    // Servicios
    public List<Usuario> getAllUsers () {
        return usuarioRpository.findAll();
    }

    @Transactional
    public Usuario createUser (RequestCreateUserDto payload) {

        // Buscar si el usuario no esta registrado
        Optional<Usuario> usuarioExist = usuarioRpository.findByUsername(payload.getUsername());

        if (usuarioExist.isPresent()) {
            throw new CustomBadRequestException("el username existe");
        }

        Persona newPersona = new Persona();
        newPersona.setNombres(payload.getNombres());
        newPersona.setPrimerApellido(payload.getPrimerApellido());
        newPersona.setEmail(payload.getEmail());
        newPersona.setCurp(payload.getCurp());
        newPersona.setFechaNacimiento(payload.getFechaNacimiento());

        personaRepository.save(newPersona);

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setUsername(payload.getUsername());
        nuevoUsuario.setPassword(payload.getPassword());
        nuevoUsuario.setDescripcion(payload.getDescripcion());
        nuevoUsuario.setRol(Roles.valueOf(payload.getRol()));
        nuevoUsuario.set_enable(true);
        // Relacionar el registro de persona con el nuevo usuario
        nuevoUsuario.setPersona(newPersona);


        return usuarioRpository.save(nuevoUsuario);
    }

    // Buscar usuario por id (confirmar info)
    public Usuario getUserById (Long id) {
        return usuarioRpository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("El usuario no existe"));
    }

    // Activar usuario
    @Transactional
    public Usuario activarUsuario (Long id) {
        Usuario usuario = getUserById(id);
        if (usuario.is_enable()){
            throw new CustomBadRequestException("El usuario ya se encuentra activado");
        }
        usuario.set_enable(true);
        return usuarioRpository.save(usuario);
    }

    // Desactivar usuario
    @Transactional
    public Usuario desactivarUsuario (Long id) {
        Usuario usuario = getUserById(id);
        if (!usuario.is_enable()) {
            throw new CustomBadRequestException("El usuario ya se encuentra desactivado");
        }
        usuario.set_enable(false);
        return usuarioRpository.save(usuario);
    }
    // Actualizar solo username y descripcion
    @Transactional
    public Usuario updateUser (Long id, RequestUpdateUserDto payload) {
        Usuario usuario = getUserById(id);

        // Validar que el nuevo username no lo tenga OTRO usuario
        Optional<Usuario> usuarioExist = usuarioRpository.findByUsername(payload.getUsername());
        if (usuarioExist.isPresent() && !usuarioExist.get().getId().equals(id)) {
            throw new CustomBadRequestException("el username existe");
        }

        usuario.setUsername(payload.getUsername());
        usuario.setDescripcion(payload.getDescripcion());
        // NO se toca usuario.setPersona(...) para no perder la relacion

        return usuarioRpository.save(usuario);
    }


}