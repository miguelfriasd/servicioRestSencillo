package com.distribuidositson.servicioRestSencillo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<Producto> obtenerTodosLosProductos() {
        return productoService.obtenerTodosLosProductos();
    }

    @GetMapping("/{id}")
    public Producto obtenerProductoPorId(@PathVariable Long id) {
        return productoService.obtenerProductoPorId(id).orElse(null);
    }

    @PostMapping
    public String crearProducto(@RequestBody Producto producto) {
        Producto nuevoProducto = productoService.crearProducto(producto);
        if (nuevoProducto != null) {
            return "{\"success\": true, \"message\": \"Producto ha sido creado exitosamente.\"}";
        } else {
            return "{\"success\": false, \"message\": \"No se pudo crear el producto.\"}";
        }
    }

    @PutMapping("/{id}")
    public String actualizarProducto(@RequestBody Producto producto, @PathVariable Long id) {
        Producto productoActualizado = productoService.actualizarProducto(id, producto);
        if (productoActualizado != null) {
            return "{\"success\": true, \"message\": \"Producto ha sido actualizado exitosamente.\"}";
        } else {
            return "{\"success\": false, \"message\": \"No se pudo actualizar el producto.\"}";
        }
    }

    @DeleteMapping("/{id}")
    public String borrarProducto(@PathVariable Long id) {
        if (productoService.borrarProducto(id)) {
            return "{\"success\": true, \"message\": \"Producto ha sido eliminado exitosamente.\"}";
        } else {
            return "{\"success\": false, \"message\": \"No se pudo eliminar el producto.\"}";
        }
    }
}
