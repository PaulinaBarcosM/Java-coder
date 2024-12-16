package pau.coderhouse.example.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "invoices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    @Schema(description = "Invoice ID", requiredMode = Schema.RequiredMode.AUTO, example = "00000000-0000-0000-0000-000000000000")
    private String id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    @JsonIgnoreProperties("client")
    @Schema(description = "Invoice client", requiredMode = Schema.RequiredMode.REQUIRED, example = "{\"dni\":\"123456789\",\"name\":\"Paulina\",\"lastname\":\"Barcos\",\"mail\":\"paulinabarcos@gmail.com\"}")
    private Client client;

    @Column(nullable = false)
    @Schema(description = "Invoice date", requiredMode = Schema.RequiredMode.REQUIRED, example = "2024-01-01T00:00:00")
    private LocalDateTime date;

    @Column(nullable = false)
    @Schema(description = "Total amount of the invoice", requiredMode = Schema.RequiredMode.REQUIRED, example = "150.000")
    private Double totalAmount;

    //relacion con invoice details
    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Schema(description = "List of invoice detail", requiredMode = Schema.RequiredMode.REQUIRED, example = "[{\"id\":\"00000000-0000-0000-0000-000000000000\",\"description\":\"Bermuda\",\"price\":25000,\"stock\":15}]")
    private List<InvoiceDetails> details;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public List<InvoiceDetails> getDetails() {
        return details;
    }

    public void setDetails(List<InvoiceDetails> details) {
        this.details = details;
    }
}
