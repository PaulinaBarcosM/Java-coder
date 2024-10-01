package java_coderhouse.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PRODUCT")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int id;

    @Column(name = "DESCRIPTION")
    @Getter
    @Setter
    private String description;

    @Column(name = "STOCK")
    @Getter
    @Setter
    private int stock;

    @Column(name = "PRICE")
    @Getter
    @Setter
    private String price;

    @Column(name = "COLORS")
    @Getter
    @Setter
    private String colors;

    @Column(name = "SIZES")
    @Getter
    @Setter
    private String sizes;
}
