
package com.proyectofinal.bazar.repository;

import com.proyectofinal.bazar.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iProductoRepository extends JpaRepository<Producto, Long>{
    
}
