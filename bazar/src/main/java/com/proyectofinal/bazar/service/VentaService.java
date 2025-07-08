
package com.proyectofinal.bazar.service;

import com.proyectofinal.bazar.dto.ResumenVentasDTO;
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
        
        Venta venta = ventaRepo.findById(id_venta).orElse(null);
        
        if(venta != null)
        {
            return venta.getListaProductos();
        }
        else{
            return new ArrayList<>();
        }
    }

    // Obtener la sumatoria del monto y también la cantidad total de ventas de un determinado día
    @Override
    public ResumenVentasDTO sumaMontoYVentas(LocalDate fecha_venta) {
        
        List<Venta> ventasPorFecha = this.findVentaByDate(fecha_venta);
        ResumenVentasDTO montoYtotalVentas = new ResumenVentasDTO();
        double total=0;
        int cantidadVentas=ventasPorFecha.size();
        
        
        for(Venta venta : ventasPorFecha)
        {
            total+=venta.getTotal();
        
        }
        
        montoYtotalVentas.setTotal(total);
        montoYtotalVentas.setTotalVentas(cantidadVentas);
        
        
        return montoYtotalVentas;
    
    }

    @Override
    public List<Venta> findVentaByDate(LocalDate fecha_venta) {
        
        List<Venta> listaVentas = ventaRepo.findAll();
        List<Venta> ventasPorFecha = new ArrayList<>();
        
        for(Venta venta : listaVentas)
        {
            if(venta.getFecha_venta().isEqual(fecha_venta))
            {
                ventasPorFecha.add(venta);
            }
        
        }
        
        
        return ventasPorFecha;
    }

    
    @Override
    public VentaProductClienteDTO traerMontoMasAlto(List<Venta> listaVentas) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
