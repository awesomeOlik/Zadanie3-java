package sk.stuba.fei.uim.oop.assignment3.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sk.stuba.fei.uim.oop.assignment3.ProductInCart;
import sk.stuba.fei.uim.oop.assignment3.product.IProductService;
import sk.stuba.fei.uim.oop.assignment3.product.Product;
import sk.stuba.fei.uim.oop.assignment3.product.ProductRepository;

import java.util.Optional;

@Service
public class CartService implements ICartService{

    private CartRepository cartRepository;

    @Autowired
    public ProductRepository productRepository;

    @Autowired
    public CartService(CartRepository repository){ this.cartRepository=repository;}

    @Autowired
    private IProductService productService;

    @Override
    public Cart createCart(){
        Cart cart=new Cart();
        cart.setPayed(false);
        this.cartRepository.save(cart);
        return cart;
    }

    @Override
    public Cart getCartId(Long id) {
        return this.cartRepository.findById(id).orElseThrow();
    }

    @Override
    public void deletCart(Long id) {
        getCartId(id);
        this.cartRepository.deleteById(id);
    }

    @Override
    public Cart addToCart(Long id, ProductInCart request){
        Optional<Cart> cartOptional= this.cartRepository.findById(id);
        Cart cart = cartOptional.get();
        Product product=this.productService.getProductById(request.getProductId());
        int i=product.getAmount()-request.getAmount();
        if(cart.isPayed()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (i>-1){
            product.setAmount(i);
            this.productRepository.save(product);
            for (ProductInCart productInCart: cart.getShoppingList()){
                if (productInCart.getProductId().equals(request.getProductId())){
                    productInCart.setAmount(productInCart.getAmount()+ request.getAmount());
                    return cart;
                }
            }
            cart.getShoppingList().add(request);
        }
        else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return cart;
    }

    @Override
    public String payForCart(Long id) {
        double price=0;
        Cart cart =getCartId(id);
        if (cart.isPayed()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        for (ProductInCart productInCart : cart.getShoppingList()){
            Product product =this.productService.getProductById(productInCart.getProductId());
            price+=product.getPrice()* productInCart.getAmount();
        }
        cart.setPayed(true);
        return Double.toString(price);
    }
}