package com.Uptc.Proyecto20.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Uptc.Proyecto20.Entities.Alimento;
import com.Uptc.Proyecto20.Management.AlimentoManagement;

import jakarta.annotation.PostConstruct;

@Service
public class AlimentoService {

    @Autowired
    private AlimentoManagement alimentoRepository;

    @PostConstruct
    public void inicializarAlimentos() {
        // Si no hay alimentos en la base de datos, crear algunos de ejemplo
        if (alimentoRepository.count() == 0) {
            Alimento[] alimentosEjemplo = {
                new Alimento("Palomitas Grandes", 12000.0),
                new Alimento("Palomitas Medianas", 8000.0),
                new Alimento("Palomitas Pequeñas", 5000.0),
                new Alimento("Gaseosa Grande", 8000.0),
                new Alimento("Gaseosa Mediana", 6000.0),
                new Alimento("Agua", 3000.0),
                new Alimento("Nachos con Queso", 15000.0),
                new Alimento("Hot Dog", 12000.0),
                new Alimento("Hamburguesa", 18000.0),
                new Alimento("Combo Palomitas + Gaseosa", 18000.0),
                new Alimento("Dulces Variados", 4000.0),
                new Alimento("Chocolate", 5000.0),
                new Alimento("Café", 4000.0),
                new Alimento("Té Helado", 5000.0),
                new Alimento("Pizza Personal", 16000.0)
            };
            
            for (Alimento alimento : alimentosEjemplo) {
                alimentoRepository.save(alimento);
            }
        }
    }

    public List<Alimento> obtenerTodosLosAlimentos() {
        return alimentoRepository.findAllByOrderByNombreAsc();
    }

    public Optional<Alimento> obtenerAlimentoPorId(Long id) {
        return alimentoRepository.findById(id);
    }

    public List<Alimento> buscarAlimentosPorNombre(String nombre) {
        return alimentoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    public List<Alimento> obtenerAlimentosPorRangoPrecio(Double precioMin, Double precioMax) {
        return alimentoRepository.findByPrecioBetween(precioMin, precioMax);
    }

    public Alimento guardarAlimento(Alimento alimento) {
        return alimentoRepository.save(alimento);
    }

    public void eliminarAlimento(Long id) {
        alimentoRepository.deleteById(id);
    }
}
