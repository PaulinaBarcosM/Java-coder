package java_coderhouse.jpa.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ADDRESS")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int id;

    @Column(name = "STREET")
    @Getter
    @Setter
    private String street;

    @Column(name = "NUMBER")
    @Getter
    @Setter
    private int number;

    @Column(name = "FLOOR")
    @Getter
    @Setter
    private int floor;

    @Column(name = "APARTMENT")
    @Getter
    @Setter
    private String apartment;

    @Column(name = "CP")
    @Getter
    @Setter
    private String cp;

    @Column(name = "PROVINCE")
    @Getter
    @Setter
    private String province;

    @Column(name = "CITY")
    @Getter
    @Setter
    private String city;
}
