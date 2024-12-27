package com.proaula.demo.api.controller;

import com.proaula.demo.domain.response.ValidateChatDTO;
import com.proaula.demo.domain.service.ChatServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/chat")
public class ChatController {

    @Autowired
    private ChatServices chatServices;

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<ValidateChatDTO> obtenerChatsDeUsuario(@PathVariable Long idUsuario) {
        ValidateChatDTO chatValidado = chatServices.obtenerChatsDeUsuario(idUsuario);

        if (!chatValidado.isEsValido()) {
            // If no chats were found, return 404 (Not Found)
            if (chatValidado.getMensaje().equals("No se encontraron chats para este usuario.")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(chatValidado);
            } // If there's an internal error or other issues, return 500 (Internal Server Error)
            else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(chatValidado);
            }
        }

        // If chats are found, return 200 OK
        return ResponseEntity.ok(chatValidado);
    }

    // Obtener los mensajes de un chat específico
    @GetMapping("/mensaje/{idChat}")
    public ResponseEntity<ValidateChatDTO> obtenerMensajesDeChat(@PathVariable Long idChat) {
        ValidateChatDTO chatValidado = chatServices.obtenerMensajesDeChat(idChat);

        if (chatValidado.isEsValido()) {
            return ResponseEntity.ok(chatValidado);
        } else {
            return ResponseEntity.badRequest().body(chatValidado);
        }
    }

    // Agregar un mensaje a un chat específico
    @PostMapping("/mensaje/{idChat}/{idUsuario}")
    public ResponseEntity<ValidateChatDTO> agregarMensaje(
            @PathVariable Long idChat,
            @PathVariable Long idUsuario,
            @RequestBody String texto
    ) {
        ValidateChatDTO chatValidado = chatServices.agregarMensaje(idChat, idUsuario, texto);

        if (chatValidado.isEsValido()) {
            return ResponseEntity.ok(chatValidado);
        } else {
            return ResponseEntity.badRequest().body(chatValidado);
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<ValidateChatDTO> crearChatPorUsuarios(
            @RequestParam Long idUsuario,
            @RequestParam Long idUsuarioOtro
    ) {
        ValidateChatDTO chatValidado = chatServices.crearChatPorUsuarios(idUsuario, idUsuarioOtro);

        if (chatValidado.isEsValido()) {
            return ResponseEntity.ok(chatValidado);
        } else if (chatValidado.isEsValido() && chatValidado.isYaExisteChat()) {
            return ResponseEntity.ok(chatValidado);
        } else {
            return ResponseEntity.badRequest().body(chatValidado);
        }
    }
}
