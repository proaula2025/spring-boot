package com.proaula.demo.persistence.repository;

import com.proaula.demo.persistence.entity.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
}
