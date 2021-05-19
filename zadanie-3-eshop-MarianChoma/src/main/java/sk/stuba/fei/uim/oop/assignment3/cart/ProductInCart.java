package sk.stuba.fei.uim.oop.assignment3.cart;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.products.Product;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class ProductInCart {
    @Getter
    @Id
    private Long productId;

    @Setter
    @OneToOne
    private Product product;

    @Getter
    @Setter
    private int amount;

    public ProductInCart(Product product, int amount) {
        this.product = product;
        this.productId = product.getProductId();
        this.amount = amount;
    }

    public double productPrice(){
        return this.product.getPrice();
    }

    public void increaseAmount(Product item){
        this.amount+= item.getAmount();
    }
}
