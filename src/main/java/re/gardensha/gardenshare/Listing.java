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
    private String	 	fruitType = "";
    private Float 	    weight = 0f;
    private String      weightUnit = "lbs";
	private Integer 	count = 0;
	private Boolean 	ended = false;
	private Time 		startTime;
    private Time 		endTime;
    
    public Listing(){
        
    }

    public Listing(String type,
                   float weight,
                   String weightUnit,
                   int count,
                   Time startTime,
                   Time endTime){
        this.fruitType = type;
        this.weight = weight;
        this.weightUnit = weightUnit;
        this.count = count;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String toString() {
        return "<Listing "+id+" of type "+fruitType+">";
    }

    public String getType(){
        return fruitType;
    }

    public String getWeightString(){
        return weight + " " + weightUnit;
    }

    public float getWeight(){
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
