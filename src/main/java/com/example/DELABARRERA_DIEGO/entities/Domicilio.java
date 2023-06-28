package com.example.DELABARRERA_DIEGO.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "domicilios")
@Getter
@Setter
public class Domicilio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String calle;
    @Column
    private String numero;
    @Column
    private String departamento;
    @Column
    private String localidad;

    public Domicilio() {
    }

    public Domicilio(String calle, String numero, String departamento, String localidad) {
        this.calle = calle;
        this.numero = numero;
        this.departamento = departamento;
        this.localidad = localidad;
    }

    public Domicilio(Long id, String calle, String numero, String departamento, String localidad) {
        this.id = id;
        this.calle = calle;
        this.numero = numero;
        this.departamento = departamento;
        this.localidad = localidad;
    }
}
