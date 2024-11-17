package project3;

//author: Shiyu Liu
public class IndexMinPQ<Key extends Comparable<Key>>  {
	//a modification of MinPQ that correlates each key to a index(used in DijkstraSPA)
	
	private Key[] pq;                    //an array that stores all the keys
	private int N = 0;                   
	private int[] index;                 //an array that stores the index of all keys in this IndexMinPQ
	private int[] inverse;               //given the index of the key outside the IndexMinPQ, return its index in this IndexMinPQ
	
	
	public IndexMinPQ(int maxN) {
		pq = (Key[])new Comparable[maxN + 1];
		index = new int[maxN + 1];
		inverse = new int[maxN + 1];
		for(int i = 0; i <= maxN; i++)
			inverse[i] = -1;
	}
	
	public boolean isEmpty() {
		return N == 0;
	}
	
	public boolean contains(int k) {
		return inverse[k] != -1;
	}
	
	public void decreaseKey(int k, Key v) {
		pq[k] = v;
		swim(inverse[k]);
	}
	
	public int getSize() {
		return N;
	}
	
	public void insert(int k, Key v) {
		N++;
		inverse[k] = N;
		index[N] = k;
		pq[k] = v;
		swim(N);
	}
	
	public int delMin() {
		int minIndex = index[1];
		exch(1, N--);
		sink(1);
		pq[index[N + 1]] = null;
		inverse[index[N + 1]] = -1;
		index[N + 1] = -1;	
		return minIndex;
		
	}
	
	public boolean more(int i, int j) {
		return pq[index[i]].compareTo(pq[index[j]]) > 0;
	}
	
	public void exch(int i, int j) {
		int swap = index[i];
		index[i] = index[j];
		index[j] = swap;
		inverse[index[i]] = i;
		inverse[index[j]] = j;
	}
	
	public void swim(int k) {
		while(k > 1 && more(k/2, k)) {
			exch(k/2, k);
			k = k/2;
		}
	}
	
	public void sink(int k) {
		while(2 * k <= N) {
			int j = 2 * k;
			if(j < N && more(j, j + 1))  j++;
			if(!more(k, j))  break;
			exch(k, j);
			k = j;
		}
	}


}
//Work cited:
//Sedgewick, Robert. Wayne, Kevin. "Algorithms Fourth Edition". 2.4 Priority Queues P333.
//2011 Pearson Education, Inc.
