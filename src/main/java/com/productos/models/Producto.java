package com.productos.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Producto {

	@Id
	private String idProducto;
	private String nombre;
	private String descripcion;
	private float precio;
	private Integer cantidad;
	private boolean isActivo;

	public Producto() {
		super();
	}

	public Producto(String idProducto, String nombre, String descripcion, float precio, Integer cantidad,
			boolean isActivo) {
		super();
		this.idProducto = idProducto;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.cantidad = cantidad;
		this.isActivo = isActivo;
	}

	public String getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(String idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public boolean isActivo() {
		return isActivo;
	}

	public void setActivo(boolean isActivo) {
		this.isActivo = isActivo;
	}

}
