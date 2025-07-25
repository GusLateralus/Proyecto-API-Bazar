
package com.proyectofinal.bazar.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

// Esta annotation embeddable dice que se usará esta clase como parte de otra entidad
// y sus atributos se convertirán en columnas. Es la tupla que será como primary key

// La ventaja principal de hacer esto, es que evitarás duplicados en la cantidad de ventas
@Embeddable
public class VentaProductoID implements Serializable {
    private Long codigoVenta;
    private Long codigoProducto;

    public VentaProductoID() {
    }

    public VentaProductoID(Long codigoVenta, Long codigoProducto) {
        this.codigoVenta = codigoVenta;
        this.codigoProducto = codigoProducto;
    }

    public Long getCodigoVenta() {
        return codigoVenta;
    }

    public void setCodigoVenta(Long codigoVenta) {
        this.codigoVenta = codigoVenta;
    }

    public Long getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(Long codigoProducto) {
        this.codigoProducto = codigoProducto;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.codigoVenta);
        hash = 71 * hash + Objects.hashCode(this.codigoProducto);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final VentaProductoID other = (VentaProductoID) obj;
        if (!Objects.equals(this.codigoVenta, other.codigoVenta)) {
            return false;
        }
        return Objects.equals(this.codigoProducto, other.codigoProducto);
    }
    
    
    
}
