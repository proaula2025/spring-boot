package com.proaula.demo.domain.dto;

import com.proaula.demo.persistence.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDTO {
    private Long idProducto;
    private String categoria;
    private String estado;
    private String nombreProducto;
    private int cantidad;
    private double precio;
    private String fecha;
    private String direccionRecogida;
    private String descripcion;
    private String tipoProducto;
    private boolean disponibilidad;
    private Usuario usuario;
    private Usuario empresaDono;
    private boolean fundacionRecogioProducto;
    private String imageUrl;
}
