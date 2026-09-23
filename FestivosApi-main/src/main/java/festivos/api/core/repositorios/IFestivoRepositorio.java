package festivos.api.core.repositorios;

import java.util.List;
import java.util.Optional;

import festivos.api.dominio.entidades.Festivo;

public interface IFestivoRepositorio {

    List<Festivo> listar();

    Optional<Festivo> obtenerPorId(int id);

    List<Festivo> buscarPorNombre(String nombre);

    List<Festivo> listarPorPais(int idPais);

    List<Festivo> listarPorTipo(int idTipo);

    Festivo guardar(Festivo festivo);

    boolean eliminar(int id);
}