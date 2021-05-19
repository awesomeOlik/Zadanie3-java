package sk.stuba.fei.uim.oop.assignment3.products;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductRequest {
    private String name;
    private String description;
    private int amount;
    private  String unit;
    private Double price;
}
