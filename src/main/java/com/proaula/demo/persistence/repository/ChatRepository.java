package com.proaula.demo.persistence.repository;

import com.proaula.demo.persistence.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {

    List<Chat> findByUsuario1_IdUsuarioOrUsuario2_IdUsuario(Long idUsuario1, Long idUsuario2);

}
