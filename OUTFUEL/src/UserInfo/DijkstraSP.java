package UserInfo;

import java.io.IOException;
import java.util.Stack;
/**
 * This class implements the Dijkstras algorithm .
 * Works cited: https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/DijkstraSP.java.html
 * @author Alessandra Guerinoni
 *@version 1.0 
 */
public class DijkstraSP {
    private double[] distTo;          // distTo[v] = distance  of shortest s->v path
    private DirectedEdge[] edgeTo;    // edgeTo[v] = last edge on shortest s->v path
    private IndexMinPQ<Double> pq;    // priority queue of vertices
    private String[] res; 
    private VehicleADT car; 
    private int s; 
    private int dest; 
    private double distance;
   
	/**
	 * This routine finds a shortest path from one location to another, nodes being these locations and gas stations within range. 
	 * If the amount of miles in car is less than the distance to the destination, an alternate path through gas stations is shown, in order
	 * to provide the user a location to fill up gas. 
	 * 
	 * @param WeightedDigraph, the graph to find the shortest path
	 * @param int s, the source node 
	 * @param int des, the destination
	 * @param double distance, the distance between the source and the destination. 
	 * @param VehicleADT car; the car from we can find the mileage. 
	 * @return a shortest path. 
	 * 
	 */
    public DijkstraSP(WeightedDigraph G, int s, int dest, double distance, VehicleADT car) {

    		this.s = s;
    		this.dest = dest; 
    		this.distance = distance; 
    		this.car = car;
    		res = new String[G.V()];
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        validateVertex(s);
        // initializes the distance as positive infinity 
        for (int v = 0; v < G.V(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
            res[v] = null; 
        }  		
        distTo[s] = 0.0;
        pq = new IndexMinPQ<Double>(G.V());
        pq.insert(s, distTo[s]);
        while (!pq.isEmpty()) {
            relax(G, pq.delMin());
        }
        
    }
	/**
	 * This routine relaxes paths. If the distance from one node to another is greater the the mileage in the car, it is not accepted into the path. 
	 * @param WeightedDigraph, the graph to find the shortest path
	 * @param int v, the nodes to get a path.  
	 * 
	 */
    private void relax(WeightedDigraph G, int v) {
    		for(DirectedEdge e: G.adj(v))
    		{
    			int w = e.to();

    			if(e.weight() > car.milesInTank()) continue; 
    			if(distTo[w] > distTo[v] + e.weight()) {
	    				distTo[w] = distTo[v] + e.weight(); 
	    				edgeTo[w] = e;
	    				if(pq.contains(w)) pq.changeKey(w, distTo[w]);
	    				else 	pq.insert(w, distTo[w]);
	    				
    			}
        }
    }

    public double distTo(int v) {
        validateVertex(v);
        return distTo[v];
    }

    public boolean hasPathTo(int v) {
        validateVertex(v);
        return distTo[v] < Double.POSITIVE_INFINITY;
    }
 // The shortest path 
    public Iterable<DirectedEdge> pathTo(int v) {
        validateVertex(v);
        Stack<DirectedEdge> path = new Stack<DirectedEdge>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.push(e);
        }
        return path;
    }
	/**
	 * 
	 * For a given vertex, determines whether it is valid, if its within the number of vertices given from the constructor.  
	 * @param int v, if the vertex v is a valid vertex. 
	 */
    private void validateVertex(int v) {
        int V = distTo.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }
	/**
	 * This routine shows the user the shortest path. If the mileage from the car is greater than the distance between the source and destination,
	 * the user will have no problem getting to their destination, and will not have to fuel  up. 
	 * If the distance from the source to a gas station is greater than the mileage left in their car, they will not be able to fuel up. 
	 * 
	 */
    public String toString() {
    		String str ="";
    		if(car.milesInTank() > distance) {
    			str += "You can get to your destination without going to a gas station!";
    			return str; 
    		} 
           // str += .name()+ " to" + " " + CitiesArray[21].name() +  " " + distTo(21) + ": ";
            for (DirectedEdge e : pathTo(dest)) {
                str += "Path: "+ e + "| ";
            }
            str += "\n";
            if(str.equals("\n")) System.out.println("oh well, you're stuck!");
        return str; 
    }	

}
