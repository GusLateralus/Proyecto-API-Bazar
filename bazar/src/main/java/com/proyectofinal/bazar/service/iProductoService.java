
package com.proyectofinal.bazar.service;

import com.proyectofinal.bazar.model.Producto;
import java.util.List;


public interface iProductoService {
    
    // Create
    public String saveProducto(Producto product);
    
    // Read
    // Un solo producto:
    public Producto findProducto(Long id_producto);
    
    // Muchos productos:
    public List<Producto> traerProductos();
    
    //Update
    public String updateProducto(Producto product);
    
    // Delete
    public void deleteProducto(Long id_producto); 
    
    // Productos cuya cantidad disponible sea menor a 5:
    public List<Producto> productosDisponiblesMenores5();
    
    
}
