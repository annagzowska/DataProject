package persistence;

import javax.persistence.*;
import java.util.List;


import lombok.NoArgsConstructor;
import lombok.Data;

@Entity
@Data
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    private String surname;

    private String phoneNo;

    private String email;

    @ManyToMany(mappedBy = "persons")
    private List<Meeting> meetings;
}
