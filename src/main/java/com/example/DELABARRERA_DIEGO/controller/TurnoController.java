package com.example.DELABARRERA_DIEGO.controller;


import com.example.DELABARRERA_DIEGO.entities.DTO.TurnoDTO;
import com.example.DELABARRERA_DIEGO.entities.Turno;
import com.example.DELABARRERA_DIEGO.exception.MethodArgumentNotValidException;
import com.example.DELABARRERA_DIEGO.exception.ResourceNotFoundException;
import com.example.DELABARRERA_DIEGO.service.OdontologoService;
import com.example.DELABARRERA_DIEGO.service.PacienteService;
import com.example.DELABARRERA_DIEGO.service.TurnoService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
@CrossOrigin
public class TurnoController {
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;


    @PostMapping
    public ResponseEntity<?> registrarTurno(@RequestBody TurnoDTO turnoDTO) throws MethodArgumentNotValidException {
        turnoService.guardarTurno(turnoDTO);
        return ResponseEntity.ok(turnoDTO);
    }


    @GetMapping("/{id}")
    public TurnoDTO buscarTurnoPorId(@PathVariable Long id) throws ResourceNotFoundException {
        return turnoService.buscarTurnoPorId(id);
    }

    @GetMapping
    public Collection<TurnoDTO> listarTurnos() throws ResourceNotFoundException {
        return turnoService.listarTurnos();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarTurnoPorId(@PathVariable Long id) throws ResourceNotFoundException {
        turnoService.eliminarTurno(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> actualizarTurno(@RequestBody TurnoDTO turnoDTO) throws ResourceNotFoundException {
        turnoService.actualizarTurno(turnoDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}