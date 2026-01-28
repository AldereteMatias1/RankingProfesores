package com.example.profcalificador.profesor;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.*;

public interface ProfesorRepository extends JpaRepository<Profesor, Long> {

  @Query("""
    select distinct p from Profesor p
    left join fetch p.materias
    left join fetch p.perfil
    where p.id = :id
  """)
  Optional<Profesor> findByIdWithPerfilAndMaterias(@Param("id") Long id);
}
