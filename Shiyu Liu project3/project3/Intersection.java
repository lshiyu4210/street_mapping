package project3;

import java.util.ArrayList;

//author: Shiyu Liu
public class Intersection {
	
	String name;
	double latitude, longitude;
	int index;
	
	public Intersection(String name, String latitude, String longitude) {
		this.name = name;
		this.latitude = Double.parseDouble(latitude);
		this.longitude = Math.abs(Double.parseDouble(longitude));     //convert negative longitude to positive value

	}

	
	public String toString() {
		return name + " " + latitude + " " + longitude + " " + index;
	}
	
	
}
