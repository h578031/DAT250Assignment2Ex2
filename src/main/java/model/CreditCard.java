package model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private String id;
    private int number;
    private int balance;
    private int limit;

    @ManyToOne
    private Bank bank;

    @OneToOne
    private Pincode pincode;


}
