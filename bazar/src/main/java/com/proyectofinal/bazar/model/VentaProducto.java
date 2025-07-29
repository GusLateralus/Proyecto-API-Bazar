// Esta entidad será ahora la intermedia entre venta y productos, contendrá la cantidad vendida
// para cada producto
package com.proyectofinal.bazar.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class VentaProducto {
    @EmbeddedId
    private VentaProductoID id;
    @ManyToOne
    @MapsId("codigoVenta") // Se usa para indicar que un campo de la tupla también está mapeado por ManyToOne, usas el nombre literal del campo de la clase de la tupla
    @JoinColumn(name="codigo_venta")
    private Venta venta;
    
    @ManyToOne
    @MapsId("codigoProducto")
    @JoinColumn(name = "codigo_producto")
    private Producto producto;
    
    private int cantidadVendida;
    
    // Copia  del precio unitario
    private double precioUnitario;
    
    // Total agregado
    private double total;

    public VentaProducto() {
    }

    public VentaProducto(VentaProductoID id, Venta venta, Producto producto, int cantidadVendida, double precioUnitario) {
        this.id = id;
        this.venta = venta;
        this.producto = producto;
        this.cantidadVendida = cantidadVendida;
        this.precioUnitario = precioUnitario;
        this.total = cantidadVendida*precioUnitario;
    }
    
    
    
}
