package UserInfo;

import preprocessDataSets.preprocessDataSets;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String [ ] args) throws Exception {
   // 	System.out.println(DistanceGoogle.distance("294 Albury St","1 Mandamah Rd"));
        GasStationADT[] stationArray = Load.LoadGasStations();
        Scanner name = new Scanner(System.in);
        Scanner locations = new Scanner(System.in);
        int year;
        String brand, model, inf, location;
     
        System.out.print("Put your's car information: 1-brand: ");
        brand = name.next();
        System.out.print(" 2-model: ");
        model = name.next();
        System.out.print(" 3-year: ");
        year = name.nextInt();
        //String GasType, double capacity, double currentGas, double aveMPG
        
        VehicleADT[] vehiclesArray = Load.LoadVehicles();
        VehicleADT usersVehicle = searchVehicle.search(vehiclesArray, year, model, brand);
        if(usersVehicle == null)
        {
        	 String ifbrand, ifmodel, iftype;
        	 int ifyear;
        	 double ifcapacity, ifcurrentgas, ifmpg;
        
        	System.out.println("Couldn't find vehicle in dataset, please manually input the information");   
        	System.out.println("What is the gas type of your vehicle?");
        	iftype = name.next();  
        	System.out.println("What is the capacity of your vehicle?");
        	ifcapacity = name.nextDouble(); 
        	System.out.println("How much gas is currently in your vehicle?");
        	ifcurrentgas = name.nextDouble();  
        	System.out.println("What is your vehicle's average MPG");
        	ifmpg= name.nextDouble();  
        	VehicleADT novehicle = new VehicleADT(year, brand, model, iftype, ifcapacity, ifcurrentgas, ifmpg);
        	usersVehicle = novehicle; 
        	
        }
        
        System.out.println("Enter your location: ");
        location = locations.nextLine();
        System.out.println("What location do you want to go to?:" );
        String destination = locations.nextLine();
        
       
        usersVehicle.set_gas(2.7);
        
   
        
        Double userlat = OpenStreetMapUtils.getLatitude(location); 
        Double userlong = OpenStreetMapUtils.getLongitude(location); 
        	
        	UsingDistance.updateDistances(stationArray, userlat, userlong);
        
        GasStationADT[] newsorted = new GasStationADT[10]; 
    System.out.print("Do you want cheapest price, or closest station? Input price or distance.");
    inf = name.next();
   int k = 0; 
        QuickSort.sort(stationArray, "distance");
      for (int i = 0; i < stationArray.length; i++) {
          	newsorted[k] = stationArray[i]; 
  			String location22 = newsorted[i].getFullAddress(); 
  			double distance = DistanceGoogle.distance(location, location22); 
  			newsorted[k].setDistance(distance); 
  			if(k == 9) break;
  			k++; 
      }
        while(true) {
        if (inf.equals("price")) {
        	QuickSort.sort(newsorted, "price");
        
        	System.out.println("The order based on the cheapest price nearest to you is: ");
            for (int i = 0; i < 10; i++) {
                System.out.println(i+1 + ". \n" + newsorted[i]);
            }
            break;
        }
        
    else if(inf.equals("distance")) {
           
        		System.out.println("The order based on the closest location is: ");
            for (int i = 0; i < 10; i++) {
				System.out.println(i + ". \n " + newsorted[i]);
            }
            break;
        }
        
        else {
            System.out.print("it's not an option, choose again");
            inf = name.next();
        }
        }
        
        System.out.println();
        System.out.println("How much gas did you put in the car?");
        double amount = name.nextDouble(); 
	 	System.out.println("Do you want route option or summary of travels?: Input \"Summary\" or \"Route\"");
	 	String choice = name.next();
while(true) {
	 	if(choice.equals("Summary")) {
	 		
	 		Summary sum = new Summary(newsorted[0], location, usersVehicle, amount);
	 		System.out.println(sum);
	 		System.out.println(sum.getDistance(destination));
	 		System.out.println("The amount of gas you have used in your trip is: " + sum.gasConsumed(destination));
	 		break; 
	 		
	 	}
	 	if(choice.equals("Route")) {

		  	Compiler graph = new Compiler(location, destination, newsorted);
			WeightedDigraph G = graph.getWeightedDigraph(); 
			GasStationADT[] array = graph.getarray(); 
			System.out.println(usersVehicle.milesInTank());
			DijkstraSP path = new DijkstraSP(G, graph.efrom(array), graph.eto(array), graph.getdistance(),usersVehicle);
			System.out.println("The distance to your destination is:" + graph.getdistance());
			System.out.println(path);
			break;
	 	}
	 	else {
	 		System.out.println("Not an option, please choose again.");
	 		choice = name.next();
	 	}
}
}
}