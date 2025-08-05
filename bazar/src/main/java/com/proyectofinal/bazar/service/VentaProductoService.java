package com.proyectofinal.bazar.service;

import com.proyectofinal.bazar.dto.ResumenVentasDTO;
import com.proyectofinal.bazar.dto.VentaProductClienteDTO;
import com.proyectofinal.bazar.model.Producto;
import com.proyectofinal.bazar.model.Venta;
import com.proyectofinal.bazar.model.VentaProducto;
import com.proyectofinal.bazar.model.VentaProductoID;
import com.proyectofinal.bazar.repository.iProductoRepository;
import com.proyectofinal.bazar.repository.iVentaProductoRepository;
import com.proyectofinal.bazar.repository.iVentaRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
//import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaProductoService implements iVentaProductoService{
    
    @Autowired
    private iVentaProductoRepository ventaProductoRepo;
    
    @Autowired
    private iVentaRepository ventaRepo;
    
    @Autowired
    private iProductoRepository productRepo;
    
    
    @Override
    public VentaProducto saveVentaProducto(VentaProducto ventaProducto) {
        // Validamos el producto:
        Long idProducto = ventaProducto.getProducto().getCodigo_producto();
        Producto producto = productRepo.findById(idProducto).orElse(null);
        
        if(producto.getCantidad_disponible() < ventaProducto.getCantidadVendida())
        {
            throw new RuntimeException("Stock insuficiente para el producto: "+ producto.getNombre());
        }
        
        // Validamos para guardar la venta:
        Venta venta = ventaProducto.getVenta();
        
        if(venta.getCodigo_venta()==null)
        {
            ventaRepo.save(venta);
        }
        
        // Calculamos el total y seteamos relaciones completas
        ventaProducto.setProducto(producto);
        ventaProducto.setVenta(venta);
        ventaProducto.setPrecioUnitario(producto.getCosto());
        ventaProducto.setTotal(producto.getCosto()*ventaProducto.getCantidadVendida());
        
        // Creamos la tupla (clave compuesta):
        VentaProductoID id = new VentaProductoID(venta.getCodigo_venta(),producto.getCodigo_producto());
        ventaProducto.setId(id);
        
        
        // Guardamos el VentaProducto, entendiendo que se completó la compra
        ventaProductoRepo.save(ventaProducto);
        
        // Y actualizamos stock:
        producto.setCantidad_disponible(producto.getCantidad_disponible()-ventaProducto.getCantidadVendida());
        productRepo.save(producto);
       
        return ventaProducto;
    }
    
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
    
        List<Venta> listaVentas = ventaRepo.findAll();
        VentaProductClienteDTO ventaDTO = new VentaProductClienteDTO();
        Venta ventaMaxima = null;
        double montoMaximo= 0;
        //List<Venta> listaVentas = new ArrayList<>();
        
        //double maxVenta = Double.MIN_VALUE;
        
        // Capturamos todas las ventas:
        for(Venta venta : listaVentas)
        {
            double totalVenta = 0;
            
            for(VentaProducto vp : venta.getListaProductos())
            {
                totalVenta += vp.getTotal();
            }
            
            if(totalVenta > montoMaximo)
            {
                montoMaximo = totalVenta;
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
        ventaDTO.setTotal(montoMaximo);
        ventaDTO.setCantidad_productos(ventaMaxima.getListaProductos().size());
        ventaDTO.setNombre_cliente(ventaMaxima.getUnCliente().getNombre());
        ventaDTO.setApellido_cliente(ventaMaxima.getUnCliente().getApellido());
        return ventaDTO;
        }
    
    }

    
    
}
