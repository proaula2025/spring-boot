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
@Table(name = "CompraProductos")
public class CompraProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCompra;
    private int cantidadComprada;
    private double totalPrecio;
    private String fechaCompra;

    @ManyToOne
    private Usuario usuarioCompro;

    @ManyToOne
    private Usuario usuarioVendio;

    @ManyToOne
    private Producto producto;

}
