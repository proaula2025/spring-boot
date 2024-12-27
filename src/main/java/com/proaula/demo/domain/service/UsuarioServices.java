package com.proaula.demo.domain.service;

import com.proaula.demo.domain.common.MensajesError;
import com.proaula.demo.domain.dto.UsuarioDTO;
import com.proaula.demo.domain.response.ValidateUsuarioDTO;
import com.proaula.demo.domain.mapper.UsuarioMapper;
import com.proaula.demo.persistence.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proaula.demo.persistence.repository.UsuarioRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UsuarioServices {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ImagenServices imagenServices;

    public ValidateUsuarioDTO registrarUsuario(UsuarioDTO usuarioDTO, MultipartFile file) {
        ValidateUsuarioDTO validarUsuario = new ValidateUsuarioDTO();

        try {

            if (validarEmail(usuarioDTO.getEmail())) {
                validarUsuario.setMensaje(MensajesError.EMAIL_YA_EXISTE);
                validarUsuario.setEsValido(false);

                return validarUsuario;
            }
            
            String imagenUrl = imagenServices.agregarImagen(file);
            usuarioDTO.setImageUrl(imagenUrl);

            Usuario usuarioARegistrar = UsuarioMapper.toEntityUsuario(usuarioDTO);

            Usuario usuRegistrado = usuarioRepository.save(usuarioARegistrar);

            if (usuRegistrado != null) {
                validarUsuario.setMensaje(MensajesError.REGISTRO_CORRECTO);
                validarUsuario.setEsValido(true);
                validarUsuario.setUsuario(UsuarioMapper.toDtoUsuario(usuRegistrado));
            } else {
                validarUsuario.setMensaje(MensajesError.ERROR_BD_REGISTRAR);
                validarUsuario.setEsValido(false);
            }
        } catch (Exception e) {
            System.out.println(MensajesError.ERROR_BD_REGISTRAR + e);
        }

        return validarUsuario;

    }

    public ValidateUsuarioDTO inicioUsuario(String email, String password, String tipoEntidad) {
        ValidateUsuarioDTO validarUsuario = new ValidateUsuarioDTO();

        try {
            Usuario usuario = usuarioRepository.findByEmailAndPasswordAndTipoEntidad(email, password, tipoEntidad);
            if (usuario != null) {
                validarUsuario.setMensaje(MensajesError.INICIO_SESION_EXITOSO);
                validarUsuario.setEsValido(true);
                validarUsuario.setUsuario(UsuarioMapper.toDtoUsuario(usuario));
            } else {
                validarUsuario.setMensaje(MensajesError.CREDENCIALES_INVALIDAS);
                validarUsuario.setEsValido(false);
            }

        } catch (Exception e) {
            System.out.println(MensajesError.CREDENCIALES_INVALIDAS + e);
        }

        return validarUsuario;

    }

    public ValidateUsuarioDTO actualizarUsuario(UsuarioDTO usuarioDTO) {
        ValidateUsuarioDTO validarUsuario = new ValidateUsuarioDTO();

        Optional<Usuario> usuarioExistente = usuarioRepository.findById(usuarioDTO.getIdUsuario());

        if (!usuarioExistente.isPresent()) {
            validarUsuario.setMensaje(MensajesError.USUARIO_NO_ENCONTRADO);
            validarUsuario.setEsValido(false);
            return validarUsuario;
        }

        try {
            Usuario usuario = usuarioExistente.get();

            // Actualizar los campos del usuario con los datos del DTO
            usuario.setProductosDonadosEmpresa(usuarioDTO.getProductosDonadosEmpresa());
            usuario.setProductosRecibidosFundacion(usuarioDTO.getProductosRecibidosFundacion());
            usuario.setProductosComprados(usuarioDTO.getProductosComprados());
            usuario.setProductosIntercambiados(usuarioDTO.getProductosIntercambiados());
            usuario.setProductosVendidos(usuarioDTO.getProductosVendidos());

            // Guardar el usuario actualizado en la base de datos
            Usuario usuarioActualizado = usuarioRepository.save(usuario);

            validarUsuario.setMensaje(MensajesError.ACTUALIZACION_CORRECTA);
            validarUsuario.setEsValido(true);
            validarUsuario.setUsuario(UsuarioMapper.toDtoUsuario(usuarioActualizado));

        } catch (Exception e) {
            System.out.println(MensajesError.CREDENCIALES_INVALIDAS + e);
        }

        return validarUsuario;
    }

    public ValidateUsuarioDTO obtenerUsuariosPorTipo(String tipoEntidad) {
        ValidateUsuarioDTO validarUsuario = new ValidateUsuarioDTO();

        try {
            List<UsuarioDTO> listaUsuarios = usuarioRepository.findByTipoEntidad(tipoEntidad)
                    .stream()
                    .map(UsuarioMapper::toDtoUsuario)
                    .collect(Collectors.toList());

            if (listaUsuarios.isEmpty()) {
                validarUsuario.setMensaje(MensajesError.USUARIOS_NO_ENCONTRADOS);
                validarUsuario.setEsValido(false);
            } else {
                validarUsuario.setMensaje(MensajesError.CONSULTA_EXITOSA);
                validarUsuario.setEsValido(true);
                validarUsuario.setListaUsuario(listaUsuarios);
            }
        } catch (Exception e) {
            System.out.println(MensajesError.ERROR_CONSULTA + e);
        }

        return validarUsuario;
    }

    public ValidateUsuarioDTO obtenerUsuarios() {
        ValidateUsuarioDTO validarUsuario = new ValidateUsuarioDTO();

        try {
            List<UsuarioDTO> listaUsuarios = usuarioRepository.findAll()
                    .stream()
                    .map(UsuarioMapper::toDtoUsuario)
                    .collect(Collectors.toList());

            if (listaUsuarios.isEmpty()) {
                validarUsuario.setMensaje(MensajesError.USUARIOS_NO_ENCONTRADOS);
                validarUsuario.setEsValido(false);
            } else {
                validarUsuario.setMensaje(MensajesError.CONSULTA_EXITOSA);
                validarUsuario.setEsValido(true);
                validarUsuario.setListaUsuario(listaUsuarios);
            }
        } catch (Exception e) {
            System.out.println(MensajesError.ERROR_CONSULTA + e);
        }

        return validarUsuario;
    }

    public ValidateUsuarioDTO obtenerUsuarioPorIdYNombreCompleto(Long idUsuario, String nombreCompleto) {
        ValidateUsuarioDTO validarUsuario = new ValidateUsuarioDTO();

        try {
            Usuario usuario = usuarioRepository.findByIdUsuarioAndNombreCompleto(idUsuario, nombreCompleto);

            if (usuario != null) {
                validarUsuario.setMensaje(MensajesError.CONSULTA_EXITOSA);
                validarUsuario.setEsValido(true);
                validarUsuario.setUsuario(UsuarioMapper.toDtoUsuario(usuario));
            } else {
                validarUsuario.setMensaje(MensajesError.USUARIO_NO_ENCONTRADO);
                validarUsuario.setEsValido(false);
            }
        } catch (Exception e) {
            System.out.println(MensajesError.ERROR_CONSULTA + e);
        }

        return validarUsuario;
    }

    public boolean validarEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

}
