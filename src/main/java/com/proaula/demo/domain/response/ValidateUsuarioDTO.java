package com.proaula.demo.domain.response;

import com.proaula.demo.domain.dto.UsuarioDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ValidateUsuarioDTO {
    private String mensaje;
    private boolean esValido;
    private UsuarioDTO usuario;
    private List<UsuarioDTO> listaUsuario;
}
