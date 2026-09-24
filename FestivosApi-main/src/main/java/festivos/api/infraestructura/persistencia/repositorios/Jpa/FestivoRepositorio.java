package festivos.api.infraestructura.persistencia.repositorios.Jpa;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import festivos.api.core.repositorios.IFestivoRepositorio;
import festivos.api.dominio.entidades.Festivo;
import festivos.api.infraestructura.persistencia.entidades.FestivoEntidad;
import festivos.api.infraestructura.persistencia.entidades.PaisEntidad;
import festivos.api.infraestructura.persistencia.entidades.TipoFestivoEntidad;
import festivos.api.infraestructura.persistencia.mapeadores.FestivoMapeador;

@Repository
public class FestivoRepositorio implements IFestivoRepositorio {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Festivo> listar() {

        List<FestivoEntidad> entidades = entityManager
                .createQuery("SELECT f FROM FestivoEntidad f", FestivoEntidad.class)
                .getResultList();

        return entidades.stream()
                .map(FestivoMapeador::aDominio)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Festivo> obtenerPorId(int id) {

        FestivoEntidad entidad = entityManager.find(FestivoEntidad.class, id);

        if (entidad == null) {
            return Optional.empty();
        }

        return Optional.of(FestivoMapeador.aDominio(entidad));
    }

    @Override
    public List<Festivo> buscarPorNombre(String nombre) {

        List<FestivoEntidad> entidades = entityManager
                .createQuery(
                    "SELECT f FROM FestivoEntidad f " +
                    "WHERE LOWER(f.nombre) LIKE LOWER(:nombre)",
                    FestivoEntidad.class
                )
                .setParameter("nombre", "%" + nombre + "%")
                .getResultList();

        return entidades.stream()
                .map(FestivoMapeador::aDominio)
                .collect(Collectors.toList());
    }

    @Override
    public List<Festivo> listarPorPais(int idPais) {

        List<FestivoEntidad> entidades = entityManager
                .createQuery(
                    "SELECT f FROM FestivoEntidad f " +
                    "WHERE f.pais.id = :idPais",
                    FestivoEntidad.class
                )
                .setParameter("idPais", idPais)
                .getResultList();

        return entidades.stream()
                .map(FestivoMapeador::aDominio)
                .collect(Collectors.toList());
    }

    @Override
    public List<Festivo> listarPorTipo(int idTipo) {

        List<FestivoEntidad> entidades = entityManager
                .createQuery(
                    "SELECT f FROM FestivoEntidad f " +
                    "WHERE f.tipo.id = :idTipo",
                    FestivoEntidad.class
                )
                .setParameter("idTipo", idTipo)
                .getResultList();

        return entidades.stream()
                .map(FestivoMapeador::aDominio)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Festivo guardar(Festivo festivo) {

        FestivoEntidad entidad = FestivoMapeador.aEntidad(festivo);

        if (festivo.getPais() != null) {
            PaisEntidad pais = entityManager.getReference(
                    PaisEntidad.class,
                    festivo.getPais().getId()
            );
            entidad.setPais(pais);
        }

        if (festivo.getTipo() != null) {
            TipoFestivoEntidad tipo = entityManager.getReference(
                    TipoFestivoEntidad.class,
                    festivo.getTipo().getId()
            );
            entidad.setTipo(tipo);
        }

        if (festivo.getId() == 0) {
            entityManager.persist(entidad);
        } else {
            entidad = entityManager.merge(entidad);
        }

        return FestivoMapeador.aDominio(entidad);
    }

    @Override
    @Transactional
    public boolean eliminar(int id) {

        FestivoEntidad entidad = entityManager.find(FestivoEntidad.class, id);

        if (entidad == null) {
            return false;
        }

        entityManager.remove(entidad);
        return true;
    }
}