
package com.proyectofinal.bazar.repository;

import com.proyectofinal.bazar.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iVentaRepository extends JpaRepository<Venta, Long>{
    
}
