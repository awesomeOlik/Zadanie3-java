package sk.stuba.fei.uim.oop.assignment3.cart;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Setter
@Getter
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany
    private List<ProductInCart> shoppingList=new ArrayList<>();
    boolean payed;


}
