
package com.proyectofinal.bazar.service;

import com.proyectofinal.bazar.model.Producto;
import com.proyectofinal.bazar.repository.iProductoRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService implements iProductoService{
    
    @Autowired
    private iProductoRepository productRepo;

    @Override
    public String saveProducto(Producto product) {
        productRepo.save(product);
        return "Producto guardado con éxito";
    }

    @Override
    public Producto findProducto(Long id_producto) {
        return productRepo.findById(id_producto).orElse(null);
        }

    @Override
    public List<Producto> traerProductos() {
        return productRepo.findAll();
        }

    @Override
    public String updateProducto(Producto product) {
        this.saveProducto(product);
        return "Datos actualizados con éxito";
    }

    @Override
    public void deleteProducto(Long id_producto) {
        productRepo.deleteById(id_producto);
    
    }

    @Override
    public List<Producto> productosDisponiblesMenores5() {
        
        List<Producto> listaProductos = productRepo.findAll();
        List<Producto> listaProductosDisponibles = new ArrayList<>();
        
        for(Producto producto : listaProductos)
        {
            if(producto.getCantidad_disponible()<5)
            {
                listaProductosDisponibles.add(producto);
            }
        
        
        }
        
        
        return listaProductosDisponibles;
        
    }
    
}
