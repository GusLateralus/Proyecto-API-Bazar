
package com.proyectofinal.bazar.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductoDTO {
    
    private Long codigo_producto;
    private String nombre;
    private double costo;
    private double cantidad_disponible;
    
    public ProductoDTO(){
        
    }

    public ProductoDTO(Long codigo_producto, String nombre, double costo, double cantidad_disponible) {
        this.codigo_producto = codigo_producto;
        this.nombre = nombre;
        this.costo = costo;
        this.cantidad_disponible = cantidad_disponible;
    }
    
    
    
    
}
