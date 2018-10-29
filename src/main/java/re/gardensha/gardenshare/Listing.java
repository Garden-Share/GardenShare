package re.gardensha.gardenshare;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Listing {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    String fruitType;

    public String toString() {
        return "<Listing "+id+" of type "+fruitType+">";
    }

}