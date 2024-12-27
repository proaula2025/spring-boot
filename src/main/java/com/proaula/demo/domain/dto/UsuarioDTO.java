package com.proaula.demo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    
    private Long idUsuario;
    private String nombreCompleto;
    private String tipoDocumento; 
    private String email; 
    private String password; 
    private Long numeroDocumento;
    private Long telefono;
    private String tipoEntidad;
    private int productosDonadosEmpresa;
    private int productosRecibidosFundacion;
    private int productosVendidos;
    private int productosIntercambiados;
    private int productosComprados;
    private String imageUrl;
    
}
