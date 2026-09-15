package festivos.api.core.repositorios;

import java.util.List;
import java.util.Optional;

import festivos.api.dominio.entidades.Festivo;

public interface IFestivoRepositorio {

    List<Festivo> listar();

    Optional<Festivo> obtenerPorId(int id);

    Festivo guardar(Festivo festivo);

    boolean eliminar(int id);
}
