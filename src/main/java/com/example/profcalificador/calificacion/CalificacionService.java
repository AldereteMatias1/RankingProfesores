package com.example.profcalificador.calificacion;

import com.example.profcalificador.calificacion.dto.CalificacionResponse;
import com.example.profcalificador.calificacion.dto.CreateCalificacionRequest;
import com.example.profcalificador.common.BusinessRuleException;
import com.example.profcalificador.common.NotFoundException;
import com.example.profcalificador.profesor.Profesor;
import com.example.profcalificador.profesor.ProfesorRepository;
import com.example.profcalificador.usuario.Usuario;
import com.example.profcalificador.usuario.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
public class CalificacionService {

  private static final int MIN_DIAS_ENTRE_CALIFICACIONES = 3;

  private final CalificacionRepository calificacionRepository;
  private final ProfesorRepository profesorRepository;
  private final UsuarioRepository usuarioRepository;

  public CalificacionService(CalificacionRepository calificacionRepository,
                             ProfesorRepository profesorRepository,
                             UsuarioRepository usuarioRepository) {
    this.calificacionRepository = calificacionRepository;
    this.profesorRepository = profesorRepository;
    this.usuarioRepository = usuarioRepository;
  }

  @Transactional
  public CalificacionResponse crear(CreateCalificacionRequest req) {
    Profesor profesor = profesorRepository.findById(req.getProfesorId())
      .orElseThrow(() -> new NotFoundException("Profesor no encontrado: " + req.getProfesorId()));

    Usuario usuario = usuarioRepository.findById(req.getUsuarioId())
      .orElseThrow(() -> new NotFoundException("Usuario no encontrado: " + req.getUsuarioId()));

    calificacionRepository.findLastByProfesorAndUsuario(profesor.getId(), usuario.getId())
      .ifPresent(last -> {
        OffsetDateTime limite = last.getCreatedAt().plusDays(MIN_DIAS_ENTRE_CALIFICACIONES);
        if (OffsetDateTime.now().isBefore(limite)) {
          throw new BusinessRuleException(
            "Debés esperar " + MIN_DIAS_ENTRE_CALIFICACIONES + " días para volver a calificar a este profesor."
          );
        }
      });

    Calificacion c = new Calificacion();
    c.setProfesor(profesor);
    c.setUsuario(usuario);
    c.setPuntaje(req.getPuntaje());
    c.setComentario(req.getComentario());

    Calificacion saved = calificacionRepository.save(c);

    return new CalificacionResponse(
      saved.getId(),
      profesor.getId(),
      usuario.getId(),
      saved.getPuntaje(),
      saved.getComentario(),
      saved.getCreatedAt()
    );
  }
}
