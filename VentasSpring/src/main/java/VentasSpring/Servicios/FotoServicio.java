package VentasSpring.Servicios;

import VentasSpring.Entidades.Foto;
import VentasSpring.Repositorios.FotoRepositorio;
import java.util.Optional;
import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Xavier Pocchettino
 */
@Service
public class FotoServicio {

    @Autowired
    private FotoRepositorio fotoRepositorio;

    @Transactional
    public Foto guardarFoto(MultipartFile archivo) throws Exception {

        if (archivo.getContentType().equals("application/octet-stream") || archivo.isEmpty()) {
            return null;
        }

        if (validarExtension(archivo)) {

            try {
                Foto foto = new Foto();
                foto.setMime(archivo.getContentType());
                foto.setNombre(archivo.getName());
                foto.setContenido(archivo.getBytes());

                return fotoRepositorio.save(foto);
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }

        } else {
            throw new Exception("El archivo debe ser de tipo imagen.");
        }

    }

    /*Valida: si el archivo es una imagen devuelve un Boolean.TRUE, caso contrario significa que 
    no es una imagen y devuelve un Boolean.FALSE
     */
    public Boolean validarExtension(MultipartFile archivo) throws Exception {
        try {
            ImageIO.read(archivo.getInputStream()).toString();
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    public Boolean validarArchivoVacio(MultipartFile archivo) throws Exception {
        try {
            if (archivo.getContentType().equals("application/octet-stream")) {
                return null;
            }
            ImageIO.read(archivo.getInputStream()).toString();
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    @Transactional
    public Foto modificarFoto(String id, MultipartFile archivo) throws Exception {
        if (archivo != null) {

            try {
                Foto foto = new Foto();

                if (id != null) {
                    Optional<Foto> respuesta = fotoRepositorio.findById(id);
                    if (respuesta.isPresent()) {
                        foto = respuesta.get();
                    }
                }

                foto.setMime(archivo.getContentType());
                foto.setNombre(archivo.getName());
                foto.setContenido(archivo.getBytes());

                return fotoRepositorio.save(foto);
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }
        }

        return null;
    }

}
