
package com.proyectofinal.bazar.repository;

import com.proyectofinal.bazar.model.Cliente;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iClienteRepository extends JpaRepository<Cliente, Long> {
    
    Optional<Cliente> findByDni(String dni);
    
}
