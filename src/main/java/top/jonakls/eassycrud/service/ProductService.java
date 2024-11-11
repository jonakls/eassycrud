package top.jonakls.eassycrud.service;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import top.jonakls.eassycrud.entity.ProductEntity;
import top.jonakls.eassycrud.repository.ProductRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ResponseEntity<?> addProduct(final @NotNull ProductEntity product) {
        final Map<String, Object> response = new HashMap<>();

        final Optional<ProductEntity> productOptional = this.productRepository.findByName(product.getName());

        if (productOptional.isPresent()) {
            response.put("error", true);
            response.put("message", "Product already exists");
            return ResponseEntity.badRequest().body(response);
        }

        response.put("error", false);
        response.put("message", "Product added successfully");
        response.put("data", this.productRepository.save(product));
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> getProduct(final int id) {
        final Map<String, Object> response = new HashMap<>();

        final Optional<ProductEntity> productOptional = this.productRepository.findById(id);

        if (productOptional.isEmpty()) {
            response.put("error", true);
            response.put("message", "Product not found");
            return ResponseEntity.badRequest().body(response);
        }

        response.put("error", false);
        response.put("message", "Product found successfully");
        response.put("data", productOptional.get());
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> getAllProducts() {
        final Map<String, Object> response = new HashMap<>();

        response.put("error", false);
        response.put("message", "Products found successfully");
        response.put("data", this.productRepository.findAll());
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> deleteProduct(final int id) {
        final Map<String, Object> response = new HashMap<>();

        final Optional<ProductEntity> productOptional = this.productRepository.findById(id);

        if (productOptional.isEmpty()) {
            response.put("error", true);
            response.put("message", "Product not found");
            return ResponseEntity.badRequest().body(response);
        }

        this.productRepository.deleteById(id);

        response.put("error", false);
        response.put("message", "Product deleted successfully");
        return ResponseEntity.ok(response);
    }
}
