
package com.proyectofinal.bazar.controller;

import com.proyectofinal.bazar.dto.ResumenVentasDTO;
import com.proyectofinal.bazar.dto.VentaProductClienteDTO;
import com.proyectofinal.bazar.model.VentaProducto;
import com.proyectofinal.bazar.service.iVentaProductoService;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VentaProductoController {
    
    @Autowired
    private iVentaProductoService ventaProductService;
    
    @PostMapping("ventaproducto/guardar")
    public VentaProducto saveVentaProducto(@RequestBody VentaProducto ventaProducto)
    {
        return ventaProductService.saveVentaProducto(ventaProducto);
    }
    
    @GetMapping("ventaproducto/{fecha_venta}")
    public ResumenVentasDTO traerSumaMontoYVentas(@PathVariable LocalDate fecha_venta)
    {
        return ventaProductService.sumaMontoYVentas(fecha_venta);
    }
    
    @GetMapping("ventaproducto/venta_mayor")
    public VentaProductClienteDTO traerVentaMayor()
    {
        return ventaProductService.traerMontoMasAlto();
    }
    
}
