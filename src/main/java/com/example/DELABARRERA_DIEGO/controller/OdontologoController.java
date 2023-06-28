package com.example.DELABARRERA_DIEGO.controller;

import com.example.DELABARRERA_DIEGO.entities.DTO.OdontologoDTO;
import com.example.DELABARRERA_DIEGO.entities.Odontologo;
import com.example.DELABARRERA_DIEGO.exception.MethodArgumentNotValidException;
import com.example.DELABARRERA_DIEGO.exception.ResourceNotFoundException;
import com.example.DELABARRERA_DIEGO.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
@CrossOrigin
public class OdontologoController {
    @Autowired
    private OdontologoService odontologoService;

    @PostMapping
    public ResponseEntity<?> guardarOdontologo(@RequestBody OdontologoDTO odontologoDTO) throws MethodArgumentNotValidException {
        odontologoService.guardarOdontologo(odontologoDTO);
        return ResponseEntity.ok(odontologoDTO);
    }

    @GetMapping("/{id}")
    public OdontologoDTO buscarOdontologoPorId(@PathVariable Long id) throws ResourceNotFoundException {
        return odontologoService.buscarOdontologoPorId(id);
    }

    @GetMapping("/matricula/{matricula}")
    public OdontologoDTO buscarOdontologoPorMatricula(@PathVariable String matricula) throws ResourceNotFoundException {
        return odontologoService.buscarOdontologoPorMatricula(matricula);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
        odontologoService.eliminarOdontologo(id);
        return ResponseEntity.ok().body("Se eliminó correctamente el odontologo");
    }


    @GetMapping
    public Collection<OdontologoDTO> listarOdontologos() throws ResourceNotFoundException {
        return odontologoService.listarOdontologos();
    }

    @PutMapping
    public ResponseEntity<?> actualizarOdontologo(@RequestBody OdontologoDTO odontologoDTO) throws ResourceNotFoundException {
        odontologoService.actualizarOdontologo(odontologoDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }


}


