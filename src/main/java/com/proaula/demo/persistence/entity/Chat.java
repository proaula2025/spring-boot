package com.proaula.demo.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idChat;

    @ManyToOne
    @JoinColumn(name = "id_usuario1")
    private Usuario usuario1;

    @ManyToOne
    @JoinColumn(name = "id_usuario2")
    private Usuario usuario2;

    @OneToMany(mappedBy = "chat")
    private List<Mensaje> mensajes;

    private boolean usuario1VioUltimoMensaje;
    private boolean usuario2VioUltimoMensaje;
}
