package com.proaula.demo.api.controller;

import com.proaula.demo.domain.dto.ComentarioDTO;
import com.proaula.demo.domain.response.ValidateComentarioDTO;
import com.proaula.demo.domain.service.ComentarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class ComentarioController {

    @Autowired
    private ComentarioServices comentarioServices;

    @PostMapping("/comentario")
    public ResponseEntity<ValidateComentarioDTO> registrarComentario(@RequestBody ComentarioDTO comentarioDTO) {
        ValidateComentarioDTO response = comentarioServices.registrarComentario(comentarioDTO);
        if (response.isEsValido()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/comentarios")
    public ResponseEntity<ValidateComentarioDTO> obtenerComentariosDeXProducto() {
        ValidateComentarioDTO response = comentarioServices.obtenerComentariosDeXProducto();
        
        return ResponseEntity.ok(response);
    }
}
