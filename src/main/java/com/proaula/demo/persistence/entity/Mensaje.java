package com.proaula.demo.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mensaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMensaje;

    @ManyToOne
    @JoinColumn(name = "id_chat")
    private Chat chat;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    private String texto;
    private LocalDateTime fecha;
}
