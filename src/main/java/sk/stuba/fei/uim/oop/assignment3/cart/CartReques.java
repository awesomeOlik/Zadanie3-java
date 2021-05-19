package sk.stuba.fei.uim.oop.assignment3.cart;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.ProductInCart;

import java.util.List;

@Setter
@Getter
public class CartReques {
    private List<ProductInCart> shoppingList;
    private boolean payed;
}
