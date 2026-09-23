package festivos.api.core.repositorios;

import java.util.List;
import java.util.Optional;
import festivos.api.dominio.entidades.TipoFestivo;

public interface ITipoFestivoRepositorio {
    
    List<TipoFestivo> listar();

    Optional<TipoFestivo> obtenerPorId(int id);

    TipoFestivo guardar(TipoFestivo tipoFestivo);

    boolean eliminar(int id);
}