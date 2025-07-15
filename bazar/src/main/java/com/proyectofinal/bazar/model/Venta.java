
package com.proyectofinal.bazar.model;

//import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long codigo_venta;
    private LocalDate fecha_venta;
    private double total;
    // Relación N a N porque así un producto puede estar en muchas ventas
    @ManyToMany
    @JoinTable(
        name = "venta_producto",
        joinColumns = @JoinColumn(name = "codigo_venta"),
        inverseJoinColumns = @JoinColumn(name = "codigo_producto")
    )
    //@JsonManagedReference // Se pone del lado del padre o el que controla la relación, ayuda con la serialización de los JSON
    private List<Producto> listaProductos; 
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    //@JsonBackReference // Se pone del lado del hijo, el que debe evitar serializarse para no tener un ciclo.
    private Cliente unCliente;
   
    
    public Venta()
    {
    
    }

    public Venta(Long codigo_venta, LocalDate fecha_venta, double total, List<Producto> listaProductos, Cliente unCliente) {
        this.codigo_venta = codigo_venta;
        this.fecha_venta = fecha_venta;
        this.total = total;
        this.listaProductos = listaProductos;
        this.unCliente = unCliente;
    }
    
    
    
}
