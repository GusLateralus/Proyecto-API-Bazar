
package com.proyectofinal.bazar.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ResumenVentasDTO {
    
    private double total;
    private int totalVentas;
    
    public ResumenVentasDTO()
    {
    
    }

    public ResumenVentasDTO(double total, int totalVentas) {
        this.total = total;
        this.totalVentas = totalVentas;
    }
    
    
    
}
