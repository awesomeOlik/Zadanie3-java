package sk.stuba.fei.uim.oop.assignment3.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import sk.stuba.fei.uim.oop.assignment3.products.IProductService;
import sk.stuba.fei.uim.oop.assignment3.products.Product;

import java.util.Optional;

@Component
public class CartService implements ICartService{

    private final CartRepository repository;
    private final IProductService productService;
    private final ProductInCartRepository productInCartRepository;

    @Autowired
    private CartService(CartRepository repository, IProductService productService, ProductInCartRepository productRepository) {
        this.repository=repository;
        this.productService = productService;
        this.productInCartRepository = productRepository;
    }

    @Override
    public CartResponse createCart() {
        Cart cart=new Cart();
        cart.setPayed(false);
        this.repository.save(cart);
        return new CartResponse(cart);
    }
    private Cart isPresentCart(Optional<Cart> cart){
        return cart.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public Cart findById(Long id) {
        return isPresentCart(this.repository.findById(id));
    }

    @Override
    public void deleteById(Long id) {
        this.repository.delete(findById(id));
    }

    @Override
    public Cart addProductToCart(Long id, Product item) {

            var cart=findById(id);
            var product = this.productService.findById(item.getProductId());

            if ((cart.isPayed()) || (product.getAmount() - item.getAmount() < 0)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
            else {
                if (cart.getShoppingList().stream().anyMatch(s -> s.getProductId().equals(item.getProductId()))) {
                    for (ProductInCart p : cart.getShoppingList()) {
                        if (p.getProductId().equals(item.getProductId())) {
                            p.increaseAmount(item);
                            this.productInCartRepository.save(p);
                            break;
                        }
                    }
                } else {
                    var newProduct = new ProductInCart(product, item.getAmount());
                    cart.getShoppingList().add(newProduct);
                    this.productInCartRepository.save(newProduct);
                }
                product.reduceAmount(item.getAmount());
                this.repository.save(cart);
            }
        return cart;
    }

    @Override
    public String payCart(Long id) {
        var cart=findById(id);
        double bill;
        if(cart.isPayed()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        else {
            bill = cart.getShoppingList().stream().mapToDouble(p -> p.getAmount() * p.productPrice()).sum();
            cart.setPayed(true);
            this.repository.save(cart);
        }
        return Double.toString(bill);
    }
}
