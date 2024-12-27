package com.proaula.demo.persistence.repository;

import com.proaula.demo.persistence.entity.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    boolean existsByNombreProducto(String nombre);
    
    List<Producto> findByTipoProductoIn(List<String> categorias);
    
    @Query("SELECT p FROM Producto p JOIN FETCH p.usuario WHERE p.usuario.idUsuario = :idUsuario")
    List<Producto> findByUsuarioIdUsuario(Long idUsuario);

    List<Producto> findByEmpresaDonoIdUsuario(Long idUsuario);
    
    Producto findByIdProducto(Long idProducto);
}
