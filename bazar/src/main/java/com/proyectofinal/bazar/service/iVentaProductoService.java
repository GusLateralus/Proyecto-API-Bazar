
package com.proyectofinal.bazar.service;

import com.proyectofinal.bazar.dto.ResumenVentasDTO;
import com.proyectofinal.bazar.dto.VentaProductClienteDTO;
import com.proyectofinal.bazar.model.Venta;
import com.proyectofinal.bazar.model.VentaProducto;
import java.time.LocalDate;
import java.util.List;


public interface iVentaProductoService {
    
    // Guardar ventaProducto, cuando hagamos esto, ahora sí se genera la venta.
    public VentaProducto saveVentaProducto(VentaProducto ventaProducto);
    
    // Obtener la sumatoria del monto y también la cantidad total de ventas de un determinado día 
    public List<Venta> findVentaByDate(LocalDate fecha_venta);
    
    public ResumenVentasDTO sumaMontoYVentas(LocalDate fecha_venta);
    
    /*Obtener el codigo_venta, total, cantidad de productos, nombre del cliente,
    apellido del cliente de la venta con el monto más alto de todos.
    */
    public VentaProductClienteDTO traerMontoMasAlto();
    
    
    
}
