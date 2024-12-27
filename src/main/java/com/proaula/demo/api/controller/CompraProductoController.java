package com.proaula.demo.api.controller;

import com.proaula.demo.domain.dto.CompraProductoDTO;
import com.proaula.demo.domain.response.ValidateCompraProductoDTO;
import com.proaula.demo.domain.service.CompraProductoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class CompraProductoController {

    @Autowired
    private CompraProductoServices compraProductoServices;

    @PostMapping("compraProducto")
    public ValidateCompraProductoDTO agregarCompra(@RequestBody CompraProductoDTO compraProductoDTO) {
        return compraProductoServices.agregarCompra(compraProductoDTO);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<ValidateCompraProductoDTO> obtenerComprasPorUsuario(@PathVariable Long usuarioId) {
        
        ValidateCompraProductoDTO response = compraProductoServices.obtenerPorUsuario(usuarioId);
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/compraProductos")
    public ResponseEntity<ValidateCompraProductoDTO> obtenerProductos() {
        ValidateCompraProductoDTO response = compraProductoServices.obtenerCompraProductos();

        return ResponseEntity.ok(response);
    }
}
