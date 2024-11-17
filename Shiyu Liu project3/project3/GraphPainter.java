package project3;

import javax.swing.*;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;


//author: Shiyu Liu
public class GraphPainter extends JComponent{
	
	private int height;
	private int width;
	private double  maxLat;
	private double  minLat;
	private double  maxLong;
	private double  minLong;
	private double unitH;                //vertical unit length
	private double unitW;                //horizontal unit length
	private ArrayList<Road> lines;       //store all the lines
	private ArrayList<Road> redRoads;    //store the lines representing the shortest path
	private ArrayList<Road> blueRoads;   //store the lines representing the minimum spanning tree
	
	public GraphPainter(int height, int width) {
		this.height = height;
		this.width = width;
		lines = new ArrayList<Road>();
		redRoads = new ArrayList<Road>();
		blueRoads = new ArrayList<Road>();
		
	}
	
	public void addRoad(Road r) {
		lines.add(r);
		
	}
	
	public void addRedRoad(Road r) {
		redRoads.add(r);
	}
	
	public void addBlueRoad(Road r) {
		blueRoads.add(r);
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		
		Graphics2D g2d = (Graphics2D)g;  //convert graphics to graphics2d
		super.paintComponent(g2d);
		
		double x1, y1, x2, y2;
		
		for(Road r : lines) {
			g2d.setColor(Color.BLACK);                            //draw graph for "-show"
			this.unitW = this.getWidth() / (maxLong - minLong) ;  //calculate horizontal/vertical unit length
			this.unitH = this.getHeight() / (maxLat - minLat);
			
			x1 = r.is1.longitude;
			y1 = r.is1.latitude;
			x2 = r.is2.longitude;
			y2 = r.is2.latitude;
			
			g2d.draw(new Line2D.Double((x1 - minLong) * unitW, getHeight() - ((y1 - minLat) * unitH),
					(x2 - minLong) * unitW, getHeight() - ((y2 - minLat) * unitH)));
		}
		
		for(Road r : redRoads) {
			g2d.setColor(Color.RED);                             //draw graph for "-directions"
			this.unitW = this.getWidth() / (maxLong - minLong) ;
			this.unitH = this.getHeight() / (maxLat - minLat);
			
			x1 = r.is1.longitude;
			y1 = r.is1.latitude;
			x2 = r.is2.longitude;
			y2 = r.is2.latitude;
			
			g2d.draw(new Line2D.Double((x1 - minLong) * unitW, getHeight() - ((y1 - minLat) * unitH),
					(x2 - minLong) * unitW, getHeight() - ((y2 - minLat) * unitH)));
		}
		
		for(Road r : blueRoads) {
			g2d.setColor(Color.BLUE);                          //draw graph for "-meridianmap"
			this.unitW = this.getWidth() / (maxLong - minLong) ;
			this.unitH = this.getHeight() / (maxLat - minLat);
			
			x1 = r.is1.longitude;
			y1 = r.is1.latitude;
			x2 = r.is2.longitude;
			y2 = r.is2.latitude;
			
			g2d.draw(new Line2D.Double((x1 - minLong) * unitW, getHeight() - ((y1 - minLat) * unitH),
					(x2 - minLong) * unitW, getHeight() - ((y2 - minLat) * unitH)));
		}
		
		
	}
	
	public void compare(Intersection i) {
		//find out the max/min latitude/longitude
		
		if(i.index == 0) {
			maxLat = i.latitude;
			minLat = i.latitude;
			maxLong = i.longitude;
			minLong = i.longitude;	
		}
		else {
			if(i.latitude > maxLat)  maxLat = i.latitude;
			if(i.latitude < minLat)  minLat = i.latitude;
			if(i.longitude > maxLong)  maxLong = i.longitude;
			if(i.longitude < minLong)  minLong = i.longitude;
			this.unitH = (maxLat - minLat) / height;
			this.unitW = (maxLong - minLong) / width;
			
		}
	}
	
	
	
	
	
	
	

}
