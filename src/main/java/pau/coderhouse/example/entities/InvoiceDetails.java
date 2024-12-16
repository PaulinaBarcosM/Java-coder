package pau.coderhouse.example.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "invoice_details")
@Getter
@Setter
public class InvoiceDetails {

    public InvoiceDetails() {
    }

    public InvoiceDetails(Invoice invoice, Integer amount, Product product, double price, double subtotal) {
        this.invoice = invoice;
        this.amount = amount;
        this.product = product;
        this.price = price;
        this.subtotal = amount * price;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    @Schema(description = "Invoice details ID", requiredMode = Schema.RequiredMode.AUTO, example = "00000000-0000-0000-0000-000000000000")
    private String id;

    @ManyToOne //relacion con el invoice
    @JoinColumn(name = "invoice_id", nullable = false)
    @Schema(description = "Invoice ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "00000000-0000-0000-0000-000000000000")
    private Invoice invoice;

    @Column(nullable = false)
    @Schema(description = "Total amount of the invoice", requiredMode = Schema.RequiredMode.REQUIRED, example = "150.000")
    private Integer amount;

    //relacion con el product id
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    @Schema(description = "Product ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Product product;

    @Column(nullable = false)
    @Schema(description = "Invoice price", requiredMode = Schema.RequiredMode.REQUIRED, example = "20.00")
    private double price;

    @Column(nullable = false)
    @Schema(description = "Invoice subtotal", requiredMode = Schema.RequiredMode.REQUIRED, example = "300.000")
    private double subtotal;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
}
