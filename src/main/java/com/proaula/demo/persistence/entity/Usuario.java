package com.proaula.demo.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Usuario {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
