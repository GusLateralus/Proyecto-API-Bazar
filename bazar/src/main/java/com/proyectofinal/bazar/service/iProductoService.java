
package com.proyectofinal.bazar.service;

import com.proyectofinal.bazar.dto.ProductoDTO;
import com.proyectofinal.bazar.model.Producto;
import java.util.List;


public interface iProductoService {
    
    // Create
    public ProductoDTO saveProducto(ProductoDTO dto);
    
    // Read
    // Un solo producto:
    public ProductoDTO findProducto(Long id_producto);
    
    // Muchos productos:
    public List<ProductoDTO> traerProductos();
    
    //Update
    public String updateProducto(Producto product);
    
    // Delete
    public void deleteProducto(Long id_producto); 
    
    // Productos cuya cantidad disponible sea menor a 5:
    public List<ProductoDTO> productosDisponiblesMenores5();
    
    
}
