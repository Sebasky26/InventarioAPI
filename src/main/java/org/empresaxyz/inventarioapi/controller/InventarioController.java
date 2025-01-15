package org.empresaxyz.inventarioapi.controller;

import org.empresaxyz.inventarioapi.service.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producto")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    // Endpoint GET /producto/{id_producto}
    @GetMapping("/{idProducto}")
    public ResponseEntity<String> consultarProducto(@PathVariable int idProducto) {
        String resultado = inventarioService.consultarProducto(idProducto);
        if (resultado.startsWith("Error")) {
            return new ResponseEntity<>(resultado, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    // Endpoint POST /producto
    @PostMapping
    public ResponseEntity<String> agregarProducto(@RequestParam int idProducto, @RequestParam String nombre, @RequestParam int cantidad) {
        String resultado = inventarioService.agregarProducto(idProducto, nombre, cantidad);
        if (resultado.startsWith("Error")) {
            return new ResponseEntity<>(resultado, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(resultado, HttpStatus.CREATED);
    }

    // Endpoint PUT /producto/{id_producto}
    @PutMapping("/{idProducto}")
    public ResponseEntity<String> actualizarStock(@PathVariable int idProducto, @RequestParam int nuevaCantidad) {
        try {
            String resultado = inventarioService.actualizarStock(idProducto, nuevaCantidad);
            if (resultado.startsWith("Error")) {
                return new ResponseEntity<>(resultado, HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } catch (AssertionError e) {
            return new ResponseEntity<>("Error de validación: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
