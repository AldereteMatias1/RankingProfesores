package com.example.profcalificador.profesor;

import com.example.profcalificador.calificacion.Calificacion;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "profesores")
public class Profesor {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 120)
  private String nombre;

  @Column(nullable = false, length = 120)
  private String apellido;

  @Column(unique = true, length = 180)
  private String email;

  // OneToOne
  @OneToOne(mappedBy = "profesor", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
  private ProfesorPerfil perfil;

  // ManyToMany
  @ManyToMany
  @JoinTable(
    name = "profesor_materia",
    joinColumns = @JoinColumn(name = "profesor_id"),
    inverseJoinColumns = @JoinColumn(name = "materia_id")
  )
  private Set<Materia> materias = new HashSet<>();

  // OneToMany
  @OneToMany(mappedBy = "profesor", fetch = FetchType.LAZY)
  private Set<Calificacion> calificaciones = new HashSet<>();

  public Long getId() { return id; }
  public String getNombre() { return nombre; }
  public void setNombre(String nombre) { this.nombre = nombre; }
  public String getApellido() { return apellido; }
  public void setApellido(String apellido) { this.apellido = apellido; }
  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }
  public ProfesorPerfil getPerfil() { return perfil; }
  public void setPerfil(ProfesorPerfil perfil) { this.perfil = perfil; }
  public Set<Materia> getMaterias() { return materias; }
  public void setMaterias(Set<Materia> materias) { this.materias = materias; }
}
