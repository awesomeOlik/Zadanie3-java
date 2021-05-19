package sk.stuba.fei.uim.oop.assignment3.cart;

import sk.stuba.fei.uim.oop.assignment3.ProductInCart;

public interface ICartService {
    Cart createCart();
    Cart getCartId(Long id);
    void deletCart(Long id);
    Cart addToCart(Long id, ProductInCart request);
    String payForCart(Long id);
}
