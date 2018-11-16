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
    public Integer 	id;
    private String	 	fruitType;
	private Integer 	weight;
	private Integer 	count;
	private boolean 	ended;
	private Time 		startTime;
    private Time 		endTime;
    
    public Listing(){
        
    }

    public Listing(String type){
        this.fruitType = type;
    }

    public String toString() {
        return "<Listing "+id+" of type "+fruitType+">";
    }

    public int getWeight(){
        return weight;
    }

    public int getCount(){
        return count;
    }

    public boolean hasEnded(){
        return ended;
    }

    public Time getStartTime(){
        return startTime;
    }

    public Time getEndTime(){
        return endTime;
    }

}
