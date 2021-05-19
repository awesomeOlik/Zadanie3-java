package sk.stuba.fei.uim.oop.assignment3.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.ProductInCart;

@RestController()
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private ICartService service;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CartResponse creatCart(){
        return new CartResponse(this.service.createCart());
    }

    @GetMapping("/{id}")
    public CartResponse getCartId(@PathVariable("id") Long id){
        return new CartResponse(this.service.getCartId(id));
    }

    @DeleteMapping("/{id}")
    public void deletCart(@PathVariable("id") Long id){
        this.service.deletCart(id);
    }

    @PostMapping("/{id}/add")
    public CartResponse addToCart(@PathVariable("id") Long cartId,@RequestBody ProductInCart productRequest){
        return new CartResponse(this.service.addToCart(cartId,productRequest));
    }

    @GetMapping("/{id}/pay")
    public String payForCart(@PathVariable("id") Long id){
        return this.service.payForCart(id);
    }
}
