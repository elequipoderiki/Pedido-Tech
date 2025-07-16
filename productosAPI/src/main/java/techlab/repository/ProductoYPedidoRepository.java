package techlab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import techlab.entity.Producto;
import techlab.entity.ProductoYPedido;

import java.util.List;

@Repository
public interface ProductoYPedidoRepository extends JpaRepository<ProductoYPedido, Integer> {
    List<ProductoYPedido> findByPedidoId(int id);
}

