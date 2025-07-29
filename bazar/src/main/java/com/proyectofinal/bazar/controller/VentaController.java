
package com.proyectofinal.bazar.controller;

//import com.proyectofinal.bazar.dto.ResumenVentasDTO;
//import com.proyectofinal.bazar.dto.VentaProductClienteDTO;
import com.proyectofinal.bazar.model.Producto;
import com.proyectofinal.bazar.model.Venta;
import com.proyectofinal.bazar.service.iVentaService;
//import java.time.LocalDate;
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
public class VentaController {
    
    @Autowired
    iVentaService ventaServ;
    
    // Crear venta
    @PostMapping("ventas/crear")
    public String saveVenta(@RequestBody Venta venta)
    {
        return ventaServ.saveVenta(venta);
    }
    
    // Traer una venta
    @GetMapping("ventas/traer/{id_venta}")
    public Venta traerVenta(@PathVariable Long id_venta)
    {
        return ventaServ.findVenta(id_venta);
    }
    
    // Traer lista de ventas
    @GetMapping("ventas/traer")
    public List<Venta> traerListaVentas()
    {
        return ventaServ.traerVentas();
    }
    
    // Editar una venta:
    @PutMapping("ventas/editar")
    public String editVenta(@RequestBody Venta venta)
    {
        return ventaServ.updateVenta(venta);
    }
    
    // Eliminar una venta:
    @DeleteMapping("ventas/eliminar/{id_venta}")
    public void deleteVenta(@PathVariable Long id_venta)
    {
        ventaServ.deleteVenta(id_venta);
    }
    
    // Lista de productos de una determinada venta:
    @GetMapping("ventas/productos/{id_venta}")
    public List<Producto> traerProductosDeUnaVenta(@PathVariable Long id_venta)
    {
        return ventaServ.productosDeUnaVenta(id_venta);
    }
    
    // Suma de monto y total de ventas por una fecha determinada:
    /*@GetMapping("ventas/{fecha_venta}")
    public ResumenVentasDTO traerSumaMontoYVentas(@PathVariable LocalDate fecha_venta)
    {
        return ventaServ.sumaMontoYVentas(fecha_venta);
    }
    
    // Atributos del DTO de la venta con el monto más alto:
    @GetMapping("ventas/venta_mayor")
    public VentaProductClienteDTO traerVentaMayor()
    {
        return ventaServ.traerMontoMasAlto();
    }*/
    
    
}
