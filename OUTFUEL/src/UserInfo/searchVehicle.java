package UserInfo;

import java.io.IOException;
/**
 * Finds a specified vehicle from an array. 
 * @return returns null if the car cannot be found. 
 * @author Max Mercer
 *
 */
public class searchVehicle {
	/**
	 * Finds a specified vehicle from an array. 
	 * @param VehicleADT[] array
	 * @param int year
	 * @param String model
	 * @param String brand
	 * @return returns null if the car cannot be found. 
	 */
	public static VehicleADT search(VehicleADT[] array, int year, String model, String brand) {
		for (int i = 0 ; i < array.length ; i++) {
			if (array[i].getModel().toLowerCase().contains(model.toLowerCase())) {
				if (array[i].getBrand().toLowerCase().contains(brand.toLowerCase())) {
					if (array[i].getYear() == year)
						return array[i];
			}
		}
	}
		return null;
	}
	

}
