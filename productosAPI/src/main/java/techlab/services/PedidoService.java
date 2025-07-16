package techlab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import techlab.entity.Pedido;
import techlab.repository.PedidoRepository;

import java.util.List;

@Service
public class PedidoService {
    private PedidoRepository repositoryJpa;

    @Autowired
    public PedidoService(PedidoRepository pedidoRepository){
        this.repositoryJpa = pedidoRepository;
    }

    public Pedido createPedido(Pedido pedido) {

        return repositoryJpa.save(pedido);
    }

    public List<Pedido> getPedidos() {
        return this.repositoryJpa.findAll();
    }

    public Pedido updatePedido(Pedido pedidoUdate, int id) {
        Pedido pedido = this.repositoryJpa.findById(id).orElse(null);
        if (pedido != null) {
            if(pedidoUdate.getTotalPrice() >= 0){
                pedido.setTotalPrice(pedidoUdate.getTotalPrice());
            }
            if(pedidoUdate.getCustomer() != null && !pedidoUdate.getCustomer().isEmpty()) {
                pedido.setCustomer(pedidoUdate.getCustomer());
            }
            return this.repositoryJpa.save(pedido);
        }
        return pedido;
    }

    public Pedido getPedido(int id) {
        Pedido pedido = this.repositoryJpa.findById(id).orElse(null);
        return pedido;
    }

    public void deletePedido(int id) {
        if(this.repositoryJpa.existsById(id)) {
            this.repositoryJpa.deleteById(id);
        }
    }
}


