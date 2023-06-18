package com.example.DELABARRERA_DIEGO.service;



import com.example.DELABARRERA_DIEGO.entities.Paciente;
import com.example.DELABARRERA_DIEGO.repository.PacienteRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    @Autowired
    private PacienteRepository pacienteRepository;

    private Logger logger = Logger.getLogger(String.valueOf(PacienteService.class));

    public Paciente guardarPaciente(Paciente paciente) {
        logger.info("iniciando el guardado de un paciente");
        try {
            return pacienteRepository.save(paciente);
        } catch (Exception e) {
            logger.error("Error al guardar el paciente: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public Optional<Paciente> buscarPacientePorId(Long id) {
        return pacienteRepository.findById(id);
    }

    public void eliminarPaciente(Long id) {
        pacienteRepository.deleteById(id);
    }

    public void actualizarPaciente(Paciente paciente) {
        pacienteRepository.save(paciente);
    }

    public List<Paciente> listarPacientes() {
        return pacienteRepository.findAll();
    }

    public Optional<Paciente> buscarPacientePorEmail(String email) {
        return pacienteRepository.findByEmail(email);
    }
}
