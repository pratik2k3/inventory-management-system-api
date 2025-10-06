package com.inventory.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.Entity.Product;
import com.inventory.Exception.ProductServiceException;
import com.inventory.Wrapper.ApiResponse;
import com.inventory.service.ProductService;


@RestController
@RequestMapping("/api/products")
public class ProductController {
	  private final ProductService productService;

	    public ProductController(ProductService productService) {
	        this.productService = productService;
	    }

	 
	    @PostMapping
	    public ResponseEntity<ApiResponse<Product>> createProduct(@RequestBody Product product) {
	        Product created = productService.createProduct(product);
	        return new ResponseEntity<>(new ApiResponse<>("Product created successfully", created), HttpStatus.CREATED);
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<ApiResponse<Product>> updateProduct(@PathVariable Long id, @RequestBody Product product) {
	        Product updated = productService.updateProduct(id, product);
	        return new ResponseEntity<>(new ApiResponse<>("Product updated successfully", updated), HttpStatus.OK);
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<ApiResponse<Void>> deleteProduct(@PathVariable Long id) {
	        productService.deleteProduct(id);
	        return new ResponseEntity<>(new ApiResponse<>("Product deleted successfully", null), HttpStatus.OK);
	    }
	    @PostMapping("/{id}/increase")
	    public ResponseEntity<ApiResponse<Product>> increaseStock(@PathVariable Long id,
	                                                              @RequestParam int quantity) {
	        Product updated = productService.increaseStock(id, quantity);
	        return new ResponseEntity<>(new ApiResponse<>("Stock increased successfully", updated), HttpStatus.OK);
	    }

	    @PostMapping("/{id}/decrease")
	    public ResponseEntity<ApiResponse<Product>> decreaseStock(@PathVariable Long id,
	                                                              @RequestParam int quantity) {
	        Product updated = productService.decreaseStock(id, quantity);
	        return new ResponseEntity<>(new ApiResponse<>("Stock decreased successfully", updated), HttpStatus.OK);
	    }

	    @GetMapping
	    public ResponseEntity<ApiResponse<List<Product>>> getAllProducts() {
	        List<Product> products = productService.getAllProducts();
	        return new ResponseEntity<>(new ApiResponse<>("All products fetched successfully", products), HttpStatus.OK);
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<ApiResponse<Product>> getProductById(@PathVariable Long id) {
	        Product product = productService.getProductById(id);
	        return new ResponseEntity<>(new ApiResponse<>("Product fetched successfully", product), HttpStatus.OK);
	    }

	    @GetMapping("/low-stock")
	    public ResponseEntity<ApiResponse<List<Product>>> getLowStockProducts() {
	        List<Product> products = productService.getLowStockProducts();
	        return new ResponseEntity<>(new ApiResponse<>("Low stock products fetched successfully", products), HttpStatus.OK);
	    }
	   
	    @ExceptionHandler(ProductServiceException.class)
	    public ResponseEntity<String> handleProductServiceException(ProductServiceException ex) {
	        return new ResponseEntity<>(ex.getMessage(), ex.getHttpStatus());
	    }
}
