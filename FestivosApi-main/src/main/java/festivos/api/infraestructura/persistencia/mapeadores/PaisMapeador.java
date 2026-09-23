package festivos.api.infraestructura.persistencia.mapeadores;

import festivos.api.dominio.entidades.Pais;
import festivos.api.infraestructura.persistencia.entidades.PaisEntidad;

public class PaisMapeador {

    // Convierte de Entidad (JPA / Infraestructura) a Dominio (Core)
    public static Pais aDominio(PaisEntidad entidad) {
        if (entidad == null) {
            return null;
        }
        return new Pais(
            entidad.getId(),
            entidad.getNombre()
        );
    }

    // Convierte de Dominio (Core) a Entidad (JPA / Infraestructura)
    public static PaisEntidad aEntidad(Pais dominio) {
        if (dominio == null) {
            return null;
        }
        return new PaisEntidad(
            dominio.getId(),
            dominio.getNombre()
        );
    }
}