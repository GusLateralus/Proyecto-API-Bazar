
package com.proyectofinal.bazar.dto;

import com.proyectofinal.bazar.model.Cliente;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class VentaDTO {
    
    private Long codigo_venta;
    private LocalDate fecha_venta;
    private Cliente unCliente;
    
    public VentaDTO(){
    
    }

    public VentaDTO(Long codigo_venta, LocalDate fecha_venta, Cliente unCliente) {
        this.codigo_venta = codigo_venta;
        this.fecha_venta = fecha_venta;
        this.unCliente = unCliente;
    }
    
    
    
}
