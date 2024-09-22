package com.example.demo.repository;

import com.example.demo.entity.Calculo;
import org.hibernate.annotations.processing.SQL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalculoRepository extends JpaRepository<Calculo,Long> {
    Calculo findByNAndK(String n,String k);
    @Query("select c FROM Calculo c WHERE c.usuario.id = :id")
    List<Calculo>findAllByIdUsuarioId(@Param("id") Long id);

}
