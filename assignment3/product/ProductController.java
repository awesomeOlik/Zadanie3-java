package sk.stuba.fei.uim.oop.assignment3.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService service;

    @GetMapping()
    public List<ProductResponse> getAllProducts(){
        return this.service.getAll().stream().map(ProductResponse::new).collect(Collectors.toList());
    }

    @PostMapping()
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest request){
        return new ResponseEntity<>(new ProductResponse(this.service.create(request)), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable("id") Long id) {
        return new ProductResponse(this.service.getById(id));
    }

    @PutMapping("/{id}")
    public ProductResponse updateProduct(@PathVariable("id") Long id, @RequestBody ProductRequest request){
        return new ProductResponse(this.service.updateProduct(id, request));
    }

    @DeleteMapping("/{id}")
    public List<ProductResponse> deleteProduct(@PathVariable("id") Long id){
        return this.service.deleteProduct(id).stream().map(ProductResponse::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}/amount")
    public Map<String, Integer> productAmount(@PathVariable("id") Long id){
        return this.service.productAmount(id);
    }

    @PostMapping("/{id}/amount")
    public Map<String, Integer> increaseAmount(@PathVariable("id") Long id, @RequestBody ProductRequest request){
        return this.service.increaseAmount(id, request);
    }
}
