package project3;

public class UnionFind {
	
	private int[] id;
	private int count;
	
	public UnionFind(int N) {
		count = N;
		id = new int[N];
		for(int i = 0; i < N; i++) {
			id[i] = i;
		}
	}
	
	public int count() {
		return count;
	}
	
	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}
	
	public int find(int p) {
		return id[p];
	}
	
	public void union(int p, int q) {
		int pID = find(p);
		int qID = find(q);
		
		if(pID == qID)  return;
		
		for(int i = 0; i < id.length; i++)
			if(id[i] == pID)  id[i] = qID;
		count--;
	}

}
