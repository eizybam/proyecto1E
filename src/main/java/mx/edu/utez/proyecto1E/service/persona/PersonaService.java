package mx.edu.utez.proyecto1E.service.persona;

import mx.edu.utez.proyecto1E.controller.persona.dto.RequestUpdatePersonaDto;
import mx.edu.utez.proyecto1E.exception.custom.CustomNotFoundException;
import mx.edu.utez.proyecto1E.model.persona.Persona;
import mx.edu.utez.proyecto1E.repository.persona.PersonaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonaService {
    // Inyeccion de dependencias
    private final PersonaRepository personaRepository;

    public PersonaService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    // Servicios
    public Persona getPersonaById (Long id) {
        return personaRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("La persona no existe"));
    }

    @Transactional
    public Persona updatePersona (Long id, RequestUpdatePersonaDto payload) {
        Persona persona = getPersonaById(id);

        persona.setNombres(payload.getNombres());
        persona.setPrimerApellido(payload.getPrimerApellido());
        persona.setSegundoApellido(payload.getSegundoApellido());
        persona.setCurp(payload.getCurp());
        persona.setEmail(payload.getEmail());
        persona.setFechaNacimiento(payload.getFechaNacimiento());

        return personaRepository.save(persona);
    }
}
