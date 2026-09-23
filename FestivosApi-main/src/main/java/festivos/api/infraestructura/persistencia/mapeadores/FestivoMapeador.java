package festivos.api.infraestructura.persistencia.mapeadores;

import festivos.api.dominio.entidades.Festivo;
import festivos.api.infraestructura.persistencia.entidades.festivoEntidades;

public class FestivoMapeador {

    public static Festivo haciaDominio(festivoEntidades entidad){
        if (entidad == null)
            return null;

        return new Festivo(
            entidad.getId(),
            entidad.getPais(),
            entidad.getNombre(),
            entidad.getDia(),
            entidad.getMes(),
            entidad.getDiasPascua(),
            entidad.getTipo(),
            

        )
    }

}
