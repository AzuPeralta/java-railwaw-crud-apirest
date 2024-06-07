package com.azuperalta.apirest.apirest.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.azuperalta.apirest.apirest.Entities.Producto;
import com.azuperalta.apirest.apirest.Services.ProductoService;


@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // Obtener todos los productos
    @GetMapping
    public List<Producto> getAllProductos() {
        return productoService.obtenerTodosLosProductos();
    }

    // Obtener un producto por ID
    @GetMapping("/{id}")
    public Producto getProductoPorId(@PathVariable Long id){
        return productoService.obtenerProductoPorId(id);
    }

    // Crear un nuevo producto
    @PostMapping
    public Producto createProducto(@RequestBody Producto productoCreado) {
        return productoService.crearProducto(productoCreado);
    }

    // Actualizar un producto existente
    @PutMapping("/{id}")
    public Producto updateProducto(@PathVariable Long id, @RequestBody Producto detallesNuevoProducto){
        return productoService.actualizarProducto(id, detallesNuevoProducto);
    }

    // Eliminar un producto por ID
    @DeleteMapping("/{id}")
    public String deleteProducto(@PathVariable Long id){
        productoService.eliminarProducto(id);
        return "El producto con el ID: " + id + " fue eliminado correctamente";
    }
}

