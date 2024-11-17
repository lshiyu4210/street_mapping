package project3;

//author: Shiyu Liu
public class Road implements Comparable<Road>{
	
	String name;
	Intersection is1, is2;
	double weight;
	private final double R = 3958.8;   //radius of the Earth in mile
	
	public Road(String name, Intersection is1, Intersection is2) {
		this.name = name;
		this.is1 = is1;
		this.is2 = is2;
		this.weight = mileConverter(is1.latitude, is1.longitude, is2.latitude, is2.longitude);
	}
	
	public int other(int i) {
		//given one vertex, return the other vertex 
		if(is1.index == i)     return is2.index;
		else                   return is1.index;
	}
	
	public double mileConverter(double latitude1, double longitude1, double latitude2, double longitude2) {
		//calculate the weight of each road in mile with Haversine formula
		double theta1 = latitude1 * Math.PI / 180;
		double theta2 = latitude2 * Math.PI / 180;
		double x = (latitude2 - latitude1) * Math.PI / 180;
		double y = (longitude2 - longitude1) * Math.PI / 180;
		
		double a = Math.sin(x /2) * Math.sin(x /2) + Math.cos(theta1) * Math.cos(theta2)
				* Math.sin(y /2) * Math.sin(y /2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double d = R * c;
		return d;
	}
	
	@Override
	public String toString() {
		return "" + name;
	}

	@Override
	public int compareTo(Road that) {
		if      (this.weight > that.weight)   return 1;
		else if (this.weight == that.weight)  return 0;
		else                                  return -1;
	}

}
