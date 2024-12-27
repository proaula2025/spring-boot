package com.proaula.demo.domain.mapper;

import com.proaula.demo.domain.dto.ComentarioDTO;
import com.proaula.demo.persistence.entity.Comentario;

public class ComentarioMapper {

    public static ComentarioDTO toDTO(Comentario comentario) {
        ComentarioDTO comentarioDTO = new ComentarioDTO();

        comentarioDTO.setIdCommentario(comentario.getIdCommentario());
        comentarioDTO.setComentario(comentario.getComentario());
        comentarioDTO.setValoracion(comentario.getValoracion());
        comentarioDTO.setUsuario(comentario.getUsuario());

        return comentarioDTO;
    }

    public static Comentario toEntity(ComentarioDTO dto) {
        Comentario comentario = new Comentario();

        comentario.setIdCommentario(dto.getIdCommentario());
        comentario.setComentario(dto.getComentario());
        comentario.setValoracion(dto.getValoracion());
        comentario.setUsuario(dto.getUsuario());

        return comentario;
    }

}
