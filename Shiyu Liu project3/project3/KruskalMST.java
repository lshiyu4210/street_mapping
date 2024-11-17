package project3;

import java.util.ArrayList;

import java.util.Iterator;

//author: Shiyu Liu
public class KruskalMST {
	
	private ArrayList<Road> mst;
	private double weight = 0.0;
	
	public KruskalMST(Graph g) {
		
		mst = new ArrayList<Road>();
		MinPQ<Double> pq = new MinPQ<Double>(g.R());   //a MinPQ to store the weights in a descending order
		UnionFind uf = new UnionFind(g.V());           //an union find object to combine all the vertexes
		Iterator<Road> i = g.roads().iterator();
		while(i.hasNext()) {
			Road tempRoad = i.next();
			pq.insert(tempRoad.weight);
		}
		
		while(!pq.isEmpty() && mst.size() < g.V() - 1) {  
			
			double d = pq.delMin();
			Road r = g.obtain(d);                       //get the road from the HashMap
			int v = r.is1.index;
			int w = r.is2.index;
			if(uf.connected(v, w))  continue;
			uf.union(v, w);                              //if two vertexes are not linked, unite them and add the road
			mst.add(r);
			this.weight += r.weight;
		}
		
	}
	
	public Iterable<Road> edges(){
		return mst;
	}
	
	public double weight() {
		return weight;
	}

}
//Work cited:
//Sedgewick, Robert. Wayne, Kevin. "Algorithms Fourth Edition". 4.3 Minimum Spanning Trees P627.
//2011 Pearson Education, Inc.

