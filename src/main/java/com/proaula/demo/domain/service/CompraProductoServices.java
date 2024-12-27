package com.proaula.demo.domain.service;

import com.proaula.demo.domain.common.MensajesError;
import com.proaula.demo.domain.dto.CompraProductoDTO;
import com.proaula.demo.domain.response.ValidateCompraProductoDTO;
import com.proaula.demo.domain.mapper.CompraProductoMapper;
import com.proaula.demo.domain.mapper.ProductoMapper;
import com.proaula.demo.persistence.entity.CompraProducto;
import com.proaula.demo.persistence.entity.Producto;
import com.proaula.demo.persistence.entity.Usuario;
import com.proaula.demo.persistence.repository.CompraProductoRepository;
import com.proaula.demo.persistence.repository.ProductoRepository;
import com.proaula.demo.persistence.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompraProductoServices {

    @Autowired
    private CompraProductoRepository compraProductoRepository;

    @Autowired
    private ProductoRepository productoRepository; // Inyección del repositorio de Producto

    @Autowired
    private UsuarioRepository usuarioRepository; // Inyección del repositorio de Usuario

    public ValidateCompraProductoDTO agregarCompra(CompraProductoDTO compraProductoDTO) {
        try {
            // Buscar producto por ID
            Optional<Producto> optionalProducto = productoRepository.findById(compraProductoDTO.getProducto().getIdProducto());
            if (optionalProducto.isEmpty()) {
                return new ValidateCompraProductoDTO("Producto no encontrado", false, null, null);
            }

            // Buscar usuario por ID
            Optional<Usuario> optionalUsuarioCompro = usuarioRepository.findById(compraProductoDTO.getUsuarioCompro().getIdUsuario());
            if (optionalUsuarioCompro.isEmpty()) {
                return new ValidateCompraProductoDTO("Usuario no encontrado", false, null, null);
            }

            Optional<Usuario> optionalUsuarioVendio = usuarioRepository.findById(compraProductoDTO.getUsuarioVendio().getIdUsuario());
            if (optionalUsuarioVendio.isEmpty()) {
                return new ValidateCompraProductoDTO("Usuario no encontrado", false, null, null);
            }

            Producto producto = optionalProducto.get();
            Usuario usuarioCompro = optionalUsuarioCompro.get();
            Usuario usuarioVendio = optionalUsuarioVendio.get();

            compraProductoDTO.setProducto(ProductoMapper.toDTO(producto));
            compraProductoDTO.setUsuarioCompro(usuarioCompro);
            compraProductoDTO.setUsuarioVendio(usuarioVendio);
            // Crear y guardar la compra
            CompraProducto compraProducto = CompraProductoMapper.toEntity(compraProductoDTO);
            CompraProducto savedCompra = compraProductoRepository.save(compraProducto);

            return new ValidateCompraProductoDTO("Compra registrada exitosamente", true,
                    CompraProductoMapper.toDTO(savedCompra), null);
        } catch (Exception e) {
            return new ValidateCompraProductoDTO("Error al registrar la compra: " + e.getMessage(), false, null, null);
        }
    }

    public ValidateCompraProductoDTO obtenerPorUsuario(Long usuarioId) {

        ValidateCompraProductoDTO valiProdu = new ValidateCompraProductoDTO();

        try {
            List<CompraProductoDTO> listaProductos = compraProductoRepository.findByUsuarioComproIdUsuario(usuarioId).stream()
                    .map(CompraProductoMapper::toDTO)
                    .collect(Collectors.toList());

            if (listaProductos.isEmpty()) {
                valiProdu.setMensaje(MensajesError.PRODUCTOS_NO_ENCONTRADOS);
                valiProdu.setEsValido(false);
            } else {
                valiProdu.setMensaje(MensajesError.CONSULTA_PRODUCTO_EXITOSA);
                valiProdu.setEsValido(true);
                valiProdu.setListaCompraProductos(listaProductos);
            }
        } catch (Exception e) {
            valiProdu.setMensaje(MensajesError.ERROR_CONSULTA_PRODUCTO);
            valiProdu.setEsValido(false);
            System.out.println("Error while fetching products: " + e);
        }

        return valiProdu;
    }

    public ValidateCompraProductoDTO obtenerCompraProductos() {
        ValidateCompraProductoDTO valiProdu = new ValidateCompraProductoDTO();

        try {
            List<CompraProductoDTO> listaProductos = compraProductoRepository.findAll()
                    .stream()
                    .map(CompraProductoMapper::toDTO)
                    .collect(Collectors.toList());

            if (listaProductos.isEmpty()) {
                valiProdu.setMensaje(MensajesError.PRODUCTOS_NO_ENCONTRADOS);
                valiProdu.setEsValido(false);
            } else {
                valiProdu.setMensaje(MensajesError.CONSULTA_PRODUCTO_EXITOSA);
                valiProdu.setEsValido(true);
                valiProdu.setListaCompraProductos(listaProductos);
            }
        } catch (Exception e) {
            valiProdu.setMensaje(MensajesError.ERROR_CONSULTA_PRODUCTO);
            valiProdu.setEsValido(false);
            System.out.println("Error while fetching products: " + e);
        }

        return valiProdu;
    }

}
