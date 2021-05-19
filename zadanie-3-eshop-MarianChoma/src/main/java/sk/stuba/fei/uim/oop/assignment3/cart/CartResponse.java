package sk.stuba.fei.uim.oop.assignment3.cart;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CartResponse {

    private Long id;
    private List<ProductInCart> shoppingList;
    boolean payed;

    public CartResponse(Cart cart) {
        this.id= cart.getId();
        this.shoppingList=cart.getShoppingList();
        this.payed= cart.isPayed();
    }

}
