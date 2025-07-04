
package com.proyectofinal.bazar.service;

import com.proyectofinal.bazar.dto.VentaProductClienteDTO;
import com.proyectofinal.bazar.model.Producto;
import com.proyectofinal.bazar.model.Venta;
import com.proyectofinal.bazar.repository.iVentaRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaService implements iVentaService{
    
    @Autowired
    private iVentaRepository ventaRepo;

    @Override
    public String saveVenta(Venta venta) {
        ventaRepo.save(venta);
        return "Datos de venta guardados con éxito";
    }

    @Override
    public Venta findVenta(Long id_venta) {
        return ventaRepo.findById(id_venta).orElse(null);
    }

    @Override
    public List<Venta> traerVentas() {
        return ventaRepo.findAll();
    }

    @Override
    public String updateVenta(Venta venta) {
        this.saveVenta(venta);
        return "Datos de venta actualizados con éxito";
    }

    @Override
    public void deleteVenta(Long id_venta) {
        ventaRepo.deleteById(id_venta);
    }

    // Traer los productos de una venta determinada:
    @Override
    public List<Producto> productosDeUnaVenta(Long id_venta) {
        return null;
        
        
        
    }

    @Override
    public String sumaMontoYVentas(LocalDate fecha_venta) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public VentaProductClienteDTO traerMontoMasAlto(List<Venta> listaVentas) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
