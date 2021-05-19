package sk.stuba.fei.uim.oop.assignment3.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final IProductService service;

    @Autowired
    public ProductController(IProductService service) {
        this.service = service;
    }

    @GetMapping()
    public List<ProductResponse> getProducts(){
        return this.service.getAll().stream().map(ProductResponse::new).collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    private ProductResponse addProduct(@RequestBody ProductRequest request){
        return new ProductResponse(this.service.create(request));
    }

    @GetMapping("/{id}")
    private ProductResponse findById(@PathVariable("id") Long id){
        return new ProductResponse(this.service.findById(id));
    }
    @PutMapping("/{id}")
    private ProductResponse updateProduct(@PathVariable("id") Long id, @RequestBody ProductRequest update){
        return new ProductResponse(this.service.updateProduct(id,update));
    }

    @DeleteMapping("/{id}")
    private void deleteProductById(@PathVariable("id") Long id){
        this.service.deleteById(id);
    }

    @GetMapping("/{id}/amount")
    private Map<String,String> getProductAmount(@PathVariable("id") Long id){
        return this.service.productAmount(id);
    }

    @PostMapping("/{id}/amount")
    private Map<String,Integer> increaseAmount(@PathVariable("id") Long id, @RequestBody ProductRequest amount){
        return this.service.increaseAmount(id,amount);
    }
}
