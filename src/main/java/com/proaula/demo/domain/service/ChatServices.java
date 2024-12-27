package com.proaula.demo.domain.service;

import com.proaula.demo.domain.dto.ChatDTO;
import com.proaula.demo.domain.dto.MensajeDTO;
import com.proaula.demo.domain.mapper.ChatMapper;
import com.proaula.demo.domain.mapper.MensajeMapper;
import com.proaula.demo.domain.response.ValidateChatDTO;
import com.proaula.demo.persistence.entity.Chat;
import com.proaula.demo.persistence.entity.Mensaje;
import com.proaula.demo.persistence.entity.Usuario;
import com.proaula.demo.persistence.repository.ChatRepository;
import com.proaula.demo.persistence.repository.MensajeRepository;
import com.proaula.demo.persistence.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChatServices {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private MensajeRepository mensajeRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Obtener todos los chats en los que el usuario está involucrado
    public ValidateChatDTO obtenerChatsDeUsuario(Long idUsuario) {
        ValidateChatDTO validateChatDTO = new ValidateChatDTO();

        try {
            // Buscar todos los chats en los que el usuario está involucrado
            List<Chat> chats = chatRepository.findByUsuario1_IdUsuarioOrUsuario2_IdUsuario(idUsuario, idUsuario);

            if (chats.isEmpty()) {
                validateChatDTO.setMensaje("No se encontraron chats para este usuario.");
                validateChatDTO.setEsValido(false);
            } else {
                List<ChatDTO> chatDTOs = chats.stream()
                        .map(ChatMapper::toDTO)
                        .collect(Collectors.toList());
                validateChatDTO.setMensaje("Chats encontrados.");
                validateChatDTO.setEsValido(true);
                validateChatDTO.setChatsDTO(chatDTOs);
            }
        } catch (Exception e) {
            validateChatDTO.setMensaje("Error al obtener los chats.");
            validateChatDTO.setEsValido(false);
        }

        return validateChatDTO;
    }

    // Obtener todos los mensajes de un chat específico
    public ValidateChatDTO obtenerMensajesDeChat(Long idChat) {
        ValidateChatDTO validateChatDTO = new ValidateChatDTO();

        try {
            // Obtener el chat por id
            Chat chat = chatRepository.findById(idChat).orElse(null);
            if (chat == null) {
                validateChatDTO.setMensaje("Chat no encontrado.");
                validateChatDTO.setEsValido(false);
                return validateChatDTO;
            }

            // Obtener todos los mensajes del chat
            List<Mensaje> mensajes = mensajeRepository.findByChat(chat);

            List<MensajeDTO> mensajeDTOs = mensajes.stream()
                    .map(MensajeMapper::toDTO)
                    .collect(Collectors.toList());

            validateChatDTO.setMensaje("Mensajes encontrados.");
            validateChatDTO.setEsValido(true);
            validateChatDTO.setMensajesDTO(mensajeDTOs);
        } catch (Exception e) {
            validateChatDTO.setMensaje("Error al obtener los mensajes.");
            validateChatDTO.setEsValido(false);
        }

        return validateChatDTO;
    }

    // Agregar un mensaje a un chat
    public ValidateChatDTO agregarMensaje(Long idChat, Long idUsuario, String texto) {
        ValidateChatDTO validateChatDTO = new ValidateChatDTO();

        try {

            if (texto.startsWith("{") && texto.contains("\"texto\":")) {
                texto = texto.replaceAll("^.*\"texto\":\"|\"\\}$", ""); // Elimina el JSON envolvente
            }

            // Obtener el chat por id
            Chat chat = chatRepository.findById(idChat).orElse(null);
            if (chat == null) {
                validateChatDTO.setMensaje("Chat no encontrado.");
                validateChatDTO.setEsValido(false);
                return validateChatDTO;
            }

            // Crear un nuevo mensaje
            Mensaje mensaje = new Mensaje();
            mensaje.setChat(chat);

            // Crear el usuario y asignar solo el ID
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(idUsuario);  // Usar el setter para asignar el ID
            mensaje.setUsuario(usuario);

            mensaje.setTexto(texto);
            mensaje.setFecha(LocalDateTime.now());

            Mensaje saveMensaje = mensajeRepository.save(mensaje);

            validateChatDTO.setMensaje("Mensaje agregado.");
            validateChatDTO.setEsValido(true);
            validateChatDTO.setMensajeDTO(MensajeMapper.toDTO(saveMensaje));
        } catch (Exception e) {
            System.out.println(e);
            validateChatDTO.setMensaje("Error al agregar el mensaje.");
            validateChatDTO.setEsValido(false);
        }

        return validateChatDTO;
    }

    public ValidateChatDTO crearChatPorUsuarios(Long idUsuario, Long idUsuarioOtro) {
        ValidateChatDTO validateChatDTO = new ValidateChatDTO();

        try {
            // Verificar si ya existe un chat entre los dos usuarios
            List<Chat> existingChats = chatRepository.findAll().stream()
                    .filter(chat
                            -> (chat.getUsuario1().getIdUsuario().equals(idUsuario) && chat.getUsuario2().getIdUsuario().equals(idUsuarioOtro))
                    || (chat.getUsuario1().getIdUsuario().equals(idUsuarioOtro) && chat.getUsuario2().getIdUsuario().equals(idUsuario))
                    )
                    .collect(Collectors.toList());

            if (!existingChats.isEmpty()) {
                Chat existingChat = existingChats.get(0); // Tomar el primer chat existente
                validateChatDTO.setMensaje("Ya existe un chat entre estos usuarios.");
                validateChatDTO.setEsValido(true);
                validateChatDTO.setYaExisteChat(true);
                validateChatDTO.setChatDTO(ChatMapper.toDTO(existingChat)); // Retornar el DTO del chat existente
                return validateChatDTO;
            }

            // Crear nuevo chat si no existe
            Optional<Usuario> usuario1Opt = usuarioRepository.findById(idUsuario);
            Optional<Usuario> usuario2Opt = usuarioRepository.findById(idUsuarioOtro);

            if (usuario1Opt.isPresent() && usuario2Opt.isPresent()) {
                Chat chat = new Chat();
                chat.setUsuario1(usuario1Opt.get());
                chat.setUsuario2(usuario2Opt.get());
                chat.setUsuario1VioUltimoMensaje(false);
                chat.setUsuario2VioUltimoMensaje(false);

                Chat newChat = chatRepository.save(chat);

                validateChatDTO.setMensaje("Chat creado exitosamente.");
                validateChatDTO.setEsValido(true);
                validateChatDTO.setChatDTO(ChatMapper.toDTO(newChat));
            } else {
                validateChatDTO.setMensaje("Uno o ambos usuarios no existen.");
                validateChatDTO.setEsValido(false);
            }
        } catch (Exception e) {
            validateChatDTO.setMensaje("Error al crear el chat: " + e.getMessage());
            validateChatDTO.setEsValido(false);
        }

        return validateChatDTO;
    }

}
