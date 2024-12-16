package pau.coderhouse.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pau.coderhouse.example.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
}
