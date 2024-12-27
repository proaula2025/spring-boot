package com.proaula.demo.domain.response;

import com.proaula.demo.domain.dto.ProductoDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ValidateProductoDTO {
    
    private String mensaje;
    private boolean esValido;
    private ProductoDTO productoDTO;
    private List<ProductoDTO> listaproductos;
}
