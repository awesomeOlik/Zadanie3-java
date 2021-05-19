package sk.stuba.fei.uim.oop.assignment3.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService service;
    @GetMapping
    public List<ProductResponse> getAllProducts(){
        return this.service.getAll().stream().map(product -> new ProductResponse(product)).collect(Collectors.toList());
    }
    @ResponseStatus (HttpStatus.CREATED)
    @PostMapping
    public ProductResponse addProduct(@RequestBody ProductRequest request){
        return new ProductResponse(this.service.create(request));
    }
    @GetMapping("/{id}")
    public ProductResponse getProduct(@PathVariable("id") Long id){
        return new ProductResponse(this.service.getProductById(id));
    }
    @PutMapping("/{id}")
    public ProductResponse updateProduct(@PathVariable("id") Long id,@RequestBody ProductRequest request){
        return new ProductResponse(this.service.updateProduct(id,request));
    }
    @DeleteMapping("/{id}")
    public void  deleteProduct(@PathVariable("id") Long id){
        this.service.deleteProduct(id);
    }
    @GetMapping("/{id}/amount")
    public ProductAmount getAmountProduct(@PathVariable("id") Long id){
        return new ProductAmount(this.service.getProductById(id));
    }
    @PostMapping("/{id}/amount")
    public ProductAmount addAmountProduct(@PathVariable("id") Long id, @RequestBody ProductRequest request){
        return new ProductAmount(this.service.addAmountProduct(id,request));
    }
}
