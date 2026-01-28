package com.example.profcalificador.profesor;

import com.example.profcalificador.profesor.dto.ProfesorResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profesores")
public class ProfesorController {

  private final ProfesorService profesorService;

  public ProfesorController(ProfesorService profesorService) {
    this.profesorService = profesorService;
  }

  @GetMapping("/{id}")
  public ProfesorResponse getById(@PathVariable Long id) {
    return profesorService.getProfesor(id);
  }
}
