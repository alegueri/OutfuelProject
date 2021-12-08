package UserInfo;

import static org.junit.Assert.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.junit.Test;

/**
 * TestClasses is a JUnit class that contains methods to test all the classes within OUTFUEL. 
 * 
 * @author Alessandra Guerinoni
 *@version 1.0 
 *
 */

public class TestClasses {
	
	@Before
	public void setUp() throws Exception {

	}
	/**
	 * testCompiler tests the ability of the compiler method to create an edge-weighted digraph. 
	 * @throws Exception
	 */

	@Test
	public void testCompiler() throws Exception {
	  	GasStationADT ar1 = new GasStationADT("station1", 1.4, "Punchbowl", "1618 Canterbury Road",1.5, "cool",true,-33.9319425, 151.0538063); 
	  	GasStationADT ar2 = new GasStationADT("station2", 1.4, "Granville", "158 Clyde Street",2.5, "cool",true, -33.8374526, 151.0146249); 
	  	GasStationADT ar4 = new GasStationADT("station2", 1.4, "Burwood", "287 Liverpool Road",2.5, "cool",true,-33.8875397, 151.0538063); 
	  	GasStationADT ar3 = new GasStationADT("station3", 1.4, "subs", "1280 Main St",2.5, "cool",true,50.4793797, 5.4092144); 
	  	VehicleADT car = new VehicleADT(2017,"bmw","kk","premium",4,0.7,18);
	  	GasStationADT[] array1 = {ar1,ar4,ar2,ar3} ; 
	  	Compiler graph = new Compiler("1618 Canterbury Road, Punchbowl", "158 Clyde Street, Granville", array1);
		WeightedDigraph G = graph.getWeightedDigraph();
		assert(G.V()== 3);
		assert(G.E() == 2);
		assert(car.milesInTank() == 12.6); 
		assert(graph.getdistance() == 12.752);
		GasStationADT[] array = graph.getarray(); 
		DijkstraSP path = new DijkstraSP(G, graph.efrom(array), graph.eto(array), graph.getdistance(),car);
		System.out.println(path);
		assert(path.hasPathTo(graph.eto(array)));

	}
	/**
	 * testDistanceGoogle determines the functionality of the method, in order to determine certain distances.  
	 * @throws IOException
	 */
	@Test
	public void testDistanceGoogle() throws IOException {
		double distance = DistanceGoogle.distance("1280 Main St", "269 Potts Terrace");
		double distance2 = DistanceGoogle.distance("1280 Main St", "1280 Main St");
		assert(distance == 12.644);
		assert(distance2 == 0.0);
		  boolean thrown = false; 
		  try {
			  DistanceGoogle.distance("1280 Main St", "noaddress");;
		 } catch (NumberFormatException e) {
		    thrown = true;
		  }
		  assertTrue(thrown);
		}
	/**
	 * testGasStationADT(); whether or not a GasStationADT() type was created..  
	 */		
	
	@Test
	public void testGasStationADT() {
		GasStationADT gas = new GasStationADT("station1",111,"hello", "hellom", 10, "rpd", true, 110, 120); 
		assert(gas.getAddress().equals("hellom"));
		assert(gas.getSuburb().equals("hello"));
		assert(gas.getDistance()==10);
		assert(gas.getGasTypes().equals("rpd"));
		assert(gas.getLatitude()==110);
		assert(gas.getLongitude()==120);
		assert(gas.getPrice()==111);
		assert(gas.getRewards()==true);
		assert(gas.getStationName().equals("station1"));
				
	}
	/**
	 * testLoad() Tests the functionality of load, and whether or not it read from the datasets to create the GasStationADT() and VehiclesADT() arrays.  
	 * @throws NumberFormatException, I0Exception
	 */	
	@Test
	public void testLoad() throws NumberFormatException, IOException {
		GasStationADT[] gasstations = Load.LoadGasStations();
		VehicleADT[] vehicles = Load.LoadVehicles(); 
		for(int i = 0; i < gasstations.length; i++) { 
			if(gasstations[i] instanceof GasStationADT) assert(true);
			else {assert(false);} } 
		for(int i = 0; i < vehicles.length; i++) { 
			if(vehicles[i] instanceof VehicleADT) assert(true);
			else {assert(false);} }
		assert(gasstations.length == 1756); 
		assert(vehicles.length == 18030); 
	}
	/**
	 * testLoad() Tests the functionas ability to determine latitude and longitude. A WARNING WILL APPEAR IF THE log4j Folder HAS NOT BEEN ADDED TO THE ROOT OF THE CLASSPATH
	 * IGNORE ERROR IF SEEN. OR ADD TO CLASS PATH:   rightclick TestClasses -> run as -> run configurations -> classpath -> outfuel -> advanced -> add folders -> log4j -> apply and close  
	 * @throws NumberFormatException, I0Exception
	 */	
	@Test
	public void testOpenStreetMapUtils() {
		String address = "1618 Canterbury Road , Punchbowl"; //from gasstations.txt 
		assert(OpenStreetMapUtils.getLatitude(address) == -33.9319425);
		assert(OpenStreetMapUtils.getLongitude(address) == 151.0538063 );
	}
	@Test
	/**
	 * testQuickSort() tests whether method can sort arrays of gas stations based on distance OR price. 
	 * @throws NumberFormatException, I0Exception
	 */	
	public void testQuickSort() {
		GasStationADT gas = new GasStationADT("station1",111,"hello", "hellom", 10, "rpd", true, 110, 110); 
		GasStationADT gas1 = new GasStationADT("station2",34,"hello", "hellom", 12, "rpd", true, 110, 110); 
		GasStationADT gas2 = new GasStationADT("station3",111,"hello", "hellom", 15, "rpd", true, 110, 110); 
		GasStationADT gas3 = new GasStationADT("station4",134,"hello", "hellom", 18, "rpd", true, 110, 110); 
		GasStationADT[] gasss = {gas,gas1,gas2,gas3};
		QuickSort.sort(gasss, "distance");
		QuickSort.isSorted(gasss, "distance");
		QuickSort.sort(gasss, "price");
		QuickSort.isSorted(gasss, "price");
	}
	/**
	 * testsearchVehicle() tests whether a vehicle is found if within the array. searchVehicle() returns null if none has been found.  
	 * @throws NumberFormatException, I0Exception
	 */	
	@Test
	public void testsearchVehicle() throws NumberFormatException, IOException {
        VehicleADT[] vehiclesArray = Load.LoadVehicles();
        VehicleADT usersVehicle = searchVehicle.search(vehiclesArray, 1993, "Corolla", "Toyota");
        assert(usersVehicle != null);
        VehicleADT usersVehicle2 = searchVehicle.search(vehiclesArray, 1993, "randommodel", "Randombrand");
        assert(usersVehicle2 == null);
	}
	/**
	 * testSummary() tests basic summary functions. 
	 * @throws I0Exception
	 */	
	@Test
	public void testSummary() throws IOException {
		String start = "294 Albury St"; 
		String destination = "1 Mandamah Rd";
		VehicleADT novehicle = new VehicleADT(1993, "Toyota", "Corolla", "r", 21, 2, 21);
		GasStationADT ar1 = new GasStationADT("station1", 1.4, "subs", "1618 Canterbury Road",1.5, "cool",true,-44.0680953, 171.088764); 
		Summary sum = new Summary(ar1,start, novehicle, 19.8);
		assert(sum.gasConsumed(destination)==0.2818671621530334);
		System.out.println(sum.getGasCosts() == 0.28);

	}
	/**
	 * testUsingDistance() tests  whether it can determine the distance based on longitude and latitude and simultaneously set the distance within the GasStationADT() objects.  
	 * @throws I0Exception
	 */	
	@Test
	public void testUsingDistance() throws IOException {
	  	GasStationADT ar1 = new GasStationADT("station1", 1.4, "subs", "1618 Canterbury Road",1.5, "cool",true,-44.0680953, 171.088764); 
	  	GasStationADT ar2 = new GasStationADT("station2", 1.4, "subs", "158 Clyde Street",2.5, "cool",true,-44.2486282857143, 171.498140755102); 
	  	GasStationADT ar3 = new GasStationADT("station3", 1.4, "subs", "47 Princes Highway",2.5, "cool",true,50.4793797, 5.4092144); 
	  	GasStationADT[] array1 = {ar1,ar2,ar3} ;
		UsingDistance.updateDistances(array1,-44.0680953,171.088764); 
		assert(array1[0].getDistance() == 0.0);
		assert(array1[1].getDistance() == 23.8194385729621);
		assert(array1[2].getDistance() == 100000); //outofrange of latitude and longitude so automatically set to 100000
	}
	/**
	 * testGasStationADT(); whether or not a VehicleADT() type was created..  
	 */	
	@Test
	public void testVehicleADT() {
			VehicleADT vehicle = new VehicleADT(1993, "Toyota", "Corolla", "r", 29, 13, 21);
			assert(vehicle.getYear() == 1993);
			assert(vehicle.getBrand().equals("Toyota"));
			assert(vehicle.getModel().equals("Corolla"));
			assert(vehicle.getGasType().equals("r"));
			assert(vehicle.getFuelTankCapac() == 29);
			assert(vehicle.getCurrentGas() == 13);
			assert(vehicle.getAverageMPG() == 21);
			assert(vehicle.milesInTank() == 273);
			assert(vehicle.gallonsOfGasNeeded(63) == 3);
			assert(vehicle instanceof VehicleADT);
			
	}
	@After
	public void tearDown() throws Exception {
	}

}

