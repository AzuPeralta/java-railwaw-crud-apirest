package com.azuperalta.apirest.apirest.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.azuperalta.apirest.apirest.Entities.Comercio;
import com.azuperalta.apirest.apirest.Services.ComercioService;

@RestController
@RequestMapping("/comercios")
public class ComercioController {

    @Autowired
    private ComercioService comercioService;

    @GetMapping
    public List<Comercio> getAllComercios() {
        return comercioService.obtenerTodosLosComercios();
    }

    @GetMapping("/{id}")
    public Comercio getComercioPorId(@PathVariable Long id) {
        return comercioService.obtenerComercioPorId(id);
    }

    @PostMapping
    public Comercio createComercio(@RequestBody Comercio comercioCreado) {
        return comercioService.crearComercio(comercioCreado);
    }

    @PutMapping("/{id}")
    public Comercio updateComercio(@PathVariable Long id, @RequestBody Comercio detallesNuevoComercio) {
        return comercioService.actualizarComercio(id, detallesNuevoComercio);
    }

    @DeleteMapping("/{id}")
    public String deleteComercio(@PathVariable Long id) {
        comercioService.eliminarComercio(id);
        return "El comercio con el ID: " + id + " fue eliminado correctamente";
    }
}
