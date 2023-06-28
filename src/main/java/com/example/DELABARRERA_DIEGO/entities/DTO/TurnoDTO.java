package com.example.DELABARRERA_DIEGO.entities.DTO;

import com.example.DELABARRERA_DIEGO.entities.Odontologo;
import com.example.DELABARRERA_DIEGO.entities.Paciente;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TurnoDTO {
    private Long id;
    private Paciente paciente;
    private Odontologo odontologo;
    private LocalDateTime fecha;


    public TurnoDTO() {
    }

    public TurnoDTO(Paciente paciente, Odontologo odontologo, LocalDateTime fecha) {
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.fecha = fecha;
    }

    public TurnoDTO(Long id, Paciente paciente, Odontologo odontologo, LocalDateTime fecha) {
        this.id = id;
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.fecha = fecha;
    }
}
