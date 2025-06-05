package com.Uptc.Proyecto20.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Uptc.Proyecto20.Entities.Asiento;
import com.Uptc.Proyecto20.Management.AsientoManagement;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;

@Service
public class AsientoService {

    @Autowired
    private AsientoManagement asientoRepository;

    @PostConstruct
    public void inicializarAsientos() {
        // Si no hay asientos en la base de datos, crear algunos de ejemplo
        if (asientoRepository.count() == 0) {
            String[] filas = {"A", "B", "C", "D", "E"};
            for (String fila : filas) {
                for (int i = 1; i <= 10; i++) {
                    Asiento asiento = new Asiento(String.valueOf(i), fila, false);
                    asientoRepository.save(asiento);
                }
            }
            // Marcar algunos asientos como ocupados para demostración
            ocuparAsientosPorDefecto();
        }
    }

    private void ocuparAsientosPorDefecto() {
        List<Asiento> asientos = asientoRepository.findAll();
        if (asientos.size() >= 5) {
            asientos.get(0).setOcupado(true);
            asientos.get(2).setOcupado(true);
            asientos.get(7).setOcupado(true);
            asientos.get(15).setOcupado(true);
            asientos.get(23).setOcupado(true);
            asientoRepository.saveAll(asientos);
        }
    }

    public List<Asiento> obtenerTodosLosAsientos() {
        return asientoRepository.findAll();
    }

    public Asiento cambiarEstadoAsiento(Long id) {
        Optional<Asiento> asientoOpt = asientoRepository.findById(id);
        if (asientoOpt.isPresent()) {
            Asiento asiento = asientoOpt.get();
            asiento.setOcupado(!asiento.isOcupado());
            return asientoRepository.save(asiento);
        }
        throw new RuntimeException("Asiento no encontrado con ID: " + id);
    }

    public Asiento reservarAsiento(Long id) {
        Optional<Asiento> asientoOpt = asientoRepository.findById(id);
        if (asientoOpt.isPresent()) {
            Asiento asiento = asientoOpt.get();
            if (!asiento.isOcupado()) {
                asiento.setOcupado(true);
                return asientoRepository.save(asiento);
            } else {
                throw new RuntimeException("El asiento ya está ocupado");
            }
        }
        throw new RuntimeException("Asiento no encontrado con ID: " + id);
    }

    @Transactional
    public List<Asiento> reservarMultiplesAsientos(List<Long> idsAsientos) {
        List<Asiento> asientosReservados = new ArrayList<>();
        List<Asiento> asientosAReservar = new ArrayList<>();
        
        // Primero verificamos que todos los asientos estén disponibles
        for (Long id : idsAsientos) {
            Optional<Asiento> asientoOpt = asientoRepository.findById(id);
            if (asientoOpt.isPresent()) {
                Asiento asiento = asientoOpt.get();
                if (asiento.isOcupado()) {
                    throw new RuntimeException("El asiento " + asiento.getFila() + asiento.getNumero() + " ya está ocupado");
                }
                asientosAReservar.add(asiento);
            } else {
                throw new RuntimeException("Asiento no encontrado con ID: " + id);
            }
        }
        
        // Si todos están disponibles, los reservamos
        for (Asiento asiento : asientosAReservar) {
            asiento.setOcupado(true);
            Asiento asientoGuardado = asientoRepository.save(asiento);
            asientosReservados.add(asientoGuardado);
        }
        
        return asientosReservados;
    }

    public Asiento liberarAsiento(Long id) {
        Optional<Asiento> asientoOpt = asientoRepository.findById(id);
        if (asientoOpt.isPresent()) {
            Asiento asiento = asientoOpt.get();
            asiento.setOcupado(false);
            return asientoRepository.save(asiento);
        }
        throw new RuntimeException("Asiento no encontrado con ID: " + id);
    }

    public List<Asiento> obtenerAsientosDisponibles() {
        return asientoRepository.findByOcupado(false);
    }

    public List<Asiento> obtenerAsientosOcupados() {
        return asientoRepository.findByOcupado(true);
    }

    public boolean validarDisponibilidadAsientos(List<Long> idsAsientos) {
        for (Long id : idsAsientos) {
            Optional<Asiento> asientoOpt = asientoRepository.findById(id);
            if (asientoOpt.isPresent()) {
                if (asientoOpt.get().isOcupado()) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }
}