package org.empresaxyz.inventarioapi;

import org.empresaxyz.inventarioapi.service.InventarioService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InventarioServiceTest {

    private final InventarioService servicio = new InventarioService();

    @Test
    public void testAgregarProductoValido() {
        String resultado = servicio.agregarProducto(1, "Laptop", 10);
        assertEquals("Producto agregado exitosamente.", resultado);
    }

    @Test
    public void testAgregarProductoConIdInvalido() {
        String resultado = servicio.agregarProducto(-1, "Laptop", 10);
        assertEquals("Error: El ID del producto debe ser un número entero positivo.", resultado);
    }

    @Test
    public void testActualizarStockValido() {
        servicio.agregarProducto(1, "Laptop", 10);
        String resultado = servicio.actualizarStock(1, 20);
        assertEquals("Stock actualizado exitosamente.", resultado);
    }

    @Test
    public void testActualizarStockInvalido() {
        AssertionError error = assertThrows(AssertionError.class, () -> {
            servicio.actualizarStock(-1, 10);
        });
        assertEquals("El ID del producto debe ser un entero positivo.", error.getMessage());
    }
}