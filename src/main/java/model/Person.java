package model;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private String id;
    private String name;

    @ManyToMany
    private List<Address> address;

    @OneToMany
    private List<CreditCard> creditCards;


}
