package com.proaula.demo.domain.mapper;

import com.proaula.demo.domain.dto.ChatDTO;
import com.proaula.demo.domain.dto.MensajeDTO;
import com.proaula.demo.persistence.entity.Chat;
import com.proaula.demo.persistence.entity.Mensaje;
import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;

public class ChatMapper {

    public static ChatDTO toDTO(Chat chat) {

        // Asegúrate de que los mensajes no sean null
        List<MensajeDTO> mensajesDTO = new ArrayList<>();
        if (chat.getMensajes() != null && !chat.getMensajes().isEmpty()) {
            mensajesDTO = chat.getMensajes().stream()
                    .map(MensajeMapper::toDTO)
                    .collect(Collectors.toList());
        }

        return new ChatDTO(
                chat.getIdChat(),
                UsuarioMapper.toDtoUsuario(chat.getUsuario1()),
                UsuarioMapper.toDtoUsuario(chat.getUsuario2()),
                chat.isUsuario1VioUltimoMensaje(),
                chat.isUsuario2VioUltimoMensaje(),
                mensajesDTO
        );
    }

    public static Chat toEntity(ChatDTO chatDTO) {

        // Asegúrate de que los mensajes no sean null
        List<Mensaje> mensajes = new ArrayList<>();
        if (chatDTO.getMensajes() != null && !chatDTO.getMensajes().isEmpty()) {
            mensajes = chatDTO.getMensajes().stream()
                    .map(MensajeMapper::toEntity)
                    .collect(Collectors.toList());
        }

        return new Chat(
                chatDTO.getIdChat(),
                UsuarioMapper.toEntityUsuario(chatDTO.getUsuario1()),
                UsuarioMapper.toEntityUsuario(chatDTO.getUsuario2()),
                mensajes,
                chatDTO.isUsuario1VioUltimoMensaje(),
                chatDTO.isUsuario2VioUltimoMensaje()
        );
    }
}
