package sk.stuba.fei.uim.oop.assignment3.products;

import lombok.Getter;
import lombok.Setter;

@Getter
public class ProductResponse {
    private final Long id;
    private String name;
    private String description;
    @Setter
    private int amount;
    private  String unit;
    private Double price;

    public ProductResponse(Product product) {
        this.id= product.getProductId();
        this.amount= product.getAmount();
        this.description=product.getDescription();
        this.name=product.getName();
        this.unit=product.getUnit();
        this.price=product.getPrice();
    }
}
