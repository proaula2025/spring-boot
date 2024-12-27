package com.proaula.demo.api.controller;

import com.proaula.demo.domain.dto.ProductoDTO;
import com.proaula.demo.domain.response.ValidateProductoDTO;
import com.proaula.demo.domain.service.ProductoServices;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api")
public class ProductoController {

    @Autowired
    private ProductoServices productoServices;

    @PostMapping("/producto")
    public ResponseEntity<ValidateProductoDTO> registrarProducto(
            @RequestPart("producto") ProductoDTO productoDTO,
            @RequestParam(value = "file") MultipartFile file) {
        ValidateProductoDTO productoValidado = productoServices.registrarProducto(productoDTO, file);

        if (productoValidado.isEsValido()) {
            return ResponseEntity.ok(productoValidado);
        } else {
            return ResponseEntity.badRequest().body(productoValidado);
        }
    }

    @GetMapping("/productos")
    public ResponseEntity<ValidateProductoDTO> obtenerProductos() {
        ValidateProductoDTO productosValidado = productoServices.obtenerProductos();

        if (productosValidado.isEsValido()) {
            return ResponseEntity.ok(productosValidado);
        } else {
            return ResponseEntity.badRequest().body(productosValidado);
        }
    }

    @GetMapping("/productos/categoria")
    public ResponseEntity<ValidateProductoDTO> obtenerProductosPorTiposProducto(@RequestParam List<String> categorias) {
        ValidateProductoDTO productosValidado = productoServices.obtenerProductosPorTiposProducto(categorias);

        if (productosValidado.isEsValido()) {
            return ResponseEntity.ok(productosValidado);
        } else {
            return ResponseEntity.badRequest().body(productosValidado);
        }
    }

    @GetMapping("/productos/usuario/{idUsuario}")
    public ResponseEntity<ValidateProductoDTO> obtenerProductosUsuario(@PathVariable Long idUsuario) {
        ValidateProductoDTO productosValidado = productoServices.obtenerProductosUsuario(idUsuario);

        if (productosValidado.isEsValido()) {
            return ResponseEntity.ok(productosValidado);
        } else {
            return ResponseEntity.badRequest().body(productosValidado);
        }
    }

    @PutMapping("/producto")
    public ResponseEntity<ValidateProductoDTO> actualizarProducto(@RequestBody ProductoDTO producto) {

        ValidateProductoDTO productoValidado = productoServices.actualizarProducto(producto);

        if (productoValidado.isEsValido()) {
            return ResponseEntity.ok(productoValidado);
        } else {
            return ResponseEntity.badRequest().body(productoValidado);
        }
    }

    @DeleteMapping("/producto/{idProducto}")
    public ResponseEntity<ValidateProductoDTO> eliminarProducto(@PathVariable Long idProducto) {
        ValidateProductoDTO productoValidado = productoServices.eliminarProducto(idProducto);

        if (productoValidado.isEsValido()) {
            return ResponseEntity.ok(productoValidado);
        } else {
            return ResponseEntity.badRequest().body(productoValidado);
        }
    }
}
