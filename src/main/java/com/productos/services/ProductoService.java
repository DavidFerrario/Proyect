package com.productos.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.productos.models.Producto;
import com.productos.repositories.ProductoRepository;

@Service
public class ProductoService {

    public static final String PRODUCTO_DE_PRUEBA = "Producto de prueba";
    public static final float PRECIO_BASE = 5;
    public static final String DESCRIPCION_DE_PRUEBA = "Descripcion de prueba";
    public static final String CODIGO_DE_PRUEBA = "codigo de prueba";
    @Autowired
    ProductoRepository productoRepository;

    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    public Page<Producto> getAllProductosPaginados(Pageable pageable) {
        return productoRepository.findAll(pageable);
    }

    public List<Producto> getAllProductosOrdenado() {
        return productoRepository.productoOrdenado();
    }

    public Producto getProductoActivoPorId(String id) { // REFLAJAR CON UN NOMBRE CORRECTO EL FUNCIONAMIENTO DEL ID
        Producto producto = new Producto();
        Optional<Producto> productoBuscado = productoRepository.findById(id);
        if (productoBuscado.isPresent()) {
            if (productoBuscado.get().isActivo()) {
                producto = productoBuscado.get();
            }
        }
        return producto;
    }


    public List<Producto> getProductoPorNombre(String nombre) {
        List<Producto> productos = new ArrayList<>();
        List<Producto> productosBuscado = productoRepository.findBynombreContains(nombre); //CAMELCASE
        if (!productosBuscado.isEmpty()) {
            productos = productosBuscado;
        }
        return productos;

    }


    public Producto saveProducto(Producto producto) {
        return productoRepository.save(producto);

    }


    public Producto deleteProducto(String id) {
        Producto productoBuscado = this.getProductoActivoPorId(id);
        productoBuscado.setActivo(false);
        return this.saveProducto(productoBuscado);
    }

    public Producto editProducto(Producto producto) {
        Producto productoBuscado = this.getProductoActivoPorId(producto.getIdProducto());
        if (productoBuscado != null) {
            producto = this.saveProducto(producto);
        }
        return producto;
    }

    public List<Producto> crearProductosDePrueba(Integer cantidadDeProductosFicticios) {
        List<Producto> productos = new ArrayList<>();
        for (int i = 0; i < cantidadDeProductosFicticios; i++) {
            Producto producto = new Producto(CODIGO_DE_PRUEBA + i, PRODUCTO_DE_PRUEBA + i, DESCRIPCION_DE_PRUEBA + i, PRECIO_BASE + i, i, true);
            this.saveProducto(producto);
            productos.add(producto);
        }
        return productos;
    }
}


