package br.com.university;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {
	
	@Column(name = "Address", nullable = false)
	private String address;
	
	@Column(name = "City", nullable = false)
	private String city;
	
	public Address() {};
	
	public Address(String address, String city) {
		this.address = address;
		this.city = city;
	}	

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if(!obj.getClass().equals(this.getClass())) {
        	return false;
        }

        Address other = (Address) obj;

        if (other.address.equals(address) && 
        	    other.getCity().equals(city)) {
            return true;
        }

        return false;
    }

    public int hashCode() {
    	final int prime = 31;
		int result = 1;
		result = prime * result + (address + city).hashCode();
		return result;
    }

}