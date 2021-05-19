package sk.stuba.fei.uim.oop.assignment3.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.payment.Payment;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ICartService service;

    @PostMapping()
    public ResponseEntity<CartResponse> addCart(){
        return new ResponseEntity<>(new CartResponse(this.service.addCart()), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public CartResponse getCartById(@PathVariable("id") Long id){
        return new CartResponse(this.service.getCartById(id));
    }

    @DeleteMapping("/{id}")
    public List<CartResponse> deleteCart(@PathVariable("id") Long id){
        return this.service.deleteCart(id).stream().map(CartResponse::new).collect(Collectors.toList());
    }

    @PostMapping("/{id}/add")
    public CartResponse addToCart(@PathVariable("id") Long id, @RequestBody Payment payment){
        return new CartResponse(this.service.addToCart(id, payment));
    }

    @GetMapping("/{id}/pay")
    public String payCart(@PathVariable("id") Long id){
        return this.service.payCart(id);
    }
}
