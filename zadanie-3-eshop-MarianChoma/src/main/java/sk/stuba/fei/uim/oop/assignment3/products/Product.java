package sk.stuba.fei.uim.oop.assignment3.products;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
@Setter
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;

    private String name;
    private String description;
    private int amount;
    private String unit;
    private Double price;


    public void increaseAmount(int number){
        this.amount+=number;
    }
    public void reduceAmount(int number){
        this.amount-=number;
    }

}
