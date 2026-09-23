package festivos.api.aplicacion.servicios;

import java.util.List;
import org.springframework.stereotype.Service;
import festivos.api.core.repositorios.ITipoFestivoRepositorio;
import festivos.api.core.servicios.ITipoFestivoServicio;
import festivos.api.dominio.entidades.TipoFestivo;

@Service
public class TipoFestivoServicio implements ITipoFestivoServicio {

    private final ITipoFestivoRepositorio repositorio;

    public TipoFestivoServicio(ITipoFestivoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public List<TipoFestivo> listar() {
        return repositorio.listar();
    }

    @Override
    public TipoFestivo obtener(int id) {
        var tipoEncontrado = repositorio.obtenerPorId(id);
        return tipoEncontrado.isEmpty() ? null : tipoEncontrado.get();
    }

    @Override
    public TipoFestivo agregar(TipoFestivo tipoFestivo) {
        tipoFestivo.setId(0);
        return repositorio.guardar(tipoFestivo);
    }

    @Override
    public TipoFestivo modificar(TipoFestivo tipoFestivo) {
        var tipoEncontrado = repositorio.obtenerPorId(tipoFestivo.getId());
        return tipoEncontrado.isEmpty() ? null : repositorio.guardar(tipoFestivo);
    }

    @Override
    public boolean eliminar(int id) {
        return repositorio.eliminar(id);
    }
}