package com.proaula.demo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatDTO {

    private Long idChat;
    private UsuarioDTO usuario1;
    private UsuarioDTO usuario2;
    private boolean usuario1VioUltimoMensaje;
    private boolean usuario2VioUltimoMensaje;
    private List<MensajeDTO> mensajes;

}
