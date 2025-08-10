
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

    // Este método parece que funciona correctamente, tal vez cosas que cambiaría sería ponerle una restricción para no registrar el mismo producto 2 veces
    // Otra cosa que podrías hacer es devolver un JSON en lugar de un String
    @Override
    public String saveProducto(Producto product) {
        productRepo.save(product);
        return "Producto guardado con éxito";
    }

    // Si devuelves un JSON, asegúrate que no entre en un ciclo de recursión (usa un DTO)
    @Override
    public Producto findProducto(Long id_producto) {
        return productRepo.findById(id_producto).orElse(null);
        }

    // Usa un DTO
    @Override
    public List<Producto> traerProductos() {
        return productRepo.findAll();
        }

    // Bien, quizás podrías mejorarlo devolviendo el JSON
    @Override
    public String updateProducto(Producto product) {
        this.saveProducto(product);
        return "Datos actualizados con éxito";
    }

    @Override
    public void deleteProducto(Long id_producto) {
        productRepo.deleteById(id_producto);
    
    }

    // Mejora la respuesta con un DTO
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
