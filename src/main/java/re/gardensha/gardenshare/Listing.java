package re.gardensha.gardenshare;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Time;

@Entity // This tells Hibernate to make a table out of this class
public class Listing {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer 	id;
	private String	 	product;
	private Integer 	weight;
	private Integer 	count;
	private boolean 	ended;
	private Time 		start_time;
	private Time 		end_time;

    public String toString() {
        return "<Listing "+id+" of type "+product+">";
    }

}
