package UserInfo;
/**
 * This class implements the VehicleADT. 
 * @author group members. 
 *@version 1.0 
 */
public class VehicleADT {
	
	private int year;
	private String brand;
	private String model;
	private String GasType;
	private double FuelTankCapac; //In Litres
	private double currentGas; //In litres
	private double averageMPG;

	   
		/**
		 * This returns a VehicleADT with information regarding the vehicle.  
		 * 
		 * @param int year; the year of the car 
		 * @param String model; the model of the car  
		 * @param String GasType; the gas type of the car. 
		 * @param double capacity; the fuel capacity of the car 
		 * @param double currentGas; the amount currently in the car
		 * @param double aveMPG; the average MPG of the car.
		 * @return a VehicleADT. 
		 * 
		 */
	public VehicleADT(int year, String brand, String model, String GasType, double capacity, double currentGas, double aveMPG){
		
		this.year = year;
		this.brand = brand;
		this.model = model;
		this.GasType = GasType;
		this.FuelTankCapac = capacity;
		this.currentGas = currentGas;
		this.averageMPG = aveMPG;
		
	}
	/**
	 * Allows users to change the amount of gas currently in their car (After a refill) .  
	 * 
	 * @param double gas; the amount of gas in their car 
	 */
	public void set_gas(double gas) {
		currentGas = gas;
	}
	/**
	 * The year of their car   
	 * 
	 * @return int year; the year of their car
	 */
	public int getYear(){
		return year;
	}
	/**
	 * The brand of the user's car.    
	 * 
	 * @return String brand; 
	 */
	public String getBrand(){
		return brand;
	}
	/**
	 * The model of the user's car.    
	 * 
	 * @return String model; 
	 */
	public String getModel(){
		return model;
	}
	/**
	 * getGastype, retrieves the gas type of the car.     
	 * 
	 * @return String GasType; 
	 */
	public String getGasType(){
		return GasType;
	}
	/**
	 * getGastype, retrieves the fuel tank capacity.    
	 * 
	 * @return double FuelTankCapac; 
	 */
	public double getFuelTankCapac(){
		return FuelTankCapac;
	}
	/**
	 * getGastype, retrieves the car's current gas.    
	 * 
	 * @return double currentGas; 
	 */
	public double getCurrentGas(){
		return currentGas;
	}
	/**
	 * getGastype, retrieves the car's averageMPG.    
	 * 
	 * @return double averageMPG; 
	 */
	public double getAverageMPG(){
		return averageMPG;
	}
	/**
	 * getGastype, retrieves the miles left in their car.     
	 * 
	 * @return double; 
	 */
	public double milesInTank(){
		return this.averageMPG * currentGas;
	}
	/**
	 * getGastype, retrieves the gallons of gas needed for a certain distance.     
	 * 
	 * @return double; 
	 */	
	public double gallonsOfGasNeeded(double distance){
		return distance / this.averageMPG ; 
	}
	
	public String toString()
	{
		String str = Integer.toString(year) + " " + brand + " " + model + " " + GasType + " " + Double.toString(FuelTankCapac) + " " + Double.toString(currentGas) + " " + Double.toString(averageMPG); 
		return str; 
	}


}
