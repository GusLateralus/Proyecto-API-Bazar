
package com.proyectofinal.bazar.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ResumenVentasDTO {
    
    private double monto;
    private int cantidadTotalVentas;
    
    public ResumenVentasDTO()
    {
    
    }

    public ResumenVentasDTO(double monto, int cantidadTotalVentas) {
        this.monto = monto;
        this.cantidadTotalVentas = cantidadTotalVentas;
    }
    
    
    
}
