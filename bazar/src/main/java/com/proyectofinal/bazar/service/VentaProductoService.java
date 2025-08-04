package com.proyectofinal.bazar.service;

import com.proyectofinal.bazar.dto.ResumenVentasDTO;
import com.proyectofinal.bazar.dto.VentaProductClienteDTO;
import com.proyectofinal.bazar.model.Venta;
import com.proyectofinal.bazar.model.VentaProducto;
import com.proyectofinal.bazar.repository.iVentaProductoRepository;
//import com.proyectofinal.bazar.repository.iVentaRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaProductoService implements iVentaProductoService{
    
    @Autowired
    private iVentaProductoRepository ventaProductoRepo;
    
    
    @Override
    public VentaProducto saveVentaProducto(VentaProducto ventaProducto) {
        return ventaProductoRepo.save(ventaProducto);
    }
    // Este método puede tener problemas de duplicados
    // Puedes utilizar un Set para controlar el no meter una misma venta si tiene más de 1 producto
    @Override
    public List<Venta> findVentaByDate(LocalDate fecha_venta) {
        List<VentaProducto> listaVentasProducto = ventaProductoRepo.findAll();
        Set<Venta> ventasPorFecha = new HashSet<>();
        //Venta venta;
        
        for(VentaProducto vp : listaVentasProducto)
        {
            if(vp.getVenta().getFecha_venta().isEqual(fecha_venta))
            {
                //venta = vp.getVenta();
                
                ventasPorFecha.add(vp.getVenta());
            }
        
        }
        
        
        return new ArrayList<>(ventasPorFecha);
        
    }

    @Override
    public ResumenVentasDTO sumaMontoYVentas(LocalDate fecha_venta) {
        
        List<Venta> ventasPorFecha = this.findVentaByDate(fecha_venta);
        ResumenVentasDTO montoYtotalVentas = new ResumenVentasDTO();
        double monto=0;
        int cantidadTotalVentas=ventasPorFecha.size();
        //List<VentaProducto> listaTablaIntermedia;
        
        
        for(Venta venta : ventasPorFecha)
        {
            //listaTablaIntermedia = venta.getListaProductos();
            
            for(VentaProducto vp : venta.getListaProductos()){
            
                monto+=vp.getTotal();
            
            }
            
        }
        
        montoYtotalVentas.setMonto(monto);
        montoYtotalVentas.setCantidadTotalVentas(cantidadTotalVentas);
        
        return montoYtotalVentas;
    }

    @Override
    public VentaProductClienteDTO traerMontoMasAlto() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    
}
