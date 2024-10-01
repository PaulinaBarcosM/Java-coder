package java_coderhouse.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.security.PrivateKey;
import java.time.LocalDate;

@Entity
@Table(name = "CLIENT")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int id;

    //Constructor
    public Client() {}

    public Client(String name, String lastname, int dni, LocalDate birth) {
        this.name = name;
        this.lastname = lastname;
        this.dni = dni;
        this.birth = birth;
    }

    //Definimos propiedades
    @Column(name = "NAME")
    @Getter
    @Setter
    private String name;

    @Column(name = "LASTNAME")
    @Getter
    @Setter
    private String lastname;

    @Column(name = "DNI")
    @Getter
    @Setter
    private int dni;

    @Column(name = "BIRTH")
    @Getter
    @Setter
    private LocalDate birth;
}
