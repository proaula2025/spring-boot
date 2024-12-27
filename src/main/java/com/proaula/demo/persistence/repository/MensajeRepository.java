package com.proaula.demo.persistence.repository;

import com.proaula.demo.persistence.entity.Mensaje;
import com.proaula.demo.persistence.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MensajeRepository extends JpaRepository<Mensaje, Long> {

    List<Mensaje> findByChat(Chat chat);
}
