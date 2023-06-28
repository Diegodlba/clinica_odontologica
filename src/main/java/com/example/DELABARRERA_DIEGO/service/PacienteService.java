package com.example.DELABARRERA_DIEGO.service;


import com.example.DELABARRERA_DIEGO.entities.DTO.PacienteDTO;
import com.example.DELABARRERA_DIEGO.entities.Paciente;
import com.example.DELABARRERA_DIEGO.exception.MethodArgumentNotValidException;
import com.example.DELABARRERA_DIEGO.exception.ResourceNotFoundException;
import com.example.DELABARRERA_DIEGO.repository.IPacienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PacienteService {
    @Autowired
    private IPacienteRepository pacienteRepository;

    @Autowired
    ObjectMapper mapper;

    private Logger logger = Logger.getLogger(String.valueOf(PacienteService.class));


    public void guardarPaciente(PacienteDTO pacienteDTO) throws MethodArgumentNotValidException {
        Paciente paciente = mapper.convertValue(pacienteDTO, Paciente.class);
        try {
            pacienteRepository.save(paciente);
        } catch (Exception e) {
            logger.error("Error al guardar el paciente" + e.getMessage());
            throw new MethodArgumentNotValidException("Datos invalidos para guardar el paciente");
        }
    }

    public PacienteDTO buscarPacientePorId(Long id) throws ResourceNotFoundException {
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        PacienteDTO pacienteDTO = null;
        if (paciente.isPresent()) {
            pacienteDTO = mapper.convertValue(paciente.get(), PacienteDTO.class);
            return pacienteDTO;
        } else {
            throw new ResourceNotFoundException("No existe un paciente con el ID: " + id);
        }
    }

    public void eliminarPaciente(Long id) throws ResourceNotFoundException {
        Optional<Paciente> pacienteBuscado = pacienteRepository.findById(id);
        if (pacienteBuscado.isPresent()) {
            pacienteRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("No existe un paciente con el ID: " + id);
        }
    }

    public void actualizarPaciente(PacienteDTO pacienteDTO) throws ResourceNotFoundException {
        Paciente paciente = mapper.convertValue(pacienteDTO, Paciente.class);
        Optional<Paciente> pacienteExistente = pacienteRepository.findById(paciente.getId());
        if (pacienteExistente.isPresent()) {
            pacienteRepository.save(paciente);
        } else {
            throw new ResourceNotFoundException("No existe el paciente");
        }
    }

    public Set<PacienteDTO> listarPacientes() throws ResourceNotFoundException {
        List<Paciente> pacientes = pacienteRepository.findAll();
        Set<PacienteDTO> pacientesDTO = new HashSet<>();
        for (Paciente paciente : pacientes) {
            pacientesDTO.add(mapper.convertValue(paciente, PacienteDTO.class));
        }
        if (pacientesDTO.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron pacientes");
        }
        return pacientesDTO;
    }


    public PacienteDTO buscarPacientePorEmail(String email) throws ResourceNotFoundException {
        Optional<Paciente> paciente = pacienteRepository.findByEmail(email);
        if (paciente.isPresent()) {
            PacienteDTO pacienteDTO = mapper.convertValue(paciente.get(), PacienteDTO.class);
            return pacienteDTO;
        } else {
            throw new ResourceNotFoundException("No se existe un paciente con el email: " + email);
        }
    }
}
