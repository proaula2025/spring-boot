package com.proaula.demo.persistence.repository;

import com.proaula.demo.persistence.entity.CompraProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompraProductoRepository extends JpaRepository<CompraProducto, Long> {
    List<CompraProducto> findByUsuarioComproIdUsuario(Long usuarioId);

    List<CompraProducto> findByUsuarioVendioIdUsuario(Long usuarioId);
}
