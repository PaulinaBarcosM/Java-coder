package pau.coderhouse.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pau.coderhouse.example.entities.Client;
import pau.coderhouse.example.repository.ClientRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public ClientService (ClientRepository repository) {
        this.clientRepository = repository;
    }

    public Client save(Client client) {
        return clientRepository.save(client);
    }

    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> getById(String id) {
        return clientRepository.findById(id);
    }

    public void deleteById(UUID id) {
    }
}

