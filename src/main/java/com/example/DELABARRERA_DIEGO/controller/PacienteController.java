package com.example.DELABARRERA_DIEGO.controller;


import com.example.DELABARRERA_DIEGO.entities.DTO.PacienteDTO;
import com.example.DELABARRERA_DIEGO.exception.MethodArgumentNotValidException;
import com.example.DELABARRERA_DIEGO.exception.ResourceNotFoundException;
import com.example.DELABARRERA_DIEGO.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequestMapping("/pacientes")
@CrossOrigin
public class PacienteController {
    @Autowired
    private PacienteService pacienteService;

    @PostMapping()
    public ResponseEntity<?> registrarPaciente(@RequestBody PacienteDTO pacienteDTO) throws MethodArgumentNotValidException {
        pacienteService.guardarPaciente(pacienteDTO);
        return ResponseEntity.ok(pacienteDTO);
    }


    @GetMapping("/{id}")
    public PacienteDTO buscarPacientePorId(@PathVariable Long id) throws ResourceNotFoundException {
        return pacienteService.buscarPacientePorId(id);
    }

    @GetMapping("/email/{email}")
    public PacienteDTO buscarPacientePorId(@PathVariable String email) throws ResourceNotFoundException {
        return pacienteService.buscarPacientePorEmail(email);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPacientePorId(@PathVariable Long id) throws ResourceNotFoundException {
        pacienteService.eliminarPaciente(id);
        return ResponseEntity.ok().body("Se eliminó correctamente el paciente con ID: " + id);
    }

    @PutMapping
    public ResponseEntity<?> actualizarPaciente(@RequestBody PacienteDTO paicenteDTO) throws ResourceNotFoundException {
        pacienteService.actualizarPaciente(paicenteDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping
    public Collection<PacienteDTO> listarPacientes() throws ResourceNotFoundException {
        return pacienteService.listarPacientes();
    }


}