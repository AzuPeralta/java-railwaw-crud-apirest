package com.azuperalta.apirest.apirest.Services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.azuperalta.apirest.apirest.Entities.Comercio;
import com.azuperalta.apirest.apirest.Entities.ComercioProducto;
import com.azuperalta.apirest.apirest.Entities.Producto;
import com.azuperalta.apirest.apirest.Repositories.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public Producto crearProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto actualizarProducto(Long id, Producto detallesNuevoProducto) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el producto con ID " + id));

        producto.setNombre(detallesNuevoProducto.getNombre());
        producto.setPrecio(detallesNuevoProducto.getPrecio());

        return productoRepository.save(producto);
    }

    public void eliminarProducto(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el producto con ID " + id));

        productoRepository.delete(producto);
    }

    public List<Producto> obtenerTodosLosProductos() {
        return productoRepository.findAll();
    }

    public Producto obtenerProductoPorId(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el producto con ID " + id));
    }

    public List<Producto> obtenerProductosConNombreComercio() {
        List<Producto> productos = productoRepository.findAll();
        
        for (Producto producto : productos) {
            String nombreComercio = obtenerNombreComercioPorProducto(producto);
            producto.setNombreComercio(nombreComercio);
        }
        
        return productos;
    }

    private String obtenerNombreComercioPorProducto(Producto producto) {
        Set<ComercioProducto> comercioProductos = producto.getComercioProductos();

        if (!comercioProductos.isEmpty()) {
            ComercioProducto primerComercioProducto = comercioProductos.iterator().next();
            Comercio comercio = primerComercioProducto.getComercio();
            return comercio.getNombreComercio();
        }
        
        return null;
    }
}
