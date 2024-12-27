package com.proaula.demo.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Productos")
public class Producto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;

    private String categoria;
    private String estado;
    private String nombreProducto;
    private int cantidad;
    private double precio;
    private String direccionRecogida;
    private String fecha;
    private String descripcion;
    private String tipoProducto;
    private boolean disponibilidad;
    private boolean fundacionRecogioProducto;
    private String imagenUrl;
    
    @ManyToOne
    private Usuario usuario;
    
    @ManyToOne
    private Usuario empresaDono;
}
