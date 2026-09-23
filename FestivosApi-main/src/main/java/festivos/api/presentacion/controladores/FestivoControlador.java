package festivos.api.presentacion.controladores;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import festivos.api.core.servicios.IFestivoServicio;
import festivos.api.dominio.entidades.Festivo;

@RestController
@RequestMapping("/api/festivos")
public class FestivoControlador {

    private final IFestivoServicio servicio;

    public FestivoControlador(IFestivoServicio servicio) {
        this.servicio = servicio;
    }

    @GetMapping(value = "/")
    public List<Festivo> listar() {
        return servicio.listar();
    }

    @GetMapping(value = "/{id}")
    public Festivo obtener(@PathVariable int id) {
        return servicio.obtener(id);
    }

    @GetMapping(value = "/buscar/{nombre}")
    public List<Festivo> buscar(@PathVariable String nombre) {
        return servicio.buscar(nombre);
    }

    @GetMapping(value = "/pais/{idPais}")
    public List<Festivo> listarPorPais(@PathVariable int idPais) {
        return servicio.listarPorPais(idPais);
    }

    @GetMapping(value = "/tipo/{idTipo}")
    public List<Festivo> listarPorTipo(@PathVariable int idTipo) {
        return servicio.listarPorTipo(idTipo);
    }

    @PostMapping(value = "/")
    public Festivo agregar(@RequestBody Festivo festivo) {
        return servicio.agregar(festivo);
    }

    @PutMapping(value = "/")
    public Festivo modificar(@RequestBody Festivo festivo) {
        return servicio.modificar(festivo);
    }

    @DeleteMapping(value = "/{id}")
    public boolean eliminar(@PathVariable int id) {
        return servicio.eliminar(id);
    }
}