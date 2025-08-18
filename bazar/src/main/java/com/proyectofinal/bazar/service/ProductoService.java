
package com.proyectofinal.bazar.service;

import com.proyectofinal.bazar.dto.ProductoDTO;
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
    public ProductoDTO saveProducto(ProductoDTO dto) {
        Producto producto = productRepo.findByNombre(dto.getNombre()).orElse(new Producto());
        
        producto.setNombre(dto.getNombre());
        producto.setCosto(dto.getCosto());
        
        // Si no es null, quiere decir que ya existía, entonces si es así, le sumamos la cantidad y actualizamos.
        if(producto.getCodigo_producto() != null){ 
            producto.setCantidad_disponible(producto.getCantidad_disponible()+dto.getCantidad_disponible());
        }
        else{
            producto.setCantidad_disponible(dto.getCantidad_disponible());  
        }
        
        Producto savedProduct = productRepo.save(producto); // Este es el que irá a la DB
        
        // Y creamos el objeto que vamos a retornar en el JSON
        ProductoDTO result = new ProductoDTO();
        result.setCodigo_producto(savedProduct.getCodigo_producto());
        result.setNombre(savedProduct.getNombre());
        result.setCosto(savedProduct.getCosto());
        result.setCantidad_disponible(savedProduct.getCantidad_disponible());
        
        return result;
    }

    
    @Override
    public ProductoDTO findProducto(Long id_producto) {
        Producto product = productRepo.findById(id_producto).orElse(null);
        
        if(product == null){
            
            return null;
        }
        else{
        
            ProductoDTO dto = new ProductoDTO();

            dto.setCodigo_producto(product.getCodigo_producto());
            dto.setNombre(product.getNombre());
            dto.setCosto(product.getCosto());
            dto.setCantidad_disponible(product.getCantidad_disponible());
            
            return dto;
        }
        
        
     }

    // Usa un DTO
    @Override
    public List<ProductoDTO> traerProductos() {
        List<Producto> listaProductos = productRepo.findAll();
        List<ProductoDTO> listaDTO = new ArrayList<>();
        
        
        for(Producto p: listaProductos){
            ProductoDTO dto = new ProductoDTO();
            dto.setCodigo_producto(p.getCodigo_producto());
            dto.setNombre(p.getNombre());
            dto.setCosto(p.getCosto());
            dto.setCantidad_disponible(p.getCantidad_disponible());
            
            listaDTO.add(dto);
        }
         
         
        
        return listaDTO;
               
     }

    // Bien, quizás podrías mejorarlo devolviendo el JSON
    @Override
    public String updateProducto(Producto product) {
        productRepo.save(product);
        return "Datos actualizados con éxito";
    }

    @Override
    public void deleteProducto(Long id_producto) {
        productRepo.deleteById(id_producto);
    
    }

    // Mejora la respuesta con un DTO
    @Override
    public List<ProductoDTO> productosDisponiblesMenores5() {
        
        List<Producto> listaProductos = productRepo.findAll();
        List<Producto> listaProductosDisponibles = new ArrayList<>();
        List<ProductoDTO> dtos = new ArrayList<>();
        
        
        for(Producto producto : listaProductos)
        {
            if(producto.getCantidad_disponible()<5)
            {
                listaProductosDisponibles.add(producto);
            }
        
        }
        
        for(Producto p:listaProductosDisponibles){
        
            ProductoDTO dto = new ProductoDTO();
            dto.setCodigo_producto(p.getCodigo_producto());
            dto.setNombre(p.getNombre());
            dto.setCosto(p.getCosto());
            dto.setCantidad_disponible(p.getCantidad_disponible());
            
            dtos.add(dto);
        }
        
        
        return dtos;
        
    }
    
}
