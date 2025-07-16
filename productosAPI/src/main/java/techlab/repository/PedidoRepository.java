package techlab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import techlab.entity.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}
