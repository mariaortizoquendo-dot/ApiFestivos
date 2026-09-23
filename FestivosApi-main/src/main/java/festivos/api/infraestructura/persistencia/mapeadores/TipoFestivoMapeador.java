package festivos.api.infraestructura.persistencia.mapeadores;

import festivos.api.dominio.entidades.TipoFestivo;
import festivos.api.infraestructura.persistencia.entidades.TipoFestivoEntidad;

public class TipoFestivoMapeador {

    // Convierte de Entidad (JPA / Infraestructura) a Dominio (Core)
    public static TipoFestivo aDominio(TipoFestivoEntidad entidad) {
        if (entidad == null) {
            return null;
        }
        return new TipoFestivo(
            entidad.getId(),
            entidad.getTipo()
        );
    }

    // Convierte de Dominio (Core) a Entidad (JPA / Infraestructura)
    public static TipoFestivoEntidad aEntidad(TipoFestivo dominio) {
        if (dominio == null) {
            return null;
        }
        return new TipoFestivoEntidad(
            dominio.getId(),
            dominio.getTipo()
        );
    }
}