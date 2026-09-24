package festivos.api.infraestructura.persistencia.repositorios.Jpa;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import festivos.api.core.repositorios.ITipoFestivoRepositorio;
import festivos.api.dominio.entidades.TipoFestivo;
import festivos.api.infraestructura.persistencia.entidades.TipoFestivoEntidad;
import festivos.api.infraestructura.persistencia.mapeadores.TipoFestivoMapeador;

@Repository
public class TipoFestivoRepositorio implements ITipoFestivoRepositorio {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<TipoFestivo> listar() {

        List<TipoFestivoEntidad> entidades = entityManager
                .createQuery(
                    "SELECT t FROM TipoFestivoEntidad t",
                    TipoFestivoEntidad.class
                )
                .getResultList();

        return entidades.stream()
                .map(TipoFestivoMapeador::aDominio)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TipoFestivo> obtenerPorId(int id) {

        TipoFestivoEntidad entidad =
                entityManager.find(TipoFestivoEntidad.class, id);

        if (entidad == null) {
            return Optional.empty();
        }

        return Optional.of(TipoFestivoMapeador.aDominio(entidad));
    }

    @Override
    @Transactional
    public TipoFestivo guardar(TipoFestivo tipoFestivo) {

        TipoFestivoEntidad entidad =
                TipoFestivoMapeador.aEntidad(tipoFestivo);

        if (tipoFestivo.getId() == 0) {
            entityManager.persist(entidad);
        } else {
            entidad = entityManager.merge(entidad);
        }

        return TipoFestivoMapeador.aDominio(entidad);
    }

    @Override
    @Transactional
    public boolean eliminar(int id) {

        TipoFestivoEntidad entidad =
                entityManager.find(TipoFestivoEntidad.class, id);

        if (entidad == null) {
            return false;
        }

        entityManager.remove(entidad);
        return true;
    }
}