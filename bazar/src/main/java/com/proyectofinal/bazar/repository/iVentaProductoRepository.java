
package com.proyectofinal.bazar.repository;

import com.proyectofinal.bazar.model.VentaProducto;
import com.proyectofinal.bazar.model.VentaProductoID;
import org.springframework.data.jpa.repository.JpaRepository;


public interface iVentaProductoRepository extends JpaRepository<VentaProducto, VentaProductoID> {
    
}
