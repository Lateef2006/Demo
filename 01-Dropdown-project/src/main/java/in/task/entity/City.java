package in.task.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;


@Entity
@Data
public class City {
    @Id
    private String cityCode;
    private String cityName;

    @ManyToOne
  //  @OneToMany
    @JoinColumn(name = "state_code", nullable = false)
    private State state;

    @ManyToOne
    @JoinColumn(name = "country_code", nullable = false)
    private Country country;

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public City(String cityCode, String cityName, State state, Country country) {
		super();
		this.cityCode = cityCode;
		this.cityName = cityName;
		this.state = state;
		this.country = country;
	}

	public City() {
		super();
		// TODO Auto-generated constructor stub
	}

    // Getters and Setters
    
}

