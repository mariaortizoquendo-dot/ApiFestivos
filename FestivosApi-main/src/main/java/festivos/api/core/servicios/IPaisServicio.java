package festivos.api.core.servicios;

import java.util.List;
import festivos.api.dominio.entidades.Pais;

public interface IPaisServicio {
    List<Pais> listar();

    Pais obtener(int id);

    List<Pais> buscar(String nombre);

    Pais agregar(Pais pais);

    Pais modificar(Pais pais);
    
    boolean eliminar(int id);
}