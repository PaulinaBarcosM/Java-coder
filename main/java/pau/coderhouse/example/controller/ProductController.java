package pau.coderhouse.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pau.coderhouse.example.entity.Product;
import pau.coderhouse.example.service.ProductService;

import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    //creamos la propiedad del servicio
    @Autowired  //inyectamos la dependencia
    private ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    //lo hacemos para obtener todos los productos
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE}) //va a ser la ruta raíz de producto
    public ResponseEntity<Iterable<Product>> getAll() {
        Iterable<Product> products = service.getProducts();
        return ResponseEntity.ok(products); //este es para devolver todos los productos
    }

    //vamos a pasarle un parametro de peticion
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Optional<Product>> getById(@PathVariable Long id) {
        //buscamos el producto del servicio
        Optional<Product> product = service.getById(id);

        //preguntamos si existe producto
        if (product.isPresent()) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build(); //de esta manera me devuelve un tipo de respuesta NOT FUND(q no se encontro el producto)
        }
    }

    //post para crear los datos
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Product> create(@RequestBody Product product) {
        try {
            Product nuevoProduct = service.save(product);
            return ResponseEntity.ok(product);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
