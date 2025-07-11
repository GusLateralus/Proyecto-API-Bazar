
package com.proyectofinal.bazar.controller;

import com.proyectofinal.bazar.model.Cliente;
import com.proyectofinal.bazar.service.iClienteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClienteController {
    
    @Autowired
    iClienteService clienteServ;
    
    // Crear cliente
    @PostMapping("clientes/crear")
    public String saveCliente(@RequestBody Cliente cliente)
    {
        return clienteServ.saveCliente(cliente);
    }
    
    // Traer un cliente
    @GetMapping("clientes/traer/{id_cliente}")
    public Cliente traerCliente(@PathVariable Long id_cliente)
    {
        return clienteServ.findCliente(id_cliente);
    }
    
    // Traer lista de clientes
    @GetMapping("clientes/traer")
    public List<Cliente> traerListaClientes()
    {
        return clienteServ.traerClientes();
    }
    
    // Editar un cliente:
    @PutMapping("clientes/editar")
    public String editCliente(@RequestBody Cliente cliente)
    {
        return clienteServ.updateCliente(cliente);
    }
    
    // Eliminar un cliente:
    @DeleteMapping("clientes/eliminar/{id_cliente}")
    public void deleteCliente(@PathVariable Long id_cliente)
    {
        clienteServ.deleteCliente(id_cliente);
    }
    
    
    
}
