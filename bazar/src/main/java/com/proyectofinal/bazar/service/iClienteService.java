
package com.proyectofinal.bazar.service;

import com.proyectofinal.bazar.model.Cliente;
import java.util.List;


public interface iClienteService {

    // Create
    public String saveCliente(Cliente clien);
    
    // Read
    // Un solo cliente:
    public Cliente findCliente(Long id_cliente);
    
    // Muchos clientes:
    public List<Cliente> traerClientes();
    
    //Update
    public String updateCliente(Cliente clien);
    
    // Delete
    public void deleteCliente(Long id_cliente); 

    
}
