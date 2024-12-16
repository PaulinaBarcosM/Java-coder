package pau.coderhouse.example.repository;

import org.springframework.stereotype.Repository;
import pau.coderhouse.example.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {}
