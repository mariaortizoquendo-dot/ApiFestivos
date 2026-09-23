package festivos.api.aplicacion.servicios;

import java.util.List;
import org.springframework.stereotype.Service;
import festivos.api.core.repositorios.IPaisRepositorio;
import festivos.api.core.servicios.IPaisServicio;
import festivos.api.dominio.entidades.Pais;

@Service
public class PaisServicio implements IPaisServicio {

    private final IPaisRepositorio repositorio;

    public PaisServicio(IPaisRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public List<Pais> listar() {
        return repositorio.listar();
    }

    @Override
    public Pais obtener(int id) {
        var paisEncontrado = repositorio.obtenerPorId(id);
        return paisEncontrado.isEmpty() ? null : paisEncontrado.get();
    }

    @Override
    public List<Pais> buscar(String nombre) {
        return repositorio.buscarPorNombre(nombre);
    }

    @Override
    public Pais agregar(Pais pais) {
        pais.setId(0);
        return repositorio.guardar(pais);
    }

    @Override
    public Pais modificar(Pais pais) {
        var paisEncontrado = repositorio.obtenerPorId(pais.getId());
        return paisEncontrado.isEmpty() ? null : repositorio.guardar(pais);
    }

    @Override
    public boolean eliminar(int id) {
        return repositorio.eliminar(id);
    }
}