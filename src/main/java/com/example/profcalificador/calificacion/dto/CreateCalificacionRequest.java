package com.example.profcalificador.calificacion.dto;

import jakarta.validation.constraints.*;

public class CreateCalificacionRequest {

  @NotNull
  private Long profesorId;

  @NotNull
  private Long usuarioId;

  @NotNull
  @Min(1) @Max(5)
  private Integer puntaje;

  @Size(max = 2000)
  private String comentario;

  public Long getProfesorId() { return profesorId; }
  public void setProfesorId(Long profesorId) { this.profesorId = profesorId; }
  public Long getUsuarioId() { return usuarioId; }
  public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
  public Integer getPuntaje() { return puntaje; }
  public void setPuntaje(Integer puntaje) { this.puntaje = puntaje; }
  public String getComentario() { return comentario; }
  public void setComentario(String comentario) { this.comentario = comentario; }
}
