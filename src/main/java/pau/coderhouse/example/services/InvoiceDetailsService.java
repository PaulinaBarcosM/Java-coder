package pau.coderhouse.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pau.coderhouse.example.entities.Invoice;
import pau.coderhouse.example.entities.InvoiceDetails;
import pau.coderhouse.example.entities.Product;
import pau.coderhouse.example.repository.InvoiceDetailsRepository;
import pau.coderhouse.example.repository.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InvoiceDetailsService {

    @Autowired
    private InvoiceDetailsRepository invoiceDetailsRepository;

    @Autowired
    private ProductRepository productRepository;

    public InvoiceDetailsService(InvoiceDetailsRepository repository) {
       this.invoiceDetailsRepository = repository;
    }

    public InvoiceDetails save(InvoiceDetails details) {
        return invoiceDetailsRepository.save(details);
    }

    public List<InvoiceDetails> getAllDetails() {
        return invoiceDetailsRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public List<InvoiceDetails> findByInvoiceId(UUID invoiceId) {
        return null;
    }
}
