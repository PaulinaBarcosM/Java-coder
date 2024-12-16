package pau.coderhouse.example.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Schema(description = "Product ID", requiredMode = Schema.RequiredMode.AUTO, example = "1")
    private Long id;

    @Column(name = "description", nullable = false)
    @Schema(description = "Product description", requiredMode = Schema.RequiredMode.REQUIRED, example = "Striped shirt")
    private String description;

    @Column(name = "stock", nullable = false)
    @Schema(description = "Product stock", requiredMode = Schema.RequiredMode.REQUIRED, example = "13")
    private double stock;

    @Column(name = "price", nullable = false)
    @Schema(description = "Product price", requiredMode = Schema.RequiredMode.REQUIRED, example = "$40000")
    private double price;

    @Column(name = "colors", nullable = false)
    @Schema(description = "Product colors", requiredMode = Schema.RequiredMode.REQUIRED, example = "BLACK-BLUE-LIGHTBLUE")
    private String colors;

    @Column(name = "sizes", nullable = false)
    @Schema(description = "Product sizes", requiredMode = Schema.RequiredMode.REQUIRED, example = "S-M-L")
    private String sizes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getColors() {
        return colors;
    }

    public void setColors(String colors) {
        this.colors = colors;
    }

    public String getSizes() {
        return sizes;
    }

    public void setSizes(String sizes) {
        this.sizes = sizes;
    }
}
