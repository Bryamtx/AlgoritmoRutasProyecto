package com.Uptc.Proyecto20.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "asientos")
public class Asiento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "numero")
    private String numero;
    
    @Column(name = "fila")
    private String fila;
    
    @Column(name = "ocupado")
    private boolean ocupado= false;
    
    // Constructor vacío
    public Asiento() {}
    
    // Constructor con parámetros
    public Asiento(String numero, String fila, boolean ocupado) {
        this.numero = numero;
        this.fila = fila;
        this.ocupado = ocupado;
    }
    
    // Getters y Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNumero() {
        return numero;
    }
    
    public void setNumero(String numero) {
        this.numero = numero;
    }
    
    public String getFila() {
        return fila;
    }
    
    public void setFila(String fila) {
        this.fila = fila;
    }
    
    public boolean isOcupado() {
        return ocupado;
    }
    
    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }
    
    @Override
    public String toString() {
        return "Asiento{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", fila='" + fila + '\'' +
                ", ocupado=" + ocupado +
                '}';
    }
}