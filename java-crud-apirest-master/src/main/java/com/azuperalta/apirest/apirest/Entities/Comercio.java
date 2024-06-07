package com.azuperalta.apirest.apirest.Entities;

import java.util.HashSet;

import jakarta.persistence.*;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Comercio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreComercio;
    private String direccion;

    // Utilización de Set para asegurar que no haya duplicados y porque no importa
    // el orden. 
    @OneToMany(mappedBy = "comercio", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("comercio-producto")
    private Set<ComercioProducto> comercioProductos = new HashSet<>();

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreComercio() {
        return nombreComercio;
    }

    public void setNombreComercio(String nombreComercio) {
        this.nombreComercio = nombreComercio;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Set<ComercioProducto> getComercioProductos() {
        return comercioProductos;
    }

    public void setComercioProductos(Set<ComercioProducto> comercioProductos) {
        this.comercioProductos = comercioProductos;
    }

}
