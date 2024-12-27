package com.proaula.demo.domain.mapper;

import com.proaula.demo.domain.dto.ProductoDTO;
import com.proaula.demo.persistence.entity.Producto;

public class ProductoMapper {

    public static ProductoDTO toDTO(Producto producto) {

        ProductoDTO productoDTO = new ProductoDTO();
        
        productoDTO.setIdProducto(producto.getIdProducto());
        productoDTO.setCategoria(producto.getCategoria());
        productoDTO.setEstado(producto.getEstado());
        productoDTO.setNombreProducto(producto.getNombreProducto());
        productoDTO.setCantidad(producto.getCantidad());
        productoDTO.setPrecio(producto.getPrecio());
        productoDTO.setFecha(producto.getFecha());
        productoDTO.setDireccionRecogida(producto.getDireccionRecogida());
        productoDTO.setDescripcion(producto.getDescripcion());
        productoDTO.setTipoProducto(producto.getTipoProducto());
        productoDTO.setDisponibilidad(producto.isDisponibilidad());
        productoDTO.setUsuario(producto.getUsuario());
        productoDTO.setEmpresaDono(producto.getEmpresaDono());
        productoDTO.setImageUrl(producto.getImagenUrl());
        productoDTO.setFundacionRecogioProducto(producto.isFundacionRecogioProducto());

        return productoDTO;
    }

    public static Producto toEntity(ProductoDTO dto) {
        Producto producto = new Producto();
        
        producto.setIdProducto(dto.getIdProducto());
        producto.setCategoria(dto.getCategoria());
        producto.setEstado(dto.getEstado()); 
        producto.setNombreProducto(dto.getNombreProducto());
        producto.setCantidad(dto.getCantidad());
        producto.setPrecio(dto.getPrecio());
        producto.setFecha(dto.getFecha());
        producto.setDireccionRecogida(dto.getDireccionRecogida());
        producto.setDescripcion(dto.getDescripcion());
        producto.setTipoProducto(dto.getTipoProducto());
        producto.setDisponibilidad(dto.isDisponibilidad());
        producto.setUsuario(dto.getUsuario());
        producto.setImagenUrl(dto.getImageUrl());
        producto.setEmpresaDono(dto.getEmpresaDono());
        producto.setFundacionRecogioProducto(dto.isFundacionRecogioProducto());

        return producto;
    }
}
