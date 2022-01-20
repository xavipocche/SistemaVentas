package VentasSpring.Servicios;

import VentasSpring.Entidades.Foto;
import VentasSpring.Repositorios.FotoRepositorio;
import java.util.Optional;
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

    public Foto guardarFoto(MultipartFile archivo) throws Exception {
        if (!archivo.isEmpty()) {

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
            return null;
        }

    }

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
