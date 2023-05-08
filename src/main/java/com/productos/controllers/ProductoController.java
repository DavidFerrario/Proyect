package com.productos.controllers;

import com.productos.services.ProductoService;
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

import com.productos.models.Producto;

@RestController
@RequestMapping("productos")
public class ProductoController {

    public static final String EL_RESULTADO_ES_NULO = "El resultado es Nulo";
    @Autowired
    ProductoService productoService;

    @GetMapping("/todos_los_productos")
    public ResponseEntity<?> obtenerTodosLosProductos() {
        try {
            return ResponseEntity.ok(productoService.getAllProductos());
        }catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/todos_los_productos_paginados")
    public ResponseEntity<?> obtenerTodosLosProductosPaginados(Pageable pageable) {
        try {
            return ResponseEntity.ok(productoService.getAllProductosPaginados(pageable));
        }catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/todos_los_productos_ordenados")
    public ResponseEntity<?> obtenerTodosLosProductosOrdenados() {
        try {
            return ResponseEntity.ok(productoService.getAllProductosOrdenado());
        }catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearProducto(@RequestBody Producto producto) {
        try {
            return new ResponseEntity<>(productoService.saveProducto(producto), HttpStatus.CREATED);
        }catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/crear_automatico")
    public ResponseEntity<?> crearCincoEmpleados() {
        try{
            return new ResponseEntity<>(productoService.crearProductosDePrueba(5), HttpStatus.CREATED);
        }catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/editar")
    public ResponseEntity<?> editarProducto(@RequestBody Producto producto) {
        try{
            return new ResponseEntity<>(productoService.editProducto(producto), HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<?> eliminarProducto(@RequestParam String id){
        try {
            return new ResponseEntity<>( productoService.deleteProducto(id),HttpStatus.OK);
        } catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/producto_por_id")
    public ResponseEntity<?> productoPorId(@RequestParam String id) {
        try {
            return ResponseEntity.ok(productoService.getProductoActivoPorId(id));
        } catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/producto_por_nombre")
    public ResponseEntity<?> productoPorNombre(@RequestParam String nombre) {
        try {
            return ResponseEntity.ok(productoService.getProductoPorNombre(nombre));
        } catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
