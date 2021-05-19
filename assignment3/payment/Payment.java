
package sk.stuba.fei.uim.oop.assignment3.payment;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;

    private int amount;

}
