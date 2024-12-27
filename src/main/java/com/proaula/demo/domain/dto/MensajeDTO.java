package com.proaula.demo.domain.dto;

import com.proaula.demo.persistence.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MensajeDTO {

    private Long idMensaje;
    private Long idChat;
    private UsuarioDTO usuario;
    private String texto;
    private LocalDateTime fecha;

}
