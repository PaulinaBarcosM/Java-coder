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
import pau.coderhouse.example.entities.Product;
import pau.coderhouse.example.services.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    public ProductController(ProductService service) {
        this.productService = service;
    }

    @Operation(summary = "Creation of a new product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product created"),
            @ApiResponse(responseCode = "400", description = "Invalid product")
    })
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Product>> findAll() {
        List<Product> products = productService.getProducts();
        return ResponseEntity.ok(products);
    }

    @Operation(summary = "Searching for product by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product found successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid product")
    })
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Optional<Product>> getById(@PathVariable Long id) {
        Optional<Product> product = productService.getById(id);
        if (product.isPresent()) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "We create a new product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid product",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}
            )
    })
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Product> create(@RequestBody Product product) {
        try {
            Product nuevoProduct = productService.save(product);
            return ResponseEntity.ok(nuevoProduct);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
