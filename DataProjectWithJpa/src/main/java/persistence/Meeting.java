package persistence;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import lombok.NoArgsConstructor;
import lombok.Data;

@Entity
@Data
public class Meeting {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "meeting_date")
    private LocalDateTime localDateTime;

    private String extraInfo;

    @ManyToOne
    private Place meetingPlace;

    @ManyToMany
    private List<Person> persons;

    public Meeting() {
    }

    public Meeting(LocalDateTime localDateTime, String extraInfo) {
        this.localDateTime = localDateTime;
        this.extraInfo = extraInfo;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}
