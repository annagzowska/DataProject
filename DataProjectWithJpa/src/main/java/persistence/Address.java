package persistence;


import javax.persistence.Embeddable;
import lombok.NoArgsConstructor;
import lombok.Data;

@Embeddable
@Data
@NoArgsConstructor
public class Address {

    private String streetName;
    private Integer streetNo;
    private Integer aptNo;
    private String postalCode;
    private String city;
    private String country;
}