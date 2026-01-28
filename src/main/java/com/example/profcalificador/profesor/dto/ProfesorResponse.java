package com.example.profcalificador.profesor.dto;

import java.util.Set;

public record ProfesorResponse(
  Long id,
  String nombre,
  String apellido,
  String email,
  Perfil perfil,
  Set<String> materias,
  Double promedio
) {
  public record Perfil(String bio, String oficina, String fotoUrl) {}
}
