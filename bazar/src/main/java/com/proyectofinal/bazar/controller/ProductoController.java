
package com.proyectofinal.bazar.controller;

import com.proyectofinal.bazar.dto.ProductoDTO;
import com.proyectofinal.bazar.model.Producto;
import com.proyectofinal.bazar.service.iProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductoController {
    
    @Autowired
    iProductoService productService;
    
    // Crear producto
    @PostMapping("productos/crear")
    public ProductoDTO saveProducto(@RequestBody ProductoDTO dto)
    {
        return productService.saveProducto(dto);
    }
    
    // Traer un producto
    @GetMapping("productos/traer/{id_producto}")
    public ProductoDTO traerProducto(@PathVariable Long id_producto)
    {
        return productService.findProducto(id_producto);
    }
    
    // Traer lista de productos
    @GetMapping("productos/traer")
    public List<ProductoDTO> traerListaProductos()
    {
        return productService.traerProductos();
    }
    
    // Editar un producto:
    @PutMapping("productos/editar")
    public String editProducto(@RequestBody Producto product)
    {
        return productService.updateProducto(product);
    }
    
    // Eliminar un producto:
    @DeleteMapping("productos/eliminar/{id_producto}")
    public void deleteProducto(@PathVariable Long id_producto)
    {
        productService.deleteProducto(id_producto);
    }
    
    // Productos cuya cantidad disponible sea menor a 5:
    @GetMapping("productos/traer/menosDeCinco")
    public List<ProductoDTO> traerProductosDisponiblesMenores5()
    {
        return productService.productosDisponiblesMenores5();
    }
    
}
