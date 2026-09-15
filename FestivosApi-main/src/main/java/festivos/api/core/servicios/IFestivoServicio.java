package festivos.api.core.servicios;

import java.util.List;

import festivos.api.dominio.entidades.Festivo;

public interface IFestivoServicio {

    List<Festivo> listar();

    Festivo obtener(int id);

    List<Festivo> buscar(String nombre);

    List<Festivo> listarPorPais(int idPais);

    List<Festivo> listarPorTipo(int idTipo);

    Festivo agregar(Festivo festivo);

    Festivo modificar(Festivo festivo);

    boolean eliminar(int id);
}
