package pau.coderhouse.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pau.coderhouse.example.entity.Product;
import pau.coderhouse.example.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

//importamos el service
@Service
public class ProductService {

    //inyectamos el repository
    @Autowired //le indicamos que la propiedad va a ser inyectada
    private ProductRepository repository;

    public ProductService (ProductRepository repository) {
        this.repository = repository;
    }

    //hacemos el create para crear todos los productos
    public Product save(Product product) {
        return repository.save(product);
    }

    //creamos nuestros métodos para obtener todos los productos. Con list le indicamos que va a haber una lista de productos
    public List<Product> getProducts() {
        return repository.findAll();
    }

    //creamos un metodo para obtener un producto solo
    public Optional<Product> getById(Long id) {
        return repository.findById(id);
    }
}
