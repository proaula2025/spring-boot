package com.proaula.demo.domain.response;

import com.proaula.demo.domain.dto.ComentarioDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidateComentarioDTO {
    private String mensaje;
    private boolean esValido;
    private ComentarioDTO comentarioDTO;
    private List<ComentarioDTO> listaComentarios;
}
