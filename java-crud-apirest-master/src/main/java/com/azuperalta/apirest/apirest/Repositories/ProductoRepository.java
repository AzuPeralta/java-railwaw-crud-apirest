package com.azuperalta.apirest.apirest.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.azuperalta.apirest.apirest.Entities.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
