
package com.proyectofinal.bazar.service;

//import com.proyectofinal.bazar.dto.ResumenVentasDTO;
//import com.proyectofinal.bazar.dto.VentaProductClienteDTO;
import com.proyectofinal.bazar.model.Producto;
import com.proyectofinal.bazar.model.Venta;
import com.proyectofinal.bazar.model.VentaProducto;
//import com.proyectofinal.bazar.repository.iProductoRepository;
//import com.proyectofinal.bazar.repository.iVentaProductoRepository;
import com.proyectofinal.bazar.repository.iVentaRepository;
//import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
//import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaService implements iVentaService{
    
    @Autowired
    private iVentaRepository ventaRepo;
    
    /*@Autowired
    private iProductoRepository productRepo;
    
    @Autowired
    private iVentaProductoRepository ventaProductRepo; */
    

    @Override
    public Venta saveVenta(Venta venta) {
        // Implementamos la funcionalidad para reducir el stock de los productos vendidos.
        // La lista de productos que viene es la del JSON, por lo que, cada producto viene como un objeto incompleto
        // Está bien, la idea es llenar con puros IDs, pero entonces debes traer el objeto completo para operar bien
        /*List<VentaProducto> listaProductosIntermedia = venta.getListaProductos();
        List<String> productosNoDisponibles;
        productosNoDisponibles = new ArrayList<>(); // Sólo así se quitó el warning
        List<Producto> productosVendidos; // Lista para productos vendidos
        productosVendidos = new ArrayList<>();
        
        
        for(VentaProducto elemento : listaProductosIntermedia)
        {
            // Traemos todo el objeto en una colección para poder operar con él
            Optional<Producto> productoEnBD = productRepo.findById(elemento.getProducto().getCodigo_producto());
            
            if(productoEnBD.isPresent())
            {
                Producto p = productoEnBD.get();
                //System.out.println("Cantidad disponible del producto: "+product.getCantidad_disponible());
                // Imprime 0.0, es porque no traes el objeto completo, por eso siempre entra en el if y parece que no hace nada
                if(p.getCantidad_disponible()<=0)
                {
                    // Aquí la idea es mostrar el listado o un mensaje con los productos no disponibles
                    productosNoDisponibles.add(p.getNombre());

                }

                else
                { 
                    // Si están disponibles, actualizamos el stock.
                    p.setCantidad_disponible(p.getCantidad_disponible()-elemento.getCantidadVendida());
                    productRepo.save(p);
                    productosVendidos.add(p);
                    
                    // Ahora toca actualizar la información en venta producto
                    elemento.setVenta(venta);
                    elemento.setProducto(p);
                    elemento.setCantidadVendida(elemento.getCantidadVendida());
                    ventaProductRepo.save(elemento);
                }
            }
            
            else
            {
                productosNoDisponibles.add("Producto con ID"+elemento.getProducto().getCodigo_producto()+"no encontrado");
                
            }
        }
        
        venta.setListaProductos(listaProductosIntermedia);
        
        
        // Podrías retornar un JSON, eso creo que serviría como si fuera tu ticket de compra 
        ventaRepo.save(venta);
        
        if(productosNoDisponibles.isEmpty()){
        
            return "Datos de venta guardados con éxito";
        }
        else
        {
            return "Venta registrada parcialmente. Sin stock: "+String.join(", ", productosNoDisponibles);
        }*/
        return ventaRepo.save(venta);
        //return "Venta guardada con éxito";
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
        List<Producto> listaProductos = new ArrayList<>();
        
        if(venta != null)
        {
            for(VentaProducto elemento : venta.getListaProductos())
            {
                Producto product = elemento.getProducto();
                listaProductos.add(product);
            
            }
            
            return listaProductos;
        }
        
        else{
            return new ArrayList<>();
        }
        
    }

    // Obtener la sumatoria del monto y también la cantidad total de ventas de un determinado día
    /*@Override
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
    
    }*/

    /*@Override
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
    }*/

    /*Obtener el codigo_venta, total, cantidad de productos, nombre del cliente,
    apellido del cliente de la venta con el monto más alto de todos.
    */
    
    /*@Override
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
        ventaDTO.setTotal(maxVenta);
        ventaDTO.setCantidad_productos(ventaMaxima.getListaProductos().size());
        ventaDTO.setNombre_cliente(ventaMaxima.getUnCliente().getNombre());
        ventaDTO.setApellido_cliente(ventaMaxima.getUnCliente().getApellido());
        return ventaDTO;
        }
        
       
    }*/
    
}
