package sk.stuba.fei.uim.oop.assignment3.cart;

import sk.stuba.fei.uim.oop.assignment3.payment.Payment;

import java.util.List;

public interface ICartService {

    Cart addCart();
    Cart getCartById(Long id);
    List<Cart> deleteCart(Long id);
    Cart addToCart(Long id, Payment payment);
    String payCart(Long id);
}
