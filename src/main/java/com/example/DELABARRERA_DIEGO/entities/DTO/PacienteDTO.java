package com.example.DELABARRERA_DIEGO.entities.DTO;

import com.example.DELABARRERA_DIEGO.entities.Domicilio;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
public class PacienteDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String documento;
    private LocalDate fechaIngreso;
    private String email;
    private Domicilio domicilio;


    public PacienteDTO() {
    }

    public PacienteDTO(String nombre, String apellido, String documento, LocalDate fechaIngreso, String email, Domicilio domicilio) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.fechaIngreso = fechaIngreso;
        this.email = email;
        this.domicilio = domicilio;
    }

    public PacienteDTO(Long id, String nombre, String apellido, String documento, LocalDate fechaIngreso, String email, Domicilio domicilio) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.fechaIngreso = fechaIngreso;
        this.email = email;
        this.domicilio = domicilio;
    }
}


