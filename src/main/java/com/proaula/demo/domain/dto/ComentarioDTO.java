package com.proaula.demo.domain.dto;

import com.proaula.demo.persistence.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComentarioDTO {

    private int idCommentario;
    private String comentario;
    private double valoracion;
    private Usuario usuario;
}
