package com.productos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.productos.exceptions.ResponseError;
import com.productos.models.Producto;
import com.productos.services.ProductoService;

@RestController
@RequestMapping("productos")
public class ProductoController<T> {

	@Autowired
	ProductoService productoService;

	@GetMapping("/TodosLosProductos")
	public ResponseEntity<?> obtenerTodosLosProductos() {
		try {
			return ResponseEntity.ok(productoService.getAllProductos());
		} catch (NullPointerException e) {
			return new ResponseEntity<ResponseError>(new ResponseError(500, "El resultado es Nulo"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/TodosLosProductosPaginados")
	public ResponseEntity<?> obtenerTodosLosProductosPaginados(Pageable pageable) {
		try {
			return ResponseEntity.ok(productoService.getAllProductosPaginados(pageable));
		} catch (NullPointerException e) {
			return new ResponseEntity<ResponseError>(new ResponseError(500, "El resultado es Nulo"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/TodosLosProductosOrdenados")
	public ResponseEntity<?> obtenerTodosLosProductosOrdenados() {
		try {
			return ResponseEntity.ok(productoService.getAllProductosOrdenado());
		} catch (NullPointerException e) {
			return new ResponseEntity<ResponseError>(new ResponseError(500, "El resultado es Nulo"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("/crear")
	public String crearProducto(@RequestBody Producto producto) {
		productoService.saveProducto(producto);
		return "Empleado Creado";
	}

	@PostMapping("/crearAutomatico")
	public String crearCincoEmpleados(@RequestParam Integer cantidadDeProductosFicticios) {
		productoService.crearProductosDePrueba(cantidadDeProductosFicticios);
		;
		return "Empleado Creado";
	}

	@PutMapping("/editar")
	public String editarProducto(String id, String nombre, String descripcion, float precio, int cantidad)
			throws Throwable {
		productoService.editProducto(id, nombre, descripcion, precio, cantidad);
		return "Producto Editado";
	}

	@DeleteMapping("/eliminar")
	public ResponseEntity<?> eliminarProducto(@RequestParam String id) throws Throwable {
		try {
			productoService.deleteProducto(id);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<ResponseError>(new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/productoPorId")
	public ResponseEntity<?> productoPorId(@RequestParam String id) throws Throwable {
		try {
			return ResponseEntity.ok(productoService.getProductoActivoPorId(id));
		} catch (NullPointerException e) {
			return new ResponseEntity<ResponseError>(new ResponseError(500, "El resultado es Nulo"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<ResponseError>(new ResponseError(500, e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/productoPorNombre")
	public ResponseEntity<?> productoPorNombre(@RequestParam String nombre) throws Throwable {
		try {
			return ResponseEntity.ok(productoService.getProductoPorNombre(nombre));
		} catch (NullPointerException e) {
			return new ResponseEntity<ResponseError>(new ResponseError(500, "El resultado es Nulo"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<ResponseError>(new ResponseError(500, e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
