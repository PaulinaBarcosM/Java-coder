package pau.coderhouse.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pau.coderhouse.example.entities.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, String> {
}
