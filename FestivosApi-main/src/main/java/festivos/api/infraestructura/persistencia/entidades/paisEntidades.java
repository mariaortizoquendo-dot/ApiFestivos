package festivos.api.infraestructura.persistencia.entidades;

import org.hibernate.boot.internal.CollectionClassification;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "pais")
public class paisEntidades {
    @Id 
    @Column(name= "id")
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
}
