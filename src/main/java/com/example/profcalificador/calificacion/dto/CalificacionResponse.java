package com.example.profcalificador.calificacion.dto;

import java.time.OffsetDateTime;

public record CalificacionResponse(
  Long id,
  Long profesorId,
  Long usuarioId,
  Integer puntaje,
  String comentario,
  OffsetDateTime createdAt
) {}
