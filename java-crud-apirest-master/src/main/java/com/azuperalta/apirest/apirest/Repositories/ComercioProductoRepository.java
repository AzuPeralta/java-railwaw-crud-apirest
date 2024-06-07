package com.azuperalta.apirest.apirest.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.azuperalta.apirest.apirest.Entities.ComercioProducto;

public interface ComercioProductoRepository extends JpaRepository<ComercioProducto, Long>{
    ComercioProducto findByComercioIdAndProductoId(Long comercioId, Long productoId);


}
