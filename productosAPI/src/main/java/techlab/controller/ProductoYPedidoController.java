package techlab.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import techlab.entity.ProductoYPedido;
import techlab.services.ProductoYPedidoService;

@RestController
@RequestMapping("/api/productanddelivery")
@CrossOrigin
public class ProductoYPedidoController {
    private final ProductoYPedidoService productoYPedidoService;

    @Autowired
    public ProductoYPedidoController(ProductoYPedidoService productoYPedidoService){
        this.productoYPedidoService = productoYPedidoService;
    }

    @GetMapping
    public ResponseEntity obtenerProductosYPedidos () {
        return ResponseEntity.ok(this.productoYPedidoService.obtenerProductosYPedidos());
    }


    @PostMapping
    public ResponseEntity agregarAlPedido(@RequestBody ProductoYPedido productoYPedido) {
        try {
            return ResponseEntity.ok(this.productoYPedidoService.agregarAlPedido(productoYPedido));
        } catch (Exception e) {
            return  new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/productsbydelivery/{pedidoId}")
    public ResponseEntity obtenerProductosDePedido(@PathVariable int pedidoId){
        return ResponseEntity.ok(this.productoYPedidoService.obtenerProductosDePedido(pedidoId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity borrarProductoYPedido(@PathVariable int id){
        try {
            this.productoYPedidoService.deleteProductoYPedido(id);
            return ResponseEntity.noContent().build();
        } catch ( Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
