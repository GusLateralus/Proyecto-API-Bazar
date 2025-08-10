
package com.proyectofinal.bazar.service;

import com.proyectofinal.bazar.model.Cliente;
import com.proyectofinal.bazar.repository.iClienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService implements iClienteService{
    
    @Autowired
    private iClienteRepository clienteRepo;
    

    // Asegúrate que no guardes al mismo cliente 2 veces, e igual, si quieres devuelve un JSON
    @Override
    public String saveCliente(Cliente clien) {
        clienteRepo.save(clien);
        return "Datos del cliente guardados con éxito";
    }

    // Mejora la respuesta con un DTO
    @Override
    public Cliente findCliente(Long id_cliente) {
        return clienteRepo.findById(id_cliente).orElse(null);
    }

    // Lo mismo, usa un DTO
    @Override
    public List<Cliente> traerClientes() {
        return clienteRepo.findAll();
        
    }

    // Podrías devolver un JSON
    @Override
    public String updateCliente(Cliente clien) {
        this.saveCliente(clien);
        return "Datos del cliente actualizados con éxito";
    }

    @Override
    public void deleteCliente(Long id_cliente) {
        clienteRepo.deleteById(id_cliente);
    }
    
}
