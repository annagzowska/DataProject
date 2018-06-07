package persistence;

import javax.persistence.*;
import java.util.List;
import lombok.NoArgsConstructor;
import lombok.Data;

@Entity
@NoArgsConstructor
@Data
public class Place {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "meeting_place")
    private List<Meeting> meetings;


}
