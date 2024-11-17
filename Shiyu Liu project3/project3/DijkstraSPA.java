package project3;

import java.util.*;

//author: Shiyu Liu
public class DijkstraSPA {
	
	private Road[] roadTo;         //record the road to each vertex
	private double[] distanceTo;   //record the shortest distance to to each vertex
	private IndexMinPQ<Double> pq; //store roads in adjacency list
	
	public DijkstraSPA(Graph G, int start) {
		
		roadTo = new Road[G.V()];
		distanceTo = new double[G.V()];
		pq = new IndexMinPQ<Double>(G.V());
		
		for(int i = 0; i < G.V(); i++) 
			distanceTo[i] = Double.POSITIVE_INFINITY;
		distanceTo[start] = 0.0;
		
		pq.insert(start, 0.0);
		while(!pq.isEmpty())
			relax(G, pq.delMin());
	}
	
	public void relax(Graph G, int v) {
		//update the shortest distance from the start to each vertex
		
		for(Road r : G.adjList(v)) {
			int d = r.other(v);
			if(distanceTo[d] > distanceTo[v] + r.weight) {
				distanceTo[d] = distanceTo[v] + r.weight;
				roadTo[d] = r;
				if(pq.contains(d))  pq.decreaseKey(d, distanceTo[d]);
				else                pq.insert(d, distanceTo[d]);
			}
		}
	}
	
	public double distanceTo(int k) {return distanceTo[k];}
	
	public boolean discovered(int k) {return distanceTo[k] < Double.POSITIVE_INFINITY;}
	
	public Iterable<Road> pathTo(int k){
		if(!discovered(k)) return null;
		ArrayList<Road> path = new ArrayList<Road>();
		for(Road r = roadTo[k]; r != null; r = roadTo[k]) {
			path.add(r);
			k = r.other(k);
		}
		return path;
		
	}

}
//Work cited:
//Sedgewick, Robert. Wayne, Kevin. "Algorithms Fourth Edition". 4.4 Shortest Paths P655.
//2011 Pearson Education, Inc.