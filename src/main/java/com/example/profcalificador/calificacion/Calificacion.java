package com.example.profcalificador.calificacion;

import com.example.profcalificador.profesor.Profesor;
import com.example.profcalificador.usuario.Usuario;
import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "calificaciones")
public class Calificacion {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "profesor_id")
  private Profesor profesor;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "usuario_id")
  private Usuario usuario;

  @Column(nullable = false)
  private Integer puntaje;

  @Column(columnDefinition = "text")
  private String comentario;

  @Column(name = "created_at", nullable = false)
  private OffsetDateTime createdAt;

  @PrePersist
  void prePersist() {
    if (createdAt == null) {
      createdAt = OffsetDateTime.now();
    }
  }

  public Long getId() { return id; }
  public Profesor getProfesor() { return profesor; }
  public void setProfesor(Profesor profesor) { this.profesor = profesor; }
  public Usuario getUsuario() { return usuario; }
  public void setUsuario(Usuario usuario) { this.usuario = usuario; }
  public Integer getPuntaje() { return puntaje; }
  public void setPuntaje(Integer puntaje) { this.puntaje = puntaje; }
  public String getComentario() { return comentario; }
  public void setComentario(String comentario) { this.comentario = comentario; }
  public OffsetDateTime getCreatedAt() { return createdAt; }
}
