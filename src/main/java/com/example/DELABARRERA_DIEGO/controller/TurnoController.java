package com.example.DELABARRERA_DIEGO.controller;


import com.example.DELABARRERA_DIEGO.entities.Turno;
import com.example.DELABARRERA_DIEGO.service.OdontologoService;
import com.example.DELABARRERA_DIEGO.service.PacienteService;
import com.example.DELABARRERA_DIEGO.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;


    @PostMapping
    public ResponseEntity<Turno> regisrarTurno(@RequestBody Turno turno) {
        if (odontologoService.buscarOdontologoPorId(turno.getOdontologo().getId()).isPresent() && pacienteService.buscarPacientePorId(turno.getPaciente().getId()).isPresent()) {
            return ResponseEntity.ok(turnoService.guardarTurno(turno));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Turno>> listarTurnos() {
        return ResponseEntity.ok(turnoService.listarTurnos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turno> buscarTurnoPorId(@PathVariable Long id) {
        Optional<Turno> turnoBuscado = turnoService.buscarTurnoPorId(id);
        if (turnoBuscado.isPresent()) {
            return ResponseEntity.ok(turnoBuscado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id) {
        Optional<Turno> turnoBuscado = turnoService.buscarTurnoPorId(id);
        if (turnoBuscado.isPresent()) {
            turnoService.eliminarTurno(id);
            return ResponseEntity.ok("Se eliminó correctamente el turno con ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("No existe el turno con ID: " + id);
        }
    }

    @PutMapping
    public ResponseEntity<String> actualizarturno(@RequestBody Turno turno) {
        Optional<Turno> turnoBuscado = turnoService.buscarTurnoPorId(turno.getId());
        if (turnoBuscado.isPresent()) {
            turnoService.actualizarTurno(turno);
            return ResponseEntity.ok("Se actualizó correctamente el turno con ID: " + turno.getId());
        } else {
            return ResponseEntity.badRequest().body("No se existe el turno con ID: " + turno.getId());
        }
    }



}
