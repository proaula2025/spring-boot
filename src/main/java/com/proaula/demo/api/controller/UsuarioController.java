package com.proaula.demo.api.controller;

import com.proaula.demo.domain.dto.UsuarioDTO;
import com.proaula.demo.domain.response.ValidateUsuarioDTO;
import com.proaula.demo.domain.service.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api")
public class UsuarioController {

    @Autowired
    private UsuarioServices usuarioServices;

    @PostMapping("/registrar")
    public ResponseEntity<ValidateUsuarioDTO> registrarUsuario(
            @RequestPart("usuario") UsuarioDTO usuarioDTO,
            @RequestParam(value = "file") MultipartFile file) {

        ValidateUsuarioDTO usuValidado = usuarioServices.registrarUsuario(usuarioDTO, file);

        if (usuValidado.isEsValido()) {
            return ResponseEntity.ok(usuValidado);
        } else {
            return ResponseEntity.badRequest().body(usuValidado);
        }
    }

    @PostMapping("/iniciar")
    public ResponseEntity<ValidateUsuarioDTO> iniciarSesion(@RequestBody UsuarioDTO usuarioDTO) {
        ValidateUsuarioDTO usuValidado = usuarioServices.inicioUsuario(
                usuarioDTO.getEmail(),
                usuarioDTO.getPassword(),
                usuarioDTO.getTipoEntidad()
        );

        if (usuValidado.isEsValido()) {
            return ResponseEntity.ok(usuValidado);
        } else {
            return ResponseEntity.badRequest().body(usuValidado);
        }
    }

    @GetMapping("/lista-usuarios")
    public ResponseEntity<ValidateUsuarioDTO> obtenerUsuarios(@RequestParam String tipoEntidad) {
        ValidateUsuarioDTO listUsuValidado = usuarioServices.obtenerUsuariosPorTipo(tipoEntidad);

        return ResponseEntity.ok(listUsuValidado);
    }

    @GetMapping("/todos-usuarios")
    public ResponseEntity<ValidateUsuarioDTO> obtenerTodosUsuarios() {
        ValidateUsuarioDTO listUsuValidado = usuarioServices.obtenerUsuarios();

        return ResponseEntity.ok(listUsuValidado);
    }

    @PutMapping("/usuario")
    public ResponseEntity<ValidateUsuarioDTO> actualizarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        ValidateUsuarioDTO usuValidado = usuarioServices.actualizarUsuario(usuarioDTO);

        if (usuValidado.isEsValido()) {
            return ResponseEntity.ok(usuValidado);
        } else {
            return ResponseEntity.badRequest().body(usuValidado);
        }

    }

    @GetMapping("/usuario/{idUsuario}/{nombreCompleto}")
    public ResponseEntity<ValidateUsuarioDTO> obtenerUsuarioPorIdYNombreCompleto(
            @PathVariable Long idUsuario,
            @PathVariable String nombreCompleto) {

        ValidateUsuarioDTO usuarioValidado = usuarioServices.obtenerUsuarioPorIdYNombreCompleto(idUsuario, nombreCompleto);

        return ResponseEntity.ok(usuarioValidado);
    }

}
