package festivos.api.aplicacion.servicios;

import java.util.List;

import org.springframework.stereotype.Service;

import festivos.api.core.repositorios.IFestivoRepositorio;
import festivos.api.core.servicios.IFestivoServicio;
import festivos.api.dominio.entidades.Festivo;

@Service
public class FestivoServicio implements IFestivoServicio {

    private final IFestivoRepositorio repositorio;

    public FestivoServicio(IFestivoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public List<Festivo> listar() {
        return repositorio.listar();
    }

    @Override
    public Festivo obtener(int id) {
        var festivoEncontrado = repositorio.obtenerPorId(id);
        return festivoEncontrado.isEmpty() ? null : festivoEncontrado.get();
    }

    @Override
    public List<Festivo> buscar(String nombre) {
        return repositorio.buscarPorNombre(nombre);
    }

    @Override
    public List<Festivo> listarPorPais(int idPais) {
        return repositorio.listarPorPais(idPais);
    }

    @Override
    public List<Festivo> listarPorTipo(int idTipo) {
        return repositorio.listarPorTipo(idTipo);
    }

    @Override
    public Festivo agregar(Festivo festivo) {
        festivo.setId(0);
        return repositorio.guardar(festivo);
    }

    @Override
    public Festivo modificar(Festivo festivo) {
        var festivoEncontrado = repositorio.obtenerPorId(festivo.getId());
        return festivoEncontrado.isEmpty() ? null : repositorio.guardar(festivo);
    }

    @Override
    public boolean eliminar(int id) {
        return repositorio.eliminar(id);
    }
}