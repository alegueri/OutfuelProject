package UserInfo;

import java.util.Comparator;
/**
 * The GasStationADT class creates a GasStationADT type. 
 * @author Group members 
 *
 */

public class GasStationADT { // took away comparable 

		private String StateCountry = "NSW, Australia";
		private String suburb;
		private String StationName;
		private double price; // measured in $/L
		private String Address;
		private double distance; 
		private String gasTypes;
		private boolean rewards;
		private double latitude;
		private double longitude;
		/**
		 * This constructor sets the station address,price, distance, and location information.
		 * 
		 * @param  String Stationname; the origin 
		 * @param double price; the destination
		 * @param String suburb
		 * @param double distance
		 * @param gasTypes
		 * @param boolean rewards
		 * @param double latitude
		 * @param double longitude
		 * @return a GasStationADT object. 
		 */
		
		public GasStationADT(String Stationname, double price, String suburb, String Address, double distance, String gasTypes, boolean rewards, double latitude, double longitude) {
			this.StationName = Stationname;
			this.price = price;
			this.suburb = suburb;
			this.Address = Address;
			this.distance = distance; 
			this.gasTypes = gasTypes;
			this.rewards = rewards;
			this.latitude = latitude;
			this.longitude = longitude;
			
		}
		/**
		 * This sets the distance from a location to this particular gas station 
		 * @param double distance ; distance between this gas station and a certain location.  
		 */
		
		public void setDistance(double distance) {
			this.distance = distance; 
		}
		/**
		 * This gets the full address of the gas station 
		 * @return String; the full address of the gas station adt
		 */
		
		public String getFullAddress() {
			return this.Address + ", " + this.suburb + ", " + this.StateCountry;  //add suburb
		}
		/**
		 * This gets the suburb of the gas station 
		 * @return String; the suburb of the gas station adt
		 */		
		public String getSuburb() {
			return suburb;
		}
		/**
		 * This gets the station name of the gas station 
		 * @return String; the station name of the gas station adt
		 */			
		public String getStationName() {
			return StationName;
		}
		/**
		 * This gets the price of the fuels associated with the gas station 
		 * @return double; the price associated with their fuels for the gas station. 
		 */	
		public double getPrice() {
			return price;
		}
		/**
		 * This gets the address of the gas station 
		 * @return String; the address of the gas station adt
		 */
		public String getAddress() {
			return Address;
		}
		/**
		 * This gets the distance from a location to this particular gas station 
		 * @return double distance ; distance between this gas station and a certain location.  
		 */
		public double getDistance() {
			return distance;
		}
		/**
		 * This gets the gastypes of the gas station 
		 * @return String; the gas types of the gasstation.
		 */	
		public String getGasTypes() {
			return gasTypes;
		}
		/**
		 * This gets the rewards the gas station offers.  
		 * @return String; the rewards.
		 */	
		public boolean getRewards() {
			return rewards;
		}
		/**
		 * Returns the latitude of the gas station .  
		 * @return double; latitude of the gas station. 
		 */		
		public double getLatitude(){
			return this.latitude;
		}
		/**
		 * Returns the longitude of the gas station .  
		 * @return double; longitude of the gas station. 
		 */		
		public double getLongitude(){
			return this.longitude;
		}
		/**
		 * Allows to compare the prices and distances  .  
		 * @param the GasStationADT type.
		 * @param  the rank at which the user wishes to rank the gas stations. 
		 */	
		public int compareTo(GasStationADT x, String rank) {
			if(rank.equals("price")) {
			if (this.price < x.getPrice())
				return -1;
			if (this.price > x.getPrice())
				return 1;
			else if(this.StationName.compareTo(x.getStationName()) <= 0) return 0; 
			} 
			if(rank.equals("distance")) {
				if (this.distance < x.getDistance()) return -1;
				if (this.distance > x.getDistance()) return 1;
				else if(this.StationName.compareTo(x.getStationName()) <= 0) return 0;
			}
			return 10; 
		} 

		public String toString()
		{
			String str = "Station:  " + StationName + "\n" + "Address: " + Address + ", " + suburb + ", " + StateCountry + "\nPrice:    " + "¢" + Double.toString(price) + "\n" + "Distance: " + Double.toString(distance) + "miles"; 
			return str; 
		}

}
