package com.example.DELABARRERA_DIEGO.repository;


import com.example.DELABARRERA_DIEGO.entities.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITurnoRepository extends JpaRepository<Turno, Long> {
}
