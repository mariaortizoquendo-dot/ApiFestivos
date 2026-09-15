package festivos.api.core.servicios;

import festivos.api.dominio.entidades.TipoFestivo;
import java.util.List;

public interface ITipoServicio {

    List<TipoFestivo> listar();

    TipoFestivo obtener(int id);

    List<TipoFestivo> buscar(String tipo);

    TipoFestivo agregar(TipoFestivo tipo);

    TipoFestivo modificar(TipoFestivo tipo);

    boolean eliminar(int id);
}
