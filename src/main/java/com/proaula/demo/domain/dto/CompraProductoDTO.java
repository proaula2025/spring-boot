package com.proaula.demo.domain.dto;

import com.proaula.demo.persistence.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompraProductoDTO {

    private Long idCompra;
    private ProductoDTO producto;
    private int cantidadComprada;
    private double totalPrecio;
    private String fechaCompra;
    private Usuario usuarioCompro;
    private Usuario usuarioVendio;
}
