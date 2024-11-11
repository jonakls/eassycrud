package top.jonakls.eassycrud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import top.jonakls.eassycrud.entity.ProductEntity;
import top.jonakls.eassycrud.service.ProductService;

@RestController
@RequestMapping("/api/v1/products")
public class UserController {

    private final ProductService productService;

    @Autowired
    public UserController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllProducts() {
        return this.productService.getAllProducts();
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody final ProductEntity productEntity) {
        return this.productService.addProduct(productEntity);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestBody final ProductEntity productEntity) {
        return this.productService.addProduct(productEntity);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable final int id) {
        return this.productService.deleteProduct(id);
    }
}
