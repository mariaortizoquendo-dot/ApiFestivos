package festivos.api.aplicacion.servicios;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import festivos.api.core.repositorios.IFestivoRepositorio;
import festivos.api.core.servicios.IFestivoServicio;
import festivos.api.dominio.dtos.FestivoDTO;
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

    private LocalDate getDomingoPascua(int año) {
        int a = año % 19;
        int b = año % 4;
        int c = año % 7;
        int d = (19 * a + 24) % 30;
        int dias = d + (2 * b + 4 * c + 6 * d + 5) % 7;

        LocalDate domingoRamos = LocalDate.of(año, 3, 15).plusDays(dias);
        return domingoRamos.plusDays(7);
    }

    private LocalDate trasladarAlLunes(LocalDate fecha) {
        if (fecha.getDayOfWeek() == DayOfWeek.MONDAY) {
            return fecha;
        }
        int diasHastaLunes = DayOfWeek.SUNDAY.getValue() - fecha.getDayOfWeek().getValue() + 1;
        if (diasHastaLunes <= 0) {
            diasHastaLunes += 7;
        }
        return fecha.plusDays(diasHastaLunes);
    }

    private LocalDate calcularFechaFestivo(Festivo festivo, int año) {
        int tipo = festivo.getTipo().getId();
        switch (tipo) {
            case 1: // Fijo
                return LocalDate.of(año, festivo.getMes(), festivo.getDia());
            case 2: // Ley de Puente (se mueve al lunes)
                return trasladarAlLunes(LocalDate.of(año, festivo.getMes(), festivo.getDia()));
            case 3: // Basado en Pascua
                return getDomingoPascua(año).plusDays(festivo.getDiasPascua());
            case 4: // Basado en Pascua + Ley de Puente
                LocalDate fechaPascua = getDomingoPascua(año).plusDays(festivo.getDiasPascua());
                return trasladarAlLunes(fechaPascua);
            default:
                return null;
        }
    }

    @Override
    public String verificarFecha(int idPais, int año, int mes, int dia) {
        // Validar si la fecha es físicamente válida
        LocalDate fechaConsulta;
        try {
            fechaConsulta = LocalDate.of(año, mes, dia);
        } catch (DateTimeException e) {
            return "Fecha No valida";
        }

        // Obtener todos los festivos calculados para ese país y año
        List<FestivoDTO> festivosAño = obtenerFestivosPorAño(idPais, año);

        // Verificar coincidencia
        boolean esFestivo = festivosAño.stream()
                .anyMatch(f -> f.getFecha().equals(fechaConsulta));

        return esFestivo ? "Es Festivo" : "No es festivo";
    }

    @Override
    public List<FestivoDTO> obtenerFestivosPorAño(int idPais, int año) {
        List<Festivo> festivos = repositorio.listarPorPais(idPais);
        List<FestivoDTO> resultado = new ArrayList<>();

        for (Festivo festivo : festivos) {
            LocalDate fechaCalculada = calcularFechaFestivo(festivo, año);
            if (fechaCalculada != null) {
                resultado.add(new FestivoDTO(festivo.getNombre(), fechaCalculada));
            }
        }
        return resultado;
    }
}
