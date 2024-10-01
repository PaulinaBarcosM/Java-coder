package java_coderhouse.jpa.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "SALE")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int id;

    @Column(name = "SALE_ID")
    @Getter
    @Setter
    private int sale_id;

    @Column(name = "FECHA")
    @Getter
    @Setter
    private LocalDate fecha;

    @Column(name = "PRODUCT_ID")
    @Getter
    @Setter
    private String product_id;

    @Column(name = "AMOUNT")
    @Getter
    @Setter
    private int amount;

    @Column(name = "TOTAL")
    @Getter
    @Setter
    private String total;
}
