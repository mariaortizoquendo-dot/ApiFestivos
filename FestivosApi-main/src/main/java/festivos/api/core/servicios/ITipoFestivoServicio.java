package festivos.api.core.servicios;

import java.util.List;
import festivos.api.dominio.entidades.TipoFestivo;

public interface ITipoFestivoServicio {

    List<TipoFestivo> listar();

    TipoFestivo obtener(int id);

    TipoFestivo agregar(TipoFestivo tipoFestivo);

    TipoFestivo modificar(TipoFestivo tipoFestivo);
    
    boolean eliminar(int id);
}