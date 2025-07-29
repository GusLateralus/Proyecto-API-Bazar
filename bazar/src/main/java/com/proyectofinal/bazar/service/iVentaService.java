
package com.proyectofinal.bazar.service;

//import com.proyectofinal.bazar.dto.ResumenVentasDTO;
//import com.proyectofinal.bazar.dto.VentaProductClienteDTO;
import com.proyectofinal.bazar.model.Producto;
import com.proyectofinal.bazar.model.Venta;
//import java.time.LocalDate;
import java.util.List;


public interface iVentaService {
    
    // Create
    public String saveVenta(Venta venta);
    
    // Read
    // Una sola venta:
    public Venta findVenta(Long id_venta);
    
    // Muchos clientes:
    public List<Venta> traerVentas();
    
    //Update
    public String updateVenta(Venta venta);
    
    // Delete
    public void deleteVenta(Long id_venta); 
    
    // Lista de productos de una determinada venta:
    public List<Producto> productosDeUnaVenta(Long id_venta);
    
    // Suma de monto y total de ventas:
    //public List<Venta> findVentaByDate(LocalDate fecha_venta);
    
    //public ResumenVentasDTO sumaMontoYVentas(LocalDate fecha_venta);
    
    // Atributos del DTO de la venta con el monto más alto
    //public VentaProductClienteDTO traerMontoMasAlto();
    
}
