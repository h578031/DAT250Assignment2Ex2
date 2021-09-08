package model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private String id;
    private String street;
    private int number;

    @ManyToMany(mappedBy = "address")
    private List<Person> personList;
}
