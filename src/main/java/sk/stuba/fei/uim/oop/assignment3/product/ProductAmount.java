package sk.stuba.fei.uim.oop.assignment3.product;

import lombok.Getter;

@Getter
public class ProductAmount {
    private int amount;

    public ProductAmount(Product product) {
        this.amount = product.getAmount();
    }
}
