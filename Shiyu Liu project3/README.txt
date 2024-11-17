Name: Shiyu Liu        NetID: sliu89
email: sliu89@u.rochester.edu

List of files:
DijkstraSPA.java
Graph.java
GraphPainter.java
IndexMinPQ.java
Intersection.java
KruskalMST.java
MinPQ.java
Road.java
StreetMapping.java
UnionFind.java
OUTPUT.txt

Synopsis:
I divided this project into four major parts: 
1. construct a graph with Graph.java, Intersection.java and Road.java
2. draw the graph with GraphPainter.java
3. find the shortest path from one point to another with DijkstraSPA.java
and IndexMinPQ.java
4. generate the minimum spanning tree with KruskalMST.java, MinPQ.java,
and UnionFind.java.
PS: the main method and the file reader is in StreetMapping.java

Part1: Constructing the graph
I started with Intersection. Each Intersection as a name, a latitude and a longitude.
Since the longitudes are negative, I converted them into positive values for further
calculations.
 
Then I went on with Road. Each road has two intersections, a name and
a weight. For the weight, I used Haversine Formula. It can calculate the distance of
two points given their coordinates on Earth. It also has a method other() that will return
the index of one intersection when the index of the other intersection is given.

Next I went on to Graph. In the graph, I made an adjacencyList for all the intersections,
three hashmaps and their individual get methods:
map and find(): map associates intersections with their names, and find() returns the 
Intersection given its name.
web and obtain(): web associates Roads with their weights, and obtain() returns the 
Road given its weight.
target and take(): target associates names of intersections with their indexes, and 
take() returns the index of an intersection given its name.
I also had an ArrayList of Roads for further usage.
addIntersection() adds an Intersection to map and target; addEdge() adds a Road
to the adjacencyList, roads and web.

Part2: Drawing the graph
In GraphPainter, firstly I have a height and a width for the JComponent. ArrayList lines
for storing all the lines in a map, redRoads for marking roads in DijkstraSPA and blueRoads
for marking the minimum spanning tree.
The compare() method can help me determine maxLat, minLat, maxLong and minLong.
These four variables are very crucial because I can use them to calculate the unit height
and unit width. Moreover, with unitW and unitH, I can calculate the coordinate of each
intersection relative to my map.
In the paintComponent() method, I used Graphics2D so I can draw Line2D.Double objects.
I used black lines to draw the map at first. Then I used red lines to highlight the shortest
path and blues to highlight the minimum spanning tree.

Part3: Dijkstra's shortest path algorithm
In DijkstraSPA I created a Road array roadTo[] to store the road to each intersection in the 
shortest path, a double array distanceTo[] to store the shortest distance to each Intersection
from the start. IndexMinPQ is an adaption of MinPQ so it can sort the distance to different 
Intersections in a descending order while also maintaining the index of these intersections.
At the start, I instantiated the arrays and IndexMinPQ pq. Then I set the distance to all the
intersections to positive infinity except for the starting point(distance = 0.0). Then I inserted
the start intersection into pq and call relax() method. relax() is the key method in this algorithm,
For every intersection, it loops through all the Roads in its adjacencyList and updates the minimum
distance to this intersection. If a shorter route is found, relax() updates distanceTo[v] and roadTo[v]
and insert(or change the distanceTo[]) a new Intersection into pq. When all the adjacencyLists
are examined, the program ends and the shortest path to each intersection is calculated.

Finally, the method pathTo adds Roads from the finishing point to the start point and return
an ArrayList of Roads in reverse order. I corrected the order issue in the main method.

Part4: Kruskal's minimum spanning tree
In KruskalMST, I created an ArrayList of Roads called mst to store all the Roads in the MST, a
MinPQ of weights of Roads to sort the weights in descending order, and a UnionFind object
uf to link all the intersections. 
At first, I obtained all the roads from the graph and inserted their weights into pq. Then I get 
the smallest weight each time, find its corresponding road and use uf to check whether the
two intersections of this road are connected. If they are not, union() them and add the road into
mst. When pq is empty, the program ends. 

Runtime analysis:
To see the runtime of my program, we have to start from StreetMapping.java since it combines
all the operations in the main method. Suppose the number of roads is R and the number of 
intersections is I. Their sum I + R = T. From line15 to line66, I looped through the entire text file
twice. When T becomes large, the constant time operations becomes insignificant so it is approximately
O(T)(linear time) in this part. Then we have to discuss Dijkstra's algorithm and Kruskal's algorithm
separately. Dijkstra's algorithm takes O(Rlog(I)) whereas Kruskal's algorithm takes O(Rlog(R)). We should
compare O(T), O(Rlog(I)) and O(Rlog(R)). First, both O(Rlog(I)) and O(Rlog(R)) are bigger than O(T) under
any circumstances because even for the smallest txt file ur.txt, where I = 124 and R = 181, the other two are
very likely to take longer time than O(T). Therefore, if the instruction only specifies "-show", the runtime is linear,
otherwise it is larger.

Next let's compare O(Rlog(I)) and O(Rlog(R)). In all three files, R > I, which means O(Rlog(R)) > O(Rlog(I)). 
This result is also validated during actual testing: for nys.txt, DijkstraSPA takes about only 4s while 
KruskalMST takes about 30s. 
