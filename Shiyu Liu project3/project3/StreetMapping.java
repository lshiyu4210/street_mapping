package project3;

import java.util.*;


import javax.swing.JFrame;

import java.io.*; 

//author: Shiyu Liu
public class StreetMapping {
	
    //file reader
	public static void main(String[] args) throws FileNotFoundException {
		String filepath = args[0];
		String instruction = args[1];
		File file = new File(filepath);
		Scanner scan1 = new Scanner(file);
		Scanner scan2 = new Scanner(file);
		int num = 0;
		int indexI = 0;		
		
		//draw the graph
		JFrame j = new JFrame();
		int w = 640;
		int h = 480;
		j.setSize(w, h);
		j.setTitle("Map");
		
		//scan1 is used to count the number of vertexes
		while(scan1.hasNext()) {
			String counter = scan1.nextLine();
			char c = counter.charAt(0);
			if(c == 'i')   num++;
		}
		
		Graph URgraph = new Graph(num);
		
		GraphPainter URpainter = new GraphPainter(h, w);
        j.add(URpainter);
		
        //build the graph by inserting all the vertexes and edges
		while(scan2.hasNextLine()) {
			String address = scan2.nextLine();
			String[] components = address.split("\t");
			if(components[0].equals("i")) {
				Intersection is = new Intersection(components[1], components[2], components[3]);
				is.index = indexI++;
				URgraph.addIntersection(is);
				URpainter.compare(is);
			}
			else {
				String name  = components[1];
				String isAname = components[2];
				String isBname = components[3];
				Road tempRoad = new Road(name, URgraph.find(isAname), URgraph.find(isBname));
				URgraph.addEdge(tempRoad);
				URpainter.addRoad(tempRoad);
			}
		}
		//take instructions from the command line
		if(instruction.equals("-show") || instruction.equals("-directions") || instruction.equals("-meridianmap")) {
			j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			j.setVisible(true);
			
			if(instruction.equals("-meridianmap")) {
				//minimum spanning tree driver
				KruskalMST m = new KruskalMST(URgraph);
				Iterator<Road> im = m.edges().iterator();
				while(im.hasNext()) {
					Road next = im.next();
					URpainter.addBlueRoad(next);
					System.out.println(next);
				}
			}
			
			if(instruction.equals("-directions")) {
				String start = args[2];
				String end = args[3];
				int s = URgraph.take(start);
				int e = URgraph.take(end);
				//Dijkstra shortest path driver
				DijkstraSPA d = new DijkstraSPA(URgraph, s);
	
				if(d.discovered(e)) {
					System.out.print("The shortest distance from " + start + " to " + end + " is : ");
					System.out.printf("%.2e", d.distanceTo(e));
					System.out.println(" miles.");
					System.out.println("Please follow the path below: ");
					ArrayList<Road> ik = (ArrayList<Road>)d.pathTo(e);
					for(int i = ik.size() - 1; i >= 0; i--){
						Road tempRoad = ik.remove(i);
					System.out.println(tempRoad.toString());
					URpainter.addRedRoad(tempRoad);
					}
				}
				else {
					System.out.println("Road to " + end + "is not found!");
				}	
			}
		}
		else   System.out.println("Invalid instruction!");
		
	}
	
	
}



