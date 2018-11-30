package re.gardensha.gardenshare;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Integer id;

    @ManyToOne
	@JoinColumn(name = "fk_reviewee")
    public User reviewee;

    @ManyToOne
	@JoinColumn(name = "fk_reviewer")
    public User reviewer;

    private Integer rating;
    private String message;

    public Review(){

    }

    public Review(String message, int rating, User revieweeUser, User reviewerUser){
        this.message = message;
        this.rating = rating;
        this.reviewee = revieweeUser;
        this.reviewer = reviewerUser;
    }

    public int getRatingScore(){
        return rating;
    }

    public String getMessage(){
        return message;
    }


}