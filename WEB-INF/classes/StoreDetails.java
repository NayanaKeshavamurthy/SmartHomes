import java.util.*;
import java.util.Map;


/*
 * Tv class contains class variables
 * name,price,image,retailer,condition,discount and Accessories Hashmap.
 * 
 * Tv class constructor with Arguments
 * name,price,image,retailer,condition,discount and Accessories .
 * 
 * Accessory class contains getters and setters for
 * name,price,image,retailer,condition,discount and Accessories .
 * 
 */

public class StoreDetails {
	public StoreDetails(int id, String name, String city, String state, Long zipcode) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
	}
	public StoreDetails() {}
	private int id;
	private String name;
	private String city;
	private String state;
	private Long zipcode;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Long getZipcode() {
		return zipcode;
	}
	public void setZipcode(Long zipcode) {
		this.zipcode = zipcode;
	}

	

}
