
package com.proyectofinal.bazar.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ClienteDTO {
    
    private Long id_cliente;
    private String nombre;
    private String apellido;
    private String dni;
    
    public ClienteDTO(){
    
    }
    
    public ClienteDTO(Long id_cliente, String nombre, String apellido, String dni)
    {
        this.id_cliente = id_cliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
    }
    
    
    
}
