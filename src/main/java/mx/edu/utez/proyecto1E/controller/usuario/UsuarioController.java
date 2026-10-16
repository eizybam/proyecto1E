package mx.edu.utez.proyecto1E.controller.usuario;

import jakarta.validation.Valid;
import mx.edu.utez.proyecto1E.controller.usuario.dto.RequestCreateUserDto;
import mx.edu.utez.proyecto1E.model.usuario.Usuario;
import mx.edu.utez.proyecto1E.service.usuario.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import mx.edu.utez.proyecto1E.controller.usuario.dto.RequestUpdateUserDto;

@RestController
@CrossOrigin ("*")
@RequestMapping("/usuario")
public class UsuarioController {
    // Inyeccion de dependencias

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsers () {
        return ResponseEntity.status(200).body(usuarioService.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<Usuario> createUser
            (@RequestBody @Valid RequestCreateUserDto payload) {
        return ResponseEntity.status(201).body(usuarioService.createUser(payload));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUserById (@PathVariable Long id) {
        return ResponseEntity.status(200).body(usuarioService.getUserById(id));
    }

    @PatchMapping("/{id}/activar")
    public ResponseEntity<Usuario> activarUsuario (@PathVariable Long id) {
        return ResponseEntity.status(200).body(usuarioService.activarUsuario(id));
    }

    @PatchMapping("/{id}/desactivar")
    public ResponseEntity<Usuario> desactivarUsuario (@PathVariable Long id) {
        return ResponseEntity.status(200).body(usuarioService.desactivarUsuario(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUser
            (@PathVariable Long id, @RequestBody @Valid RequestUpdateUserDto payload) {
        return ResponseEntity.status(200).body(usuarioService.updateUser(id, payload));
    }
}


