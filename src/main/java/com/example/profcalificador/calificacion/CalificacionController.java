package com.example.profcalificador.calificacion;

import com.example.profcalificador.calificacion.dto.CalificacionResponse;
import com.example.profcalificador.calificacion.dto.CreateCalificacionRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/calificaciones")
public class CalificacionController {

  private final CalificacionService calificacionService;

  public CalificacionController(CalificacionService calificacionService) {
    this.calificacionService = calificacionService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CalificacionResponse crear(@Valid @RequestBody CreateCalificacionRequest req) {
    return calificacionService.crear(req);
  }
}
