package com.example.DELABARRERA_DIEGO.service;

import com.example.DELABARRERA_DIEGO.entities.DTO.TurnoDTO;
import com.example.DELABARRERA_DIEGO.entities.Turno;
import com.example.DELABARRERA_DIEGO.exception.MethodArgumentNotValidException;
import com.example.DELABARRERA_DIEGO.exception.ResourceNotFoundException;
import com.example.DELABARRERA_DIEGO.repository.ITurnoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class TurnoService {
    @Autowired
    private ITurnoRepository turnoRepository;

    @Autowired
    ObjectMapper mapper;

    private Logger logger = Logger.getLogger(String.valueOf(OdontologoService.class));


    public void guardarTurno(TurnoDTO turnoDTO) throws MethodArgumentNotValidException {
        Turno turno = mapper.convertValue(turnoDTO, Turno.class);
        try {
            turnoRepository.save(turno);
        } catch (Exception e) {
            logger.error("Error al guardar el turno " + e.getMessage());
            throw new MethodArgumentNotValidException("Datos invalidos para guardar el turno");
        }
    }

    public TurnoDTO buscarTurnoPorId(Long id) throws ResourceNotFoundException {
        Optional<Turno> turno = turnoRepository.findById(id);
        TurnoDTO turnoDTO = null;
        if (turno.isPresent()) {
            turnoDTO = mapper.convertValue(turno.get(), TurnoDTO.class);
            return turnoDTO;
        } else {
            throw new ResourceNotFoundException("No existe un turno con el ID: " + id);
        }
    }


    public Set<TurnoDTO> listarTurnos() throws ResourceNotFoundException {
        List<Turno> turnos = turnoRepository.findAll();
        Set<TurnoDTO> turnosDTO = new HashSet<>();
        for (Turno turno : turnos) {
            turnosDTO.add(mapper.convertValue(turno, TurnoDTO.class));
        }
        if (turnosDTO.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron turnos");
        }
        return turnosDTO;
    }


    public void eliminarTurno(Long id) throws ResourceNotFoundException {
        Optional<Turno> turnoBuscado = turnoRepository.findById(id);
        if (turnoBuscado.isPresent()) {
            turnoRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("No existe un turno con id: " + id);
        }
    }

    public void actualizarTurno(TurnoDTO turnoDTO) throws ResourceNotFoundException {
        Turno turno = mapper.convertValue(turnoDTO, Turno.class);
        Optional<Turno> turnoExistente = turnoRepository.findById(turno.getId());
        if (turnoExistente.isPresent()) {
            turnoRepository.save(turno);
        } else {
            throw new ResourceNotFoundException("No existe el turno");
        }
    }

}