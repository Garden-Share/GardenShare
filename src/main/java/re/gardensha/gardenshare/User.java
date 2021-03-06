package re.gardensha.gardenshare;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String name;

	private String email;

	private String oauthId;

	private String description;
	

	public User(String oauthId){
		this.oauthId = oauthId;
		this.name = "Gardenshare User";
	}

	public User(){
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOAuthId(){
		return oauthId;
	}

	public String getDescription(){
		return description;
	}

	public void setDescription(String desc){
		description = desc;
	}

}