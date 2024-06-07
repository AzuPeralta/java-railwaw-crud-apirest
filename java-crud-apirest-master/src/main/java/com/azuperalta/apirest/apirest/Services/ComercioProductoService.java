package com.azuperalta.apirest.apirest.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.azuperalta.apirest.apirest.Entities.Comercio;
import com.azuperalta.apirest.apirest.Entities.ComercioProducto;
import com.azuperalta.apirest.apirest.Entities.Producto;
import com.azuperalta.apirest.apirest.Repositories.ComercioProductoRepository;

@Service
public class ComercioProductoService {

    @Autowired
    private ComercioProductoRepository comercioProductoRepository;



    @Autowired
    private ComercioService comercioService;

    @Autowired
    private ProductoService productoService;

    public ComercioProducto crearRelacion(ComercioProducto relacion) {
        return comercioProductoRepository.save(relacion);
    }

    public ComercioProducto actualizarRelacion(Long id, ComercioProducto detallesNuevaRelacion) {
        ComercioProducto relacion = comercioProductoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró la relación con ID " + id));

        relacion.setComercio(detallesNuevaRelacion.getComercio());
        relacion.setProducto(detallesNuevaRelacion.getProducto());

        return comercioProductoRepository.save(relacion);
    }

    public void eliminarRelacion(Long id) {
        ComercioProducto relacion = comercioProductoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró la relación con ID " + id));

        comercioProductoRepository.delete(relacion);
    }

    public List<ComercioProducto> obtenerTodasLasRelaciones() {
        return comercioProductoRepository.findAll();
    }

    public ComercioProducto obtenerRelacionPorId(Long id) {
        return comercioProductoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró la relación con ID " + id));
    }

    public ComercioProducto agregarProductoAComercio(Long comercioId, Long productoId) {
        // Verificar si ya existe una relación entre el comercio y el producto
        ComercioProducto existente = comercioProductoRepository.findByComercioIdAndProductoId(comercioId, productoId);

        if (existente != null) {
            // Si la relación ya existe, devolver la relación existente
            return existente;
        } else {
            // Si la relación no existe, crear una nueva relación
            ComercioProducto nuevaRelacion = new ComercioProducto();
            Comercio comercio = comercioService.obtenerComercioPorId(comercioId);// Obtener el comercio por su ID
            Producto producto = productoService.obtenerProductoPorId(productoId);// Obtener el producto por su ID

            nuevaRelacion.setComercio(comercio);
            nuevaRelacion.setProducto(producto);

            return comercioProductoRepository.save(nuevaRelacion);
        }
    }
}
