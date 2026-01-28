package com.example.profcalificador.usuario;

import com.example.profcalificador.calificacion.Calificacion;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usuarios")
public class Usuario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true, length = 180)
  private String email;

  @Column(nullable = false, length = 120)
  private String nombre;

  @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
  private Set<Calificacion> calificaciones = new HashSet<>();

  public Long getId() { return id; }
  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }
  public String getNombre() { return nombre; }
  public void setNombre(String nombre) { this.nombre = nombre; }
}
