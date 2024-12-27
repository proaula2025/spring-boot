package com.proaula.demo.domain.response;

import com.proaula.demo.domain.dto.ChatDTO;
import com.proaula.demo.domain.dto.MensajeDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidateChatDTO {

    private String mensaje;
    private boolean esValido;
    private boolean yaExisteChat;
    private ChatDTO chatDTO;
    private List<ChatDTO> chatsDTO;
    private MensajeDTO mensajeDTO;
    private List<MensajeDTO> mensajesDTO;

}
