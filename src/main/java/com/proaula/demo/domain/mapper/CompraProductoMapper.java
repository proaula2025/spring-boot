package com.proaula.demo.domain.mapper;

import com.proaula.demo.domain.dto.CompraProductoDTO;
import com.proaula.demo.persistence.entity.CompraProducto;

public class CompraProductoMapper {

    public static CompraProductoDTO toDTO(CompraProducto compraProducto) {
        return new CompraProductoDTO(
                compraProducto.getIdCompra(),
                ProductoMapper.toDTO(compraProducto.getProducto()),
                compraProducto.getCantidadComprada(),
                compraProducto.getTotalPrecio(),
                compraProducto.getFechaCompra(),
                compraProducto.getUsuarioCompro(),
                compraProducto.getUsuarioVendio()
        );
    }

    public static CompraProducto toEntity(CompraProductoDTO compraProductoDTO) {
        CompraProducto compraProducto = new CompraProducto();
        compraProducto.setIdCompra(compraProductoDTO.getIdCompra());
        compraProducto.setProducto(ProductoMapper.toEntity(compraProductoDTO.getProducto()));
        compraProducto.setCantidadComprada(compraProductoDTO.getCantidadComprada());
        compraProducto.setTotalPrecio(compraProductoDTO.getTotalPrecio());
        compraProducto.setFechaCompra(compraProductoDTO.getFechaCompra());
        compraProducto.setUsuarioCompro(compraProductoDTO.getUsuarioCompro());
        compraProducto.setUsuarioVendio(compraProductoDTO.getUsuarioVendio());

        return compraProducto;
    }
}
