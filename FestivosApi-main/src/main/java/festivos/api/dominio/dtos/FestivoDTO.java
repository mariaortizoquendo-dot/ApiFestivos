package festivos.api.dominio.dtos;

import java.time.LocalDate;

public class FestivoDTO {

    private String nombre;
    private LocalDate fecha;

    public FestivoDTO() {
    }

    public FestivoDTO(String nombre, LocalDate fecha) {
        this.nombre = nombre;
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}