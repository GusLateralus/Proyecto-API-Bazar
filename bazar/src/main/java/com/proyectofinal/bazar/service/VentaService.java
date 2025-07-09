
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

    /*Obtener el codigo_venta, total, cantidad de productos, nombre del cliente,
    apellido del cliente de la venta con el monto más alto de todos.
    */
    @Override
    public VentaProductClienteDTO traerMontoMasAlto() {
        
        List<Venta> listaVentas = ventaRepo.findAll();
        VentaProductClienteDTO ventaDTO = new VentaProductClienteDTO();
        Venta ventaMaxima = null;
        

        double maxVenta = Double.MIN_VALUE;
        
        for(Venta venta : listaVentas)
        {
            if(venta.getTotal() > maxVenta)
            {
                maxVenta = venta.getTotal();
                ventaMaxima = venta;
            }
            
        }
        
        // Si no encuentra una ventaMaxima (porque no hay ventas) entonces arroja null
        if( ventaMaxima==null)
        {
            return null;
        }
        
        else{
        
        ventaDTO.setCodigo_venta(ventaMaxima.getCodigo_venta());
        ventaDTO.setCantidad_productos(ventaMaxima.getListaProductos().size());
        ventaDTO.setNombre_cliente(ventaMaxima.getUnCliente().getNombre());
        ventaDTO.setApellido_cliente(ventaMaxima.getUnCliente().getApellido());
        return ventaDTO;
        }
        
       
    }
    
}
