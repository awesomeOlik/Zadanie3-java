package sk.stuba.fei.uim.oop.assignment3.cart;

import lombok.Getter;
import sk.stuba.fei.uim.oop.assignment3.payment.Payment;

import java.util.List;

@Getter
public class CartResponse {

    private final Long id;

    List<Payment> shoppingList;

    boolean payed;

    public CartResponse(Cart c){
        this.id = c.getId();
        this.shoppingList = c.getShoppingList();
        this.payed= c.isPayed();
    }

}
