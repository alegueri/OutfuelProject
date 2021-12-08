package UserInfo;

import java.io.IOException;
/**
 * This class retrieves the distances of two locations based on latitude and longitude. 
 * @author Alessandra Guerinoni and Max Mercer
 *@version 1.0 
 */
public class UsingDistance {
	/**
	 * This routine updates the distances within the GasStationADT[] array using the distance method below. 
	 * 
	 * @param GasStationADT[] array;
	 * @param double startingLatitude
	 * @param double startingLongitude
	 * @throw  IOException
	 * 
	 */

	  public static void updateDistances(GasStationADT[] array, double startingLatitude, double startingLongitude) throws IOException {
		  double endingLatitude, endingLongitude, distance;
		  
		  for (int i = 0; i < array.length; i++) {

		    	if( array[i].getLatitude()>startingLatitude -0.5 && array[i].getLatitude() < startingLatitude +0.5) {
				endingLatitude = array[i].getLatitude(); 
				endingLongitude = array[i].getLongitude(); 
		
				distance = distance(startingLatitude, endingLatitude, startingLongitude, endingLongitude);
				array[i].setDistance(distance); 
		    	}
				else{
			    		array[i].setDistance(100000); 
			    	}
				}
			//System.out.println(array[i]);
			//	System.out.println(i); 
		}
		/**
		 * This routine uses a distance formula to find the distance of two locations using latitude and longitude. 
		 * 
		 * @param double lat1 the origin latitude
		 * @param double lat2 the destination latitude 
		 * @param double lon1 the origin longitude 
		 * @param  double lon2 the destination longtitude 
		 * 
		 */
	    
	  private static double distance(double lat1, double lat2, double lon1, double lon2) {
		  	if(lat1 == lat2 && lon1 == lon2) return 0.0; 
		    final int R = 6371; // Radius of the earth

		    double latDistance = (lat2 - lat1)*(Math.PI/180);
		    double lonDistance = (lon2 - lon1)*(Math.PI/180);
		    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
		            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
		            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		    double distance = R *c ; 

		    return distance*0.621371;
		}

}