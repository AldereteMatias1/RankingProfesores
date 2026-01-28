package com.example.profcalificador.profesor;

import com.example.profcalificador.calificacion.CalificacionRepository;
import com.example.profcalificador.common.NotFoundException;
import com.example.profcalificador.profesor.dto.ProfesorResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
public class ProfesorService {

  private final ProfesorRepository profesorRepository;
  private final CalificacionRepository calificacionRepository;

  public ProfesorService(ProfesorRepository profesorRepository,
                         CalificacionRepository calificacionRepository) {
    this.profesorRepository = profesorRepository;
    this.calificacionRepository = calificacionRepository;
  }

  @Transactional(readOnly = true)
  public ProfesorResponse getProfesor(Long id) {
    Profesor p = profesorRepository.findByIdWithPerfilAndMaterias(id)
      .orElseThrow(() -> new NotFoundException("Profesor no encontrado: " + id));

    Double avg = calificacionRepository.avgByProfesor(id);

    ProfesorResponse.Perfil perfil = null;
    if (p.getPerfil() != null) {
      perfil = new ProfesorResponse.Perfil(
        p.getPerfil().getBio(),
        p.getPerfil().getOficina(),
        p.getPerfil().getFotoUrl()
      );
    }

    return new ProfesorResponse(
      p.getId(),
      p.getNombre(),
      p.getApellido(),
      p.getEmail(),
      perfil,
      p.getMaterias().stream().map(Materia::getNombre).collect(Collectors.toSet()),
      avg
    );
  }
}
