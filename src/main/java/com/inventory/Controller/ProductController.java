package com.inventory.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.Entity.Product;
import com.inventory.service.ProductService;


@RestController
@RequestMapping("/api/products")
public class ProductController {
	 private final ProductService productService;

	    public ProductController(ProductService productService) {
	        this.productService = productService;
	    }

	    // Create
	    @PostMapping
	    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
	        return new ResponseEntity<>(productService.createProduct(product), HttpStatus.CREATED);
	    }

	    // Read all
	    @GetMapping
	    public ResponseEntity<List<Product>> getAllProducts() {
	        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
	    }

	    // Read by ID
	    @GetMapping("/{id}")
	    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
	        Product product = productService.getProductById(id);
	        if (product == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        return new ResponseEntity<>(product, HttpStatus.OK);
	    }

	    // Update
	    @PutMapping("/{id}")
	    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
	        Product updated = productService.updateProduct(id, product);
	        if (updated == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        return new ResponseEntity<>(updated, HttpStatus.OK);
	    }

	    // Delete
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
	        productService.deleteProduct(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }

	    // Increase stock
	    @PostMapping("/{id}/increase")
	    public ResponseEntity<Product> increaseStock(@PathVariable Long id, @RequestParam int quantity) {
	        Product updated = productService.increaseStock(id, quantity);
	        if (updated == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        return new ResponseEntity<>(updated, HttpStatus.OK);
	    }

	    // Decrease stock
	    @PostMapping("/{id}/decrease")
	    public ResponseEntity<?> decreaseStock(@PathVariable Long id, @RequestParam int quantity) {
	        try {
	            Product updated = productService.decreaseStock(id, quantity);
	            return new ResponseEntity<>(updated, HttpStatus.OK);
	        } catch (RuntimeException e) {
	            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	        }
	    }

	    // Bonus: Low stock products
	    @GetMapping("/low-stock")
	    public ResponseEntity<List<Product>> getLowStockProducts() {
	        return new ResponseEntity<>(productService.getLowStockProducts(), HttpStatus.OK);
	    }
}
