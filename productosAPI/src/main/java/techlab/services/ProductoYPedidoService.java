package techlab.services;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import techlab.entity.*;
import techlab.repository.ProductoYPedidoRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoYPedidoService {

    private ProductoYPedidoRepository repositoryJpa;
    private PedidoService pedidoService;
    private ProductoService productoService;

    @Autowired
    ProductoYPedidoService(ProductoYPedidoRepository productoYPedidoRepository, PedidoService pedidoService,
                           ProductoService productoService){
        this.repositoryJpa = productoYPedidoRepository;
        this.pedidoService = pedidoService;
        this.productoService = productoService;
    }

    public List<ProductoYPedido> obtenerProductosYPedidos() {
        return this.repositoryJpa.findAll();
    }

    public ProductoYPedido agregarAlPedido(ProductoYPedido productoYPedido) throws Exception {
        Pedido pedido = this.pedidoService.getPedido(productoYPedido.getPedidoId());
        Producto producto = this.productoService.getProducto(productoYPedido.getProductoId());

        Integer currentStock = producto.getStock();

        if(currentStock >= 1) {
            Integer newStock = currentStock - 1;
            producto.setStock(newStock);
            this.productoService.updateProduct(producto, productoYPedido.getProductoId());
            Double currentTotalPrice = pedido.getTotalPrice() != null ? pedido.getTotalPrice() : 0;
            Double newTotalPrice = currentTotalPrice + producto.getPrice();
            pedido.setTotalPrice(newTotalPrice);
            this.pedidoService.updatePedido(pedido, productoYPedido.getPedidoId());

            return this.repositoryJpa.save(productoYPedido);
        } else {
            throw new Exception("No se pudo agregar el producto al pedido por falta de stock");
        }

    }

    public List<Producto> obtenerProductosDePedido(int id) {
        List<Producto> result = new ArrayList<>();
        this.repositoryJpa.findByPedidoId(id).forEach(
                productoYPedido ->  {
                    try {
                        Producto p = this.productoService.getProducto(productoYPedido.getProductoId());
                        result.add(p);
                    }catch (Exception e) {
                        System.out.println("Producto no encontrado");
                    }
                });
        return result;
    }

    public void deleteProductoYPedido(int id) throws Exception{
        if(this.repositoryJpa.existsById(id)) {
            this.repositoryJpa.deleteById(id);
        } else {
            throw new EntityNotFoundException("El item con id " + id + " no se encuentra");
        }
    }
}
