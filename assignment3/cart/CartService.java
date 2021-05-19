package sk.stuba.fei.uim.oop.assignment3.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sk.stuba.fei.uim.oop.assignment3.payment.Payment;
import sk.stuba.fei.uim.oop.assignment3.product.IProductService;
import sk.stuba.fei.uim.oop.assignment3.product.Product;
import sk.stuba.fei.uim.oop.assignment3.product.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CartService implements ICartService{

    private final CartRepository repository;

    @Autowired
    private CartService(CartRepository repository){
        this.repository = repository;
    }

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private IProductService productService;

    @Override
    public Cart addCart() {
        Cart cart = new Cart();

        cart.setPayed(false);

        return this.repository.save(cart);
    }

    @Override
    public Cart getCartById(Long id) {
        return this.repository.findById(id).orElseThrow();
    }

    @Override
    public List<Cart> deleteCart(Long id) {
        Optional<Cart> cartOpt = this.repository.findById(id);
        Cart cart = cartOpt.orElseThrow();

        this.repository.deleteById(cart.getId());

        return this.repository.findAll();
    }

    @Override
    public Cart addToCart(Long id, Payment payment) {
        Cart cart = this.repository.findById(id).orElseThrow();

        if(cart.isPayed())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        Product product = this.productService.getById(payment.getProductId());

        if(product.getAmount() - payment.getAmount() > -1){
            product.setAmount(product.getAmount() - payment.getAmount());
            this.productRepository.save(product);

            for(Payment p : cart.getShoppingList()){
                if(p.getProductId().equals(payment.getProductId())) {
                    p.setAmount(p.getAmount() + payment.getAmount());
                    return cart;
                }
            }

            cart.getShoppingList().add(payment);
        }else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        return cart;
    }

    @Override
    public String payCart(Long id) {
        Cart cart = this.repository.findById(id).orElseThrow();

        if(cart.isPayed())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        for(Payment p : cart.getShoppingList()){
            Product product = this.productService.getById(p.getProductId());
            cart.setPrice(cart.getPrice() + (p.getAmount() * product.getPrice()));
        }
        cart.setPayed(true);

        return Double.toString(cart.getPrice());
    }
}
