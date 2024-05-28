package com.azuperalta.apirest.apirest.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.azuperalta.apirest.apirest.Entities.Comercio;
import com.azuperalta.apirest.apirest.Repositories.ComercioRepository;

@RestController
@RequestMapping("/comercios")

public class ComercioController {

    @Autowired
    private ComercioRepository comercioRepository;

    //Obtiene una lista con todos los productos.
    @GetMapping
    public List<Comercio> getAllProductos() {
        return comercioRepository.findAll();
    }

    //Get individual segun id 
    @GetMapping("/{id}")
    public Comercio getProductoPorId(@PathVariable Long id){
        return comercioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro el comercio con ID " + id));
    }

    @PostMapping
    public Comercio createComercio (@RequestBody Comercio comercioCreado) {
        return comercioRepository.save(comercioCreado);
    }

    @PutMapping("/{id}")
    public Comercio updateComercio(@PathVariable Long id, @RequestBody Comercio detallesNuevoComercio){
        Comercio comercio = comercioRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontro el comercio con ID " + id));

        comercio.setNombreComercio(detallesNuevoComercio.getNombreComercio());
        comercio.setDireccion(detallesNuevoComercio.getDireccion());

        //.save actualiza base de datos
        return comercioRepository.save(comercio);
    }

    @DeleteMapping("/{id}")
    public String deleteComercio(@PathVariable Long id){
        Comercio comercio = comercioRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontro el comercio con ID " + id));

        comercioRepository.delete(comercio);
        return "El comercio con el ID: " + id + " fue eliminado correctamente";
    }
}
