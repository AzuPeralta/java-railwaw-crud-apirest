package com.azuperalta.apirest.apirest.Services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.azuperalta.apirest.apirest.Entities.Comercio;
import com.azuperalta.apirest.apirest.Entities.ComercioProducto;
import com.azuperalta.apirest.apirest.Entities.Producto;
import com.azuperalta.apirest.apirest.Repositories.ComercioRepository;
import com.azuperalta.apirest.apirest.Repositories.ProductoRepository;
import com.azuperalta.apirest.apirest.Repositories.ComercioProductoRepository;

@Service
public class ComercioService {

    @Autowired
    private ComercioRepository comercioRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ComercioProductoRepository comercioProductoRepository;

    public List<Comercio> obtenerTodosLosComercios() {
        return comercioRepository.findAll();
    }

    public Comercio obtenerComercioPorId(Long id) {
        return comercioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el comercio con ID " + id));
    }

    public Comercio crearComercio(Comercio comercio) {
        return comercioRepository.save(comercio);
    }

    public Comercio actualizarComercio(Long id, Comercio detallesNuevoComercio) {
        Comercio comercio = comercioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el comercio con ID " + id));

        comercio.setNombreComercio(detallesNuevoComercio.getNombreComercio());
        comercio.setDireccion(detallesNuevoComercio.getDireccion());

        return comercioRepository.save(comercio);
    }

    public void eliminarComercio(Long id) {
        Comercio comercio = comercioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el comercio con ID " + id));

        comercioRepository.delete(comercio);
    }

    public ComercioProducto agregarProductoAComercio(Long comercioId, Long productoId) {
        Comercio comercio = comercioRepository.findById(comercioId)
                .orElseThrow(() -> new RuntimeException("No se encontró el comercio con ID " + comercioId));
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("No se encontró el producto con ID " + productoId));

        ComercioProducto comercioProducto = new ComercioProducto();
        comercioProducto.setComercio(comercio);
        comercioProducto.setProducto(producto);

        return comercioProductoRepository.save(comercioProducto);
    }
}
