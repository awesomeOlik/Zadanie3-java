package sk.stuba.fei.uim.oop.assignment3.product;

import lombok.Getter;

@Getter
public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private int amount;
    private String unit;
    private double price;

    public ProductResponse(Product product){
        //if (product == null){return ;}
        this.id= product.getId();
        this.name=product.getName();
        this.description=product.getDescription();
        this.amount=product.getAmount();
        this.unit= product.getUnit();
        this.price= product.getPrice();
    }
}
