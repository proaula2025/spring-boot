package com.proaula.demo.persistence.repository;

import com.proaula.demo.persistence.entity.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsByEmail(String email);

    Usuario findByEmailAndPasswordAndTipoEntidad(String email, String password, String tipoEntidad);

    List<Usuario> findByTipoEntidad(String tipoEntidad);

    Usuario findByIdUsuarioAndNombreCompleto(Long idUsuario, String nombreCompleto);

}
