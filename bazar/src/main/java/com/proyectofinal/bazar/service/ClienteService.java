
package com.proyectofinal.bazar.service;

import com.proyectofinal.bazar.dto.ClienteDTO;
import com.proyectofinal.bazar.exceptions.ClienteAlreadyExistsException;
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
    public ClienteDTO saveCliente(ClienteDTO dto) {
        // Usamos un método del repo para encontrar el cliente por nombre, si no lo encuentra, entonces creas una nueva instancia
        Cliente clienExists = clienteRepo.findByDni(dto.getDni()).orElse(null);
        
        if(clienExists != null){
            // Aquí quizás debas arrojar una excepción o un status code personalizado, para indicar que ya existe el registro.
            throw new ClienteAlreadyExistsException("Cliente con ID: "+clienExists.getId_cliente()+" ya existe");
        
        }
        else{
            Cliente clien = new Cliente();
            clien.setNombre(dto.getNombre());
            clien.setApellido(dto.getApellido());
            clien.setDni(dto.getDni());
            
            
            clienteRepo.save(clien);

            dto.setId_cliente(clien.getId_cliente());

            return dto;
        }
        
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
        clienteRepo.save(clien);
        return "Datos del cliente actualizados con éxito";
    }

    @Override
    public void deleteCliente(Long id_cliente) {
        clienteRepo.deleteById(id_cliente);
    }
    
}
