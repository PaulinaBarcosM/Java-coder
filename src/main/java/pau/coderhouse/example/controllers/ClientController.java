package pau.coderhouse.example.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pau.coderhouse.example.dto.ErrorResponseDto;
import pau.coderhouse.example.entities.Client;
import pau.coderhouse.example.services.ClientService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Operation(summary = "Creation a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client created"),
            @ApiResponse(responseCode = "400", description = "Invalid client"),
    })
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Client>> findAll() {
        List<Client> clients = clientService.getClients();
        return ResponseEntity.ok(clients);
    }

    @Operation
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client ID found"),
            @ApiResponse(responseCode = "400", description = "Invalid client")
    })
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Optional<Client>> getById(@PathVariable String id) {
        Optional<Client> client = clientService.getById(id);
        if (client.isPresent()) {
            return ResponseEntity.ok(client);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "We create a new client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "client created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid client",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}
            )
    })
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Client> create(@RequestBody Client client) {
        try {
            Client newClient = clientService.save(client);
            return ResponseEntity.ok(newClient);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Delete client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client successfully deleted"),
            @ApiResponse(responseCode = "400", description = "Invalid deletion")
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        try{
            clientService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
