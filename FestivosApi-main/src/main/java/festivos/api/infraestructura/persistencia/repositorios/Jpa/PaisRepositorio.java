package festivos.api.infraestructura.persistencia.repositorios.Jpa;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import festivos.api.core.repositorios.IPaisRepositorio;
import festivos.api.dominio.entidades.Pais;
import festivos.api.infraestructura.persistencia.entidades.PaisEntidad;
import festivos.api.infraestructura.persistencia.mapeadores.PaisMapeador;

@Repository
public class PaisRepositorio implements IPaisRepositorio {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Pais> listar() {

        List<PaisEntidad> entidades = entityManager
                .createQuery(
                    "SELECT p FROM PaisEntidad p",
                    PaisEntidad.class
                )
                .getResultList();

        return entidades.stream()
                .map(PaisMapeador::aDominio)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Pais> obtenerPorId(int id) {

        PaisEntidad entidad = entityManager.find(PaisEntidad.class, id);

        if (entidad == null) {
            return Optional.empty();
        }

        return Optional.of(PaisMapeador.aDominio(entidad));
    }

    @Override
    public List<Pais> buscarPorNombre(String nombre) {

        List<PaisEntidad> entidades = entityManager
                .createQuery(
                    "SELECT p FROM PaisEntidad p " +
                    "WHERE LOWER(p.nombre) LIKE LOWER(:nombre)",
                    PaisEntidad.class
                )
                .setParameter("nombre", "%" + nombre + "%")
                .getResultList();

        return entidades.stream()
                .map(PaisMapeador::aDominio)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Pais guardar(Pais pais) {

        PaisEntidad entidad = PaisMapeador.aEntidad(pais);

        if (pais.getId() == 0) {
            entityManager.persist(entidad);
        } else {
            entidad = entityManager.merge(entidad);
        }

        return PaisMapeador.aDominio(entidad);
    }

    @Override
    @Transactional
    public boolean eliminar(int id) {

        PaisEntidad entidad = entityManager.find(PaisEntidad.class, id);

        if (entidad == null) {
            return false;
        }

        entityManager.remove(entidad);
        return true;
    }
}