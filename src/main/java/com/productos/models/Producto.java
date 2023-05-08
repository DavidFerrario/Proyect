package com.productos.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto {

	@Id
	private String idProducto;
	private String nombre;
	private String descripcion;
	private float precio;
	private Integer cantidad;
	private boolean isActivo;
}
