package com.example.profcalificador.profesor;

import jakarta.persistence.*;

@Entity
@Table(name = "profesor_perfiles")
public class ProfesorPerfil {

  @Id
  private Long profesorId;

  @OneToOne(fetch = FetchType.LAZY)
  @MapsId
  @JoinColumn(name = "profesor_id")
  private Profesor profesor;

  @Column(columnDefinition = "text")
  private String bio;

  @Column(length = 80)
  private String oficina;

  @Column(name = "foto_url", columnDefinition = "text")
  private String fotoUrl;

  public Long getProfesorId() { return profesorId; }
  public Profesor getProfesor() { return profesor; }
  public void setProfesor(Profesor profesor) { this.profesor = profesor; }
  public String getBio() { return bio; }
  public void setBio(String bio) { this.bio = bio; }
  public String getOficina() { return oficina; }
  public void setOficina(String oficina) { this.oficina = oficina; }
  public String getFotoUrl() { return fotoUrl; }
  public void setFotoUrl(String fotoUrl) { this.fotoUrl = fotoUrl; }
}
