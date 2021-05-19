package sk.stuba.fei.uim.oop.assignment3.cart;

import sk.stuba.fei.uim.oop.assignment3.products.Product;


public interface ICartService {
    CartResponse createCart();
    Cart findById(Long id);
    void deleteById(Long id);
    Cart addProductToCart(Long id, Product product);
    String payCart(Long id);
}
