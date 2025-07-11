
package com.proyectofinal.bazar.repository;

import com.proyectofinal.bazar.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iClienteRepository extends JpaRepository<Cliente, Long> {
    
}
