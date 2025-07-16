package techlab.controller;


import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import techlab.entity.Pedido;
import techlab.services.PedidoService;

@RestController
@RequestMapping("/api/delivery")
@CrossOrigin
public class PedidoController {
    private final PedidoService pedidoService;

    @Autowired
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity createPedido(@RequestBody Pedido pedido) {
        return ResponseEntity.ok(this.pedidoService.createPedido(pedido));
    }

    @GetMapping
    public ResponseEntity getPedidos() {
        return ResponseEntity.ok(this.pedidoService.getPedidos());
    }

    @PutMapping("/{id}")
    public ResponseEntity updatePedido(@RequestBody Pedido pedido, @PathVariable int id){
        return ResponseEntity.ok(this.pedidoService.updatePedido(pedido, id));
    }

    @GetMapping("/{id}")
    public ResponseEntity getPedido(@PathVariable int id) {
        return ResponseEntity.ok(this.pedidoService.getPedido(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePedido(@PathVariable int id) {
        this.pedidoService.deletePedido(id);
        return ResponseEntity.noContent().build();
    }
}
