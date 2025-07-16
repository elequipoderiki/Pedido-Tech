package techlab.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import techlab.entity.Producto;
import techlab.repository.ProductoRepository;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    private ProductoRepository repositoryJpa;

    @Autowired
    public ProductoService(ProductoRepository repositoryJpa) {
        this.repositoryJpa = repositoryJpa;
    }

    public List<Producto> getProductos() {

        return this.repositoryJpa.findAll();
    }

    public Producto getProducto(int id) throws Exception {

        Optional<Producto> encontrado = this.repositoryJpa.findById(id);
        if(encontrado.isEmpty()) {
            throw new EntityNotFoundException("El item con id " + id + " no se encuentra");
        }
        return encontrado.get();

    }

    public Producto createProduct(Producto producto)  {
            return this.repositoryJpa.save(producto);
    }

    public Producto updateProduct(Producto productoUpdate, int id) throws Exception {

        Producto producto = this.repositoryJpa.findById(id).orElse(null);
        if ( producto != null) {
            if (productoUpdate.getTitle() != null)
                producto.setTitle(productoUpdate.getTitle());
            if (productoUpdate.getPrice() != null)
                producto.setPrice(productoUpdate.getPrice());
            if (productoUpdate.getCategory() != null)
                producto.setCategory(productoUpdate.getCategory());
            if (productoUpdate.getStock() != null)
                producto.setStock(productoUpdate.getStock());
            if (productoUpdate.getDescription() != null)
                producto.setDescription(productoUpdate.getDescription());
            if (productoUpdate.getImage() != null)
                producto.setImage(productoUpdate.getImage());

            return this.repositoryJpa.save(producto);
        }
        else {
            throw new EntityNotFoundException("El item con id " + id + " no se encuentra");
            //Exception("El item con id " + id + " no se encuentra");
        }
    }

    public void deleteProducto(int id) throws Exception {
        if (this.repositoryJpa.existsById(id)) {
            this.repositoryJpa.deleteById(id);
        } else {
            throw new EntityNotFoundException("El item con id " + id + " no se encuentra");
        }
    }
}
