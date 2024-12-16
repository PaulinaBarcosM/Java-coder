package pau.coderhouse.example.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pau.coderhouse.example.entities.InvoiceDetails;
import pau.coderhouse.example.services.InvoiceDetailsService;
import pau.coderhouse.example.services.InvoiceService;
import pau.coderhouse.example.services.ProductService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceDetailsController {

    @Autowired
    private InvoiceDetailsService invoiceDetailsService;

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private ProductService productService;

    @Operation(summary = "Get invoice details by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request was successful"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    @GetMapping(value = "/{invoiceId}/details", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<InvoiceDetails>> getDetailsByInvoiceId(@PathVariable UUID invoiceId) {
        List<InvoiceDetails> details = invoiceDetailsService.findByInvoiceId(invoiceId);
        return ResponseEntity.ok(details);
    }
}
