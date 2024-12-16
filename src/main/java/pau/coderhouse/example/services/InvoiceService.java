package pau.coderhouse.example.services;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pau.coderhouse.example.entities.Client;
import pau.coderhouse.example.entities.Invoice;
import pau.coderhouse.example.entities.Product;
import pau.coderhouse.example.repository.ClientRepository;
import pau.coderhouse.example.repository.InvoiceDetailsRepository;
import pau.coderhouse.example.repository.InvoiceRepository;
import pau.coderhouse.example.repository.ProductRepository;
import pau.coderhouse.example.entities.InvoiceDetails;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private InvoiceDetailsRepository invoiceDetailsRepository;

    public InvoiceService (InvoiceRepository invoiceRepository, InvoiceDetailsRepository invoiceDetailsRepository) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceDetailsRepository = invoiceDetailsRepository;
    }

    public Invoice save(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    public List<Invoice> getInvoice() {
        return invoiceRepository.findAll();
    }

    public Optional<Invoice> getById(String id) {
        return invoiceRepository.findById(id);
    }

    //lógica de la factura
    public Invoice createInvoice(String clientId, Long productId, Double productPrice, int amount) throws Exception{
        Optional<Client> clientOpt = clientRepository.findById(clientId);
        Optional<Product> productOpt = productRepository.findById(productId);

        if (clientOpt.isPresent() && productOpt.isPresent()) {
            Client client = clientOpt.get();
            Product product = productOpt.get();

            if (product.getStock() < amount) {
                throw new BadRequestException("Not enough stock available for this product");
            }

            product.setStock(product.getStock() - amount);
            productRepository.save(product);

            Double subtotal = productPrice * amount;

            Invoice invoice = new Invoice();
            invoice.setClient(client);
            invoice.setTotalAmount(subtotal);
            invoice.setDate(LocalDateTime.now());
            invoiceRepository.save(invoice);

            InvoiceDetails invoiceDetails = new InvoiceDetails();
            invoiceDetails.setInvoice(invoice);
            invoiceDetails.setProduct(product);
            invoiceDetails.setPrice(productPrice);
            invoiceDetails.setAmount(amount);
            invoiceDetails.setSubtotal(amount * productPrice);
            invoiceDetailsRepository.save(invoiceDetails);

            return invoice;
        } else {
            throw new BadRequestException("Client or Product not found");
        }
    }
}
