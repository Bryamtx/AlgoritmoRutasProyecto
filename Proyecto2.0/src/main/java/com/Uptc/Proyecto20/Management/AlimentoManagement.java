package com.Uptc.Proyecto20.Management;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Uptc.Proyecto20.Entities.Alimento;

@Repository
public interface AlimentoManagement extends JpaRepository<Alimento, Long> {
    
    List<Alimento> findByNombreContainingIgnoreCase(String nombre);
    
    List<Alimento> findByPrecioBetween(Double precioMin, Double precioMax);
    
    List<Alimento> findAllByOrderByNombreAsc();
}