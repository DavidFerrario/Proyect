package com.productos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.productos.models.Producto;
import com.productos.repositories.ProductoRepository;
@Service
public class ProductoService<E> {
	
	@Autowired
	ProductoRepository productoRepository;
	
	public List<Producto> getAllProductos() {
		return productoRepository.findAll();
	}
	
	public Page<E> getAllProductosPaginados(Pageable pageable) {
		return productoRepository.findAll(pageable);
	}

	public List<Producto> getAllProductosOrdenado() {
		return productoRepository.productoOrdenado();
	}
	
	public Optional<Producto> getProductoActivoPorId(String id) throws Throwable  { // REFLAJAR CON UN NOMBRE CORRECTO EL FUNCIONAMIENTO DEL ID
		
		Optional<Producto> productoBuscado =  productoRepository.findById(id);
		
		if(productoBuscado.isPresent()){
			if(productoBuscado.get().isActivo()) {
				return productoBuscado;
			}else {
				throw new Exception("El producto fue dado de baja");
			}
		}else {
			throw new Exception("El producto no figura en nuestra base de datos");
		}
	}

	
public List<Producto> getProductoPorNombre(String nombre) throws Throwable  {
		
		List<Producto> productosBuscado = productoRepository.findBynombreContains(nombre); //CAMELCASE
		
		if(!productosBuscado.isEmpty()){
			
				return productosBuscado;
			}else {
				throw new Exception("El producto fue dado de baja");
			}
		
	}
	
	
	public void saveProducto(Producto producto) {
		productoRepository.save(producto);
		
	}
	

	public void deleteProducto(String id) throws Throwable {
		Optional<Producto> productoBuscado = this.getProductoActivoPorId(id);
		productoBuscado.get().setActivo(false);
		this.saveProducto(productoBuscado.get());
		
		
	}


	public void editProducto(String id, String nombre,String descripcion,float precio,int cantidad) throws Throwable {
		Optional<Producto> productoBuscado = this.getProductoActivoPorId(id);
		Producto producto = new Producto();
		if(productoBuscado.isPresent()) {
			producto.setIdProducto(productoBuscado.get().getIdProducto());
			producto.setNombre(nombre);
			producto.setDescripcion(descripcion);
			producto.setPrecio(precio);
			producto.setCantidad(cantidad);
			this.saveProducto(producto);
		}		
		
	}
	
	public void crearProductosDePrueba(Integer cantidadDeProductosFicticios) {
		float precioBase = 5;
		for (int i = 0; i <cantidadDeProductosFicticios;  i++) {
		Producto producto = new Producto("a"+i,"Producto de prueba"+i,"Descripcion de prueba"+i,precioBase+i,i,true);
		this.saveProducto(producto);
		}
		
		
	}
		
	}


