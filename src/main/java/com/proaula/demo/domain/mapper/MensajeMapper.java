package com.proaula.demo.domain.mapper;

import com.proaula.demo.domain.dto.MensajeDTO;
import com.proaula.demo.persistence.entity.Chat;
import com.proaula.demo.persistence.entity.Mensaje;

public class MensajeMapper {

    // Convertir Mensaje a MensajeDTO
    public static MensajeDTO toDTO(Mensaje mensaje) {
        if (mensaje == null) {
            return null;
        }

        return new MensajeDTO(
                mensaje.getIdMensaje(),
                mensaje.getChat().getIdChat(),
                UsuarioMapper.toDtoUsuario(mensaje.getUsuario()), // Mapeo de Usuario
                mensaje.getTexto(),
                mensaje.getFecha()
        );
    }

    // Convertir MensajeDTO a Mensaje
    public static Mensaje toEntity(MensajeDTO mensajeDTO) {
        if (mensajeDTO == null) {
            return null;
        }

        // Aquí se supone que se pasa el chat correspondiente, si es necesario se ajusta.
        return new Mensaje(
                mensajeDTO.getIdMensaje(),
                new Chat(mensajeDTO.getIdChat(), null, null, null, false, false), // Chat vacío
                UsuarioMapper.toEntityUsuario(mensajeDTO.getUsuario()), // Mapeo de Usuario
                mensajeDTO.getTexto(),
                mensajeDTO.getFecha()
        );
    }
}
