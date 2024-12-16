package pau.coderhouse.example.services;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pau.coderhouse.example.entities.Client;
import pau.coderhouse.example.entities.Invoice;
import pau.coderhouse.example.entities.Product;
import pau.coderhouse.example.repository.ClientRepository;
import pau.coderhouse.example.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private ClientRepository clientRepository;

    public ProductService (ProductRepository repository) {
        this.productRepository = repository;
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getById(Long id) {
        return productRepository.findById(id);
    }

    public Invoice purchaseAndInvoice(String clientId, Long productId, Double productPrice, int amount) throws Exception {
        Optional<Client> clientOpt = clientRepository.findById(clientId);
        Optional<Product> productOpt = productRepository.findById(productId);

        if (clientOpt.isEmpty()) {
            throw new BadRequestException("Customer not found");
        }

        if (productOpt.isEmpty()) {
            throw new BadRequestException("Product not found");
        }

        Product product = productOpt.get();
        if (product.getStock() < amount) {
            throw new BadRequestException("not enough stock for the product");
        }

        invoiceService.createInvoice(clientId, productId, productPrice, amount);

        return null;
    }
}
