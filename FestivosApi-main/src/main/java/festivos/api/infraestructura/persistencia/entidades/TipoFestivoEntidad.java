package festivos.api.infraestructura.persistencia.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "tipofestivo")
public class TipoFestivoEntidad {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "secuencia_tipo_festivo")
    @SequenceGenerator(
        name = "secuencia_tipo_festivo",
        sequenceName = "tipofestivo_id_seq",
        allocationSize = 1
    )
    private int id;

    @Column(name = "tipo", length = 100, nullable = false)
    private String tipo;

    public TipoFestivoEntidad() {
    }

    public TipoFestivoEntidad(int id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}