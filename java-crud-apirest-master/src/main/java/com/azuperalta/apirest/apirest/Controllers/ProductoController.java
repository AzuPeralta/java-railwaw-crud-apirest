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

import com.azuperalta.apirest.apirest.Repositories.ProductoRepository;
import com.azuperalta.apirest.apirest.Entities.Producto;

@RestController
@RequestMapping("/productos")

public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    //Obtiene una lista con todos los productos.
    @GetMapping
    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    //Get individual segun id 
    @GetMapping("/{id}")
    public Producto getProductoPorId(@PathVariable Long id){
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro el producto con ID " + id));
    }

    @PostMapping
    public Producto createProducto(@RequestBody Producto productoCreado) {
        return productoRepository.save(productoCreado);
    }

    @PutMapping("/{id}")
    public Producto updateProducto(@PathVariable Long id, @RequestBody Producto detallesNuevoProducto){
        Producto producto = productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontro el producto con ID " + id));

        producto.setNombre(detallesNuevoProducto.getNombre());
        producto.setPrecio(detallesNuevoProducto.getPrecio());

        //.save actualiza base de datos
        return productoRepository.save(producto);
    }

    @DeleteMapping("/{id}")
    public String deleteProducto(@PathVariable Long id){
        Producto producto = productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontro el producto con ID " + id));

        productoRepository.delete(producto);
        return "El producto con el ID: " + id + " fue eliminado correctamente";
    }

};
