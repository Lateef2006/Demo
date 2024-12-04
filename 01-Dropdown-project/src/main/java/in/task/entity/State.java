package in.task.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class State {
    @Id
    private String stateCode;
    private String stateName;

   @ManyToOne
 //  @OneToMany
    @JoinColumn(name = "country_code", nullable = false)
    private Country country;

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public State(String stateCode, String stateName, Country country) {
		super();
		this.stateCode = stateCode;
		this.stateName = stateName;
		this.country = country;
	}

	public State() {
		super();
		
	}   
}

