package com.proaula.demo.domain.service;

import com.proaula.demo.persistence.entity.Imagen;
import com.proaula.demo.persistence.repository.ImagenRepository;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImagenServices {

    @Autowired
    private ImagenRepository imagenRepository;

    public String agregarImagen(MultipartFile imagen) throws IOException {
        try {
            Imagen imagenGuardada = new Imagen();
            imagenGuardada.setNombre(imagen.getOriginalFilename());
            imagenGuardada.setContenido(imagen.getBytes());
            Imagen savedImagen = imagenRepository.save(imagenGuardada);

            return "http://localhost:9999/api/imagen/" + savedImagen.getId();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }

    public byte[] obtenerImagenPerfil(Long imagenId) {
        return imagenRepository.findById(imagenId)
                .map(Imagen::getContenido)
                .orElseThrow(() -> new RuntimeException("Imagen no encontrada"));
    }
}
