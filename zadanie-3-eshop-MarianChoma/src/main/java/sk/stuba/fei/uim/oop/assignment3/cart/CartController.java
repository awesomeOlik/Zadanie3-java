package sk.stuba.fei.uim.oop.assignment3.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.products.Product;


@RestController
@RequestMapping("/cart")
public class CartController {
    private final ICartService service;

    @Autowired
    public CartController(ICartService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CartResponse newCart(){
        return this.service.createCart();
    }

    @GetMapping("/{id}")
    public CartResponse findById(@PathVariable("id") Long id){
        return new CartResponse(this.service.findById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id){
        this.service.deleteById(id);
    }

    @PutMapping ("/{id}/add")
    public CartResponse addProduct(@PathVariable("id") Long id, @RequestBody Product product){
        return new CartResponse(this.service.addProductToCart(id,product));
    }

    @GetMapping("/{id}/pay")
    public String payCart(@PathVariable("id") Long id){
        return this.service.payCart(id);
    }
}
