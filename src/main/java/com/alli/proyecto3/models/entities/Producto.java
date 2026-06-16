package com.alli.proyecto3.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "precio", nullable = false)
    private Double precio;

    @Column(name = "cantidad_en_stock", nullable = false)
    private Integer cantidadEnStock;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoria_id", referencedColumnName = "id", nullable = false)
    private Categoria categoria;
}