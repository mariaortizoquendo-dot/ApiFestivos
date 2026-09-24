package festivos.api.presentacion.controladores;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import festivos.api.core.servicios.IPaisServicio;
import festivos.api.dominio.entidades.Pais;

@RestController
@RequestMapping("/api/paises")
public class PaisControlador {

    private final IPaisServicio servicio;

    public PaisControlador(IPaisServicio servicio) {
        this.servicio = servicio;
    }

    @GetMapping
    public ResponseEntity<List<Pais>> listar() {
        return ResponseEntity.ok(servicio.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pais> obtener(@PathVariable int id) {
        Pais pais = servicio.obtener(id);
        return pais == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(pais);
    }

    @GetMapping("/buscar/{nombre}")
    public ResponseEntity<List<Pais>> buscar(@PathVariable String nombre) {
        return ResponseEntity.ok(servicio.buscar(nombre));
    }

    @PostMapping
    public ResponseEntity<Pais> agregar(@RequestBody Pais pais) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.agregar(pais));
    }

    @PutMapping
    public ResponseEntity<Pais> modificar(@RequestBody Pais pais) {
        Pais modificado = servicio.modificar(pais);
        return modificado == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(modificado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        return servicio.eliminar(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}