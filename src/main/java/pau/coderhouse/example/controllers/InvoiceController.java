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
import pau.coderhouse.example.entities.Invoice;
import pau.coderhouse.example.services.InvoiceService;
import pau.coderhouse.example.services.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private ProductService productService;

    public InvoiceController(InvoiceService service) {
        this.invoiceService = service;
    }

    @Operation(summary = "We create the invoice")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Invoice created"),
            @ApiResponse(responseCode = "400", description = "Invalid invoice")
    })
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Invoice>> findAll() {
        List<Invoice> invoices = invoiceService.getInvoice();
        return ResponseEntity.ok(invoices);
    }

    @Operation(summary = "Searching for the invoice by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Invoice found successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid invoice")
    })
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Optional<Invoice>> getById(@PathVariable String id) {
        Optional<Invoice> invoice = invoiceService.getById(id);
        if (invoice.isPresent()) {
            return ResponseEntity.ok(invoice);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Do we create a new invoice")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Invoice creates successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid invoice",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}
            )
    })
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Invoice> create(@RequestBody Invoice invoice) {
        try {
            Invoice newInvoice = invoiceService.save(invoice);
            return ResponseEntity.ok(newInvoice);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Product purchase and invoice generation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The operation was completed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid operation")
    })
    //endpoint para comprar un producto y generar la factura
    @PostMapping("/{clientId}/purchase/{productId}")
    public ResponseEntity<Invoice> purchaseAndInvoice(
            @PathVariable String clientId,
            @PathVariable Long productId,
            @RequestParam Double productPrice,
            @RequestParam int amount) throws Exception{

        try {
            //llamamos al servicio para generar la factura
            Invoice invoice = productService.purchaseAndInvoice(clientId, productId, productPrice, amount);

            return ResponseEntity.ok(invoice);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
