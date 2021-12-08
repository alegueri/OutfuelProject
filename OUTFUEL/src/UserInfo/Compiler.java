package UserInfo;
import java.util.ArrayList; 
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
/**
 * The Compiler class will create a WeightedDigraph with nodes as the origin, destination and gas stations within bounds of the origin and destination.
 * Bounds which are determined by the longitude and latitude.  
 * @author Alessandra Guerinoni 
 *
 */
public class Compiler {
	private GasStationADT[] gasarray;
	private String place1;
	private String place2; 
	private double fromto; 
	private ArrayList<GasStationADT> newarray = new ArrayList<GasStationADT>(); 
	private GasStationADT[] newnewarray; 
	
	/**
	 * This constructor sets the origin and the destination/ .
	 * 
	 * @param  place1; the origin 
	 * @param place2; the destination
	 */
	public Compiler(String place1, String place2, GasStationADT[] gasarray) {
		this.place1 = place1; 
		this.place2 = place2; 
		this.gasarray = gasarray; 
	}
	// gas array will be the array with gas station locations in sorted order ? 
	/**
	 * This method returns a edge filled weighted digraph. The source node is connected to every other node. Every node is connected to the one farther away from it. The
	 * nodes never connect backwards (in order to make sure the user doesn't travel away from their destination). An edge will not be created between the
	 * source and destination, since if the mileage is more than the distance, the path directly from the source to destination can be taken. 
	 * 
	 * We also consider the longitude and latitude of every gas station within the provided array, in order to make sure the stations are between the source
	 * and destination, so to not lead the user far away from their destination. 
	 * @return a Weighted Digraph to be used in Dijkstras algorithm. 
	 */
    public WeightedDigraph getWeightedDigraph() throws Exception {
    		int count = 0; 
    		int counter = 0; 
    		WeightedDigraph G = null; 
            double latitude = OpenStreetMapUtils.getLatitude(place1);
            double longitude = OpenStreetMapUtils.getLongitude(place1);
            double latitude2 = OpenStreetMapUtils.getLatitude(place2);
            double longitude2 = OpenStreetMapUtils.getLongitude(place2);
    		int a = 0;
    		while(true) {
    			if(longitude <= longitude2 && latitude <= latitude2)
    			{
    				
    			if(gasarray[a].getLatitude() <= latitude2 && gasarray[a].getLatitude() >= latitude && gasarray[a].getLongitude() <= longitude2  && gasarray[a].getLongitude() >= longitude )
    			{ 
    				
      			if(gasarray[a].getFullAddress().toLowerCase().contains(place1.toLowerCase())) {count++;}
    				if(gasarray[a].getFullAddress().toLowerCase().contains(place2.toLowerCase())) { counter++;}
    				newarray.add(gasarray[a]); }
    			}
    			if(longitude >= longitude2 && latitude >= latitude2)
    				
    			{
    			if(gasarray[a].getLatitude() >= latitude2 && gasarray[a].getLatitude() <= latitude && gasarray[a].getLongitude() >= longitude2  && gasarray[a].getLongitude() <= longitude )
    			{ 
    				
      			if(gasarray[a].getFullAddress().toLowerCase().contains(place1.toLowerCase())) {  count++;}
    				if(gasarray[a].getFullAddress().toLowerCase().contains(place2.toLowerCase())) { counter++;}
    				newarray.add(gasarray[a]); }
    			}
    			if(longitude <= longitude2 && latitude >= latitude2)
    			{
    				
    			if(gasarray[a].getLatitude() >= latitude2 && gasarray[a].getLatitude() <= latitude && gasarray[a].getLongitude() <= longitude2  && gasarray[a].getLongitude() >= longitude )
    			{ 
      			if(gasarray[a].getFullAddress().toLowerCase().contains(place1.toLowerCase())) {count++;}
    				if(gasarray[a].getFullAddress().toLowerCase().contains(place2.toLowerCase())) { counter++;}
    				newarray.add(gasarray[a]); }
    			}
    			if(longitude >= longitude2 && latitude <= latitude2)
    			{
    			if(gasarray[a].getLatitude() <= latitude2 && gasarray[a].getLatitude() >= latitude && gasarray[a].getLongitude() >= longitude2  && gasarray[a].getLongitude() <= longitude )
    			{ 
      			if(gasarray[a].getFullAddress().toLowerCase().contains(place1.toLowerCase())) { count++;}
    				if(gasarray[a].getFullAddress().toLowerCase().contains(place2.toLowerCase())) { counter++;}
    				newarray.add(gasarray[a]); }
    			}
    			
    			a++; 
    			if(a == gasarray.length) {break;} 
    			}
    		/*
    		if(newarray.size() == 0)
    		{
    			System.out.println("True");
    			for(int i = 0; i < gasarray.length; i++) {
    				if(gasarray[i].getAddress().toLowerCase().equals(place1.toLowerCase())) { count++;}
    				if(gasarray[i].getAddress().toLowerCase().equals(place2.toLowerCase())) { counter++;}
    			newarray.add(gasarray[i]);
    			}
    		}*/
    		if(count == 1 && counter!=1) {  G = new WeightedDigraph(newarray.size() + 1); } 
    		if(counter == 1 && count!=1){  G = new WeightedDigraph(newarray.size() + 1); } 
    		if(counter == 1 && count==1){  G = new WeightedDigraph(newarray.size()); } 
    		if(counter !=1 && count != 1){ G = new WeightedDigraph(newarray.size() + 2); } 
    		newnewarray = new GasStationADT[newarray.size()]; 
    		int k = 0;
    		for(GasStationADT w : newarray) {
    			newnewarray[k] = w; 
    			k++;
    		}

    		//creates an edge from source to destination 

    		for(int i = 0; i < newarray.size(); i++) {
    			for (int j = i+1;  j < newarray.size();  j++) {
    				
    				if(!newarray.get(i).getFullAddress().toLowerCase().contains(place1.toLowerCase()) || !newarray.get(j).getFullAddress().toLowerCase().contains(place2.toLowerCase())) {
    				double distance1 = DistanceGoogle.distance(newarray.get(i).getFullAddress(), newarray.get(j).getFullAddress());
    				DirectedEdge ed1 = new DirectedEdge(newarray.get(i).getAddress(), newarray.get(j).getAddress(), distance1, newnewarray); 
    				G.addEdge(ed1);
    			}
    			}
    			if(count!=1) { 
        		double distance = DistanceGoogle.distance(place1, newarray.get(i).getFullAddress());
        		DirectedEdge ed = new DirectedEdge(place1, newarray.get(i).getFullAddress(), distance, newnewarray); 
        		G.addEdge(ed); } 
        		if(counter!=1) {
    			double distdest = DistanceGoogle.distance(newarray.get(i).getFullAddress(), place2);
    			DirectedEdge edge = new DirectedEdge(newarray.get(i).getAddress(), place2, distdest, newnewarray); 
				G.addEdge(edge); } 
    			
    		}
    		System.out.println(G);

    return G; 
   
}
	/**
	 * Helper method that are implemented within other classes for ease. getsource() returns the origin location.  

	 * @return a String, of their source location.  
	 */
    public String getsource() {
    		return this.place1; 
    }
	/**
	 * Helper method that are implemented within other classes for ease. getdestination() returns the destination.  

	 * @return a String, of their destination.   
	 */
    public String getdestination() {
		return this.place2; 
}
    /**
	 * Helper method that are implemented within other classes for ease. getdistance() returns the distance between the source and destination.  

	 * @return a double, the distance.   
	 */
    public double getdistance() throws IOException {
		return DistanceGoogle.distance(place1, place2);
		
}
    /**
	 * Helper method that are implemented within other classes for ease. efrom() returns the integer associated with the source node.  

	 * @return an integer, value associated with the source node.   
	 */
    public int efrom(GasStationADT[] gasarray) {
		double newdistance = 0;
		DirectedEdge edges = new DirectedEdge(place1, place2, newdistance, gasarray);
		return edges.from();
    }
    /**
	 * Helper method that are implemented within other classes for ease. eto() returns the integer associated with the desintation node.  

	 * @return an integer, value associated with the destination node.   
	 */
    public int eto(GasStationADT[] gasarray) {
		double newdistance = 0;
		DirectedEdge edges = new DirectedEdge(place1, place2, newdistance, gasarray);
		return edges.to();
    }
    /**
	 * Helper method that are implemented within other classes for ease. getarray() returns the simplified array with the gas stations that will be considered as nodes.
	 * if within range.   
	 * @return a GasStationADT[] array.   
	 */
    public GasStationADT[] getarray() {
    		return newnewarray;
    }

} 
