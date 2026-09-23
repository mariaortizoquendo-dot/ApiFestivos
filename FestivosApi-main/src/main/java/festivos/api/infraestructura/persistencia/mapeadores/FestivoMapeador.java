package festivos.api.infraestructura.persistencia.mapeadores;

import festivos.api.dominio.entidades.Festivo;
import festivos.api.infraestructura.persistencia.entidades.FestivoEntidad;

public class FestivoMapeador {

    // Convierte de Entidad (JPA / Infraestructura) a Dominio (Core)
    public static Festivo aDominio(FestivoEntidad entidad) {
        if (entidad == null) {
            return null;
        }

        return new Festivo(
            entidad.getId(),
            PaisMapeador.aDominio(entidad.getPais()),           // Mapea la relación de País
            entidad.getNombre(),
            entidad.getDia(),
            entidad.getMes(),
            entidad.getDiasPascua(),
            TipoFestivoMapeador.aDominio(entidad.getTipo())     // Mapea la relación de TipoFestivo
        );
    }

    // Convierte de Dominio (Core) a Entidad (JPA / Infraestructura)
    public static FestivoEntidad aEntidad(Festivo dominio) {
        if (dominio == null) {
            return null;
        }

        return new FestivoEntidad(
            dominio.getId(),
            PaisMapeador.aEntidad(dominio.getPais()),           // Mapea el objeto País de Dominio a Entidad
            dominio.getNombre(),
            dominio.getDia(),
            dominio.getMes(),
            dominio.getDiasPascua(),
            TipoFestivoMapeador.aEntidad(dominio.getTipo())     // Mapea el objeto TipoFestivo de Dominio a Entidad
        );
    }
}