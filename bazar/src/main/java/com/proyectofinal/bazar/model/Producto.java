
package com.proyectofinal.bazar.model;

//import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long codigo_producto;
    private String nombre;
    private double costo;
    private double cantidad_disponible;
    
    @ManyToMany(mappedBy = "listaProductos")
    //@JsonManagedReference
    private List<Venta> ventas = new ArrayList<>();
    
    
    public Producto()
    {
        
    }

    public Producto(Long codigo_producto, String nombre, double costo, double cantidad_disponible,
                    List<Venta> ventas) {
        this.codigo_producto = codigo_producto;
        this.nombre = nombre;
        this.costo = costo;
        this.cantidad_disponible = cantidad_disponible;
        this.ventas = ventas;
    }
    
    
    
}
