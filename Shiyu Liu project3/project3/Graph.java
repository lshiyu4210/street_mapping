package project3;

import java.util.*;

//author: Shiyu Liu
public class Graph {
	
	private int V;
	private int R;
	private ArrayList<Road>[] adjacencyList;     
	private HashMap<String, Intersection> map;  //relate each intersection to its name
	private HashMap<Double, Road> web;          //relate each road to its weight
	private HashMap<String, Integer> target;    //relate the name of each intersection to its index
	private ArrayList<Road> roads;              //store all the roads

	public Graph(int V) {
		this.V = V;
		this.R = 0;
		adjacencyList = (ArrayList<Road>[])new ArrayList[V];
		for(int i = 0; i < V; i++) {
			adjacencyList[i] = new ArrayList<Road>();
		}
		map = new HashMap<String, Intersection>(V);
		web = new HashMap<Double, Road>();
		target = new HashMap<String, Integer>();
		roads = new ArrayList<Road>();
	}
	
	public void addIntersection(Intersection i) {
		map.put(i.name, i);
		target.put(i.name, i.index);
	}
	
	public void addEdge(Road r) {
		//add road to the adjacencyList, roads, and web
		
		adjacencyList[r.is1.index].add(r);
		adjacencyList[r.is2.index].add(r);
		roads.add(r);
		web.put(r.weight, r);
		R++;
	}
	
	public int V() {return V;}
	
	public int R() {return R;}
	
	public Iterable<Road> adjList(int v){
		return adjacencyList[v];
	}
	
	public Intersection find(String name) {
		//given the name, find the intersection
		
		return map.get(name);
	}
	
	public Road obtain(double d) {
		//given the weight, find the road
		
		return web.get(d);
	}
	
	public int take(String name) {
		//given the name of the intersection, find its index
		
		return target.get(name);
	}
	
	public Iterable<Road> roads(){
		return roads;
	}
	

}
