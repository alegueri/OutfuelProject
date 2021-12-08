package UserInfo;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides user with a summary of gas consumed, distance traveled 
 * and and money spent on gas
 * 
 * Initially, user will input the amount of gas they currently have in their car
 * 
 * After 10 closest gas stations are presented. We prompt user to enter the 
 * amount of gas they will be refilling their car with.
 * 
 * @author Andy
 *
 */
public class Summary {

	private String start;
	private GasStationADT station;
	private VehicleADT v;
	private double amount; 

	
	
	public Summary(GasStationADT station, String start, VehicleADT v, double amount) {
		this.station = station;
		this.start = start; 
		this.v = v; 
		this.amount = amount; 

	}

	/**
	 * Returns the distance between start and the final destination
	 * 
	 * @param destination
	 * @return
	 * @throws IOException
	 */
	public String getDistance(String destination) throws IOException {
		
		String str = "Your distance is: " + DistanceGoogle.distance(start, destination);
		return  str; 
		
	}
	
	public double gasConsumed(String destination) throws IOException {
		//constants represent conversion rates for gallons to litres and mile to km
		//System.out.println(DistanceGoogle.distance(start, destination));
		//System.out.println(v.getAverageMPG());
		return (DistanceGoogle.distance(start, destination) * 0.621371) / (v.getAverageMPG()*3.78541);
	}
	
	/**
	 * Returns the costs of refueling car with amount Litres  of gas 
	 * 
	 * @param amount spent on g609 Argyle Street, Moss Valeas fuel, measured in Litres 
	 * @return
	 */
	public double getGasCosts() {
		
		//convert price from cents to dollar
		double price = ((station.getPrice())/100.0);
	
		//format to 2 decimal places
		DecimalFormat df = new DecimalFormat("###.##");
		
		double formatPrice = Double.parseDouble(df.format(amount*price));
		
		//fuel rate for gas station * amount refilled 
		return formatPrice; 
	}
	
	public String toString() {
		
		String str ="Your total cost is: $" + getGasCosts() + " AUD" + "\n";

		return str; 
	}

	public static void main(String[] args) throws NumberFormatException, IOException {

		
	}
}