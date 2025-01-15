package org.empresaxyz.inventarioapi.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class InventarioService {

    // Parte 1: Programación Defensiva

    private final Map<Integer, Producto> inventario = new HashMap<>();

    // Consulta un producto por ID con validación defensiva
    public String consultarProducto(int idProducto) {
        if (idProducto <= 0) {
            return "Error: El ID del producto debe ser un número entero positivo.";
        }
        Producto producto = inventario.get(idProducto);
        if (producto == null) {
            return "Error: Producto no encontrado.";
        }
        return "Producto: " + producto.getNombre() + ", Stock: " + producto.getCantidad();
    }

    // Agrega un producto validando la cantidad
    public String agregarProducto(int idProducto, String nombre, int cantidad) {
        if (idProducto <= 0) {
            return "Error: El ID del producto debe ser un número entero positivo.";
        }
        if (cantidad <= 0) {
            return "Error: La cantidad debe ser un número entero positivo.";
        }
        inventario.put(idProducto, new Producto(nombre, cantidad));
        return "Producto agregado exitosamente.";
    }

    // Parte 2: Programación por Contrato y Aserciones

    // Actualiza el stock de un producto con aserciones
    public String actualizarStock(int idProducto, int nuevaCantidad) {
        assert idProducto > 0 : "El ID del producto debe ser un entero positivo.";
        assert nuevaCantidad >= 0 : "La cantidad debe ser un entero no negativo.";

        Producto producto = inventario.get(idProducto);
        if (producto == null) {
            return "Error: Producto no encontrado.";
        }
        producto.setCantidad(nuevaCantidad);
        return "Stock actualizado exitosamente.";
    }
}
