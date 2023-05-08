package com.productos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.productos.models.Producto;

public interface ProductoRepository<E> extends JpaRepository<Producto, String> {

	List<Producto> findBynombreContains(String nombre);

	@Query(value = "SELECT * FROM PRODUCTO ORDER BY precio ASC", nativeQuery = true)
	List<Producto> productoOrdenado();

}
