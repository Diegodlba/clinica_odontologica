package com.example.DELABARRERA_DIEGO.service;

import com.example.DELABARRERA_DIEGO.entities.DTO.OdontologoDTO;
import com.example.DELABARRERA_DIEGO.entities.Odontologo;
import com.example.DELABARRERA_DIEGO.exception.MethodArgumentNotValidException;
import com.example.DELABARRERA_DIEGO.exception.ResourceNotFoundException;
import com.example.DELABARRERA_DIEGO.repository.IOdontologoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class OdontologoService {
    @Autowired
    private IOdontologoRepository odontologoRepository;

    @Autowired
    ObjectMapper mapper;

    private Logger logger = Logger.getLogger(String.valueOf(OdontologoService.class));


    public void guardarOdontologo(OdontologoDTO odontologoDTO) throws MethodArgumentNotValidException {
        Odontologo odontologo = mapper.convertValue(odontologoDTO, Odontologo.class);
        try {
            odontologoRepository.save(odontologo);
        } catch (Exception e) {
            logger.error("Error al guardar el odontologo" + e.getMessage());
            throw new MethodArgumentNotValidException("Datos invalidos para guardar el odontologo");
        }
    }

    public OdontologoDTO buscarOdontologoPorId(Long id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologo = odontologoRepository.findById(id);
        OdontologoDTO odontologoDTO = null;
        if (odontologo.isPresent()) {
            odontologoDTO = mapper.convertValue(odontologo.get(), OdontologoDTO.class);
            return odontologoDTO;
        } else {
            throw new ResourceNotFoundException("No existe un odontologo con el ID: " + id);
        }
    }


    public OdontologoDTO buscarOdontologoPorMatricula(String matricula) throws ResourceNotFoundException {
        Optional<Odontologo> odontologo = odontologoRepository.findByMatricula(matricula);
        if (odontologo.isPresent()) {
            return mapper.convertValue(odontologo.get(), OdontologoDTO.class);
        } else {
            throw new ResourceNotFoundException("No se encontró un odotologo con la matricula: " + matricula);
        }
    }

    public Set<OdontologoDTO> listarOdontologos() throws ResourceNotFoundException{
        List<Odontologo> odontologos = odontologoRepository.findAll();
        Set<OdontologoDTO> odontologosDTO = new HashSet<>();
        for (Odontologo odontologo : odontologos) {
            odontologosDTO.add(mapper.convertValue(odontologo, OdontologoDTO.class));
        }
        if (odontologosDTO.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron odontologos");
        }
        return odontologosDTO;
    }

    public void actualizarOdontologo(OdontologoDTO odontologoDTO) throws ResourceNotFoundException {
        Odontologo odontologo = mapper.convertValue(odontologoDTO, Odontologo.class);
        Optional<Odontologo> odontologoExistente = odontologoRepository.findById(odontologo.getId());
        if (odontologoExistente.isPresent()) {
            odontologoRepository.save(odontologo);
        }else{
            throw new ResourceNotFoundException("No existe el odontologo");
        }
    }


    public void eliminarOdontologo(Long id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoBuscado = odontologoRepository.findById(id);
        if (odontologoBuscado.isPresent()) {
            odontologoRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("No existe un odontologo con ese ID");
        }
    }
}
