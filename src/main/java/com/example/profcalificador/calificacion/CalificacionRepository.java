package com.example.profcalificador.calificacion;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.time.OffsetDateTime;
import java.util.Optional;

public interface CalificacionRepository extends JpaRepository<Calificacion, Long> {

  @Query("""
    select c from Calificacion c
    where c.profesor.id = :profesorId and c.usuario.id = :usuarioId
    order by c.createdAt desc
  """)
  Optional<Calificacion> findLastByProfesorAndUsuario(@Param("profesorId") Long profesorId,
                                                     @Param("usuarioId") Long usuarioId);

  @Query("""
    select avg(c.puntaje) from Calificacion c
    where c.profesor.id = :profesorId
  """)
  Double avgByProfesor(@Param("profesorId") Long profesorId);

  @Query("""
    select count(c) from Calificacion c
    where c.profesor.id = :profesorId and c.createdAt >= :since
  """)
  long countLastDays(@Param("profesorId") Long profesorId, @Param("since") OffsetDateTime since);
}
