
package com.proyectofinal.bazar.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id_cliente;
    private String nombre;
    private String apellido;
    private String dni;
    
    @OneToMany(mappedBy="unCliente")
    private List<Venta> ventas = new ArrayList<>();
            
    public Cliente()
    {
    
    }

    public Cliente(Long id_cliente, String nombre, String apellido, String dni, List<Venta> ventas) {
        this.id_cliente = id_cliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.ventas = ventas;
    }
    
    
    
}
