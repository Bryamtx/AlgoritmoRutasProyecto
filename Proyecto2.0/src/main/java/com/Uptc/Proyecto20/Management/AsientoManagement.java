package com.Uptc.Proyecto20.Management;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Uptc.Proyecto20.Entities.*;
@Repository
public interface AsientoManagement extends JpaRepository<Asiento, Long> {
    
    List<Asiento> findByOcupado(boolean ocupado);
    
    List<Asiento> findByFila(String fila);
    
    List<Asiento> findByFilaAndOcupado(String fila, boolean ocupado);
}