package UserInfo;
import java.util.Map;

import java.util.Scanner;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.io.IOException;

/** 
 * DistanceGoogle uses the google api to find the distances between two locations. Must keep in mind how you input the address, as an empty_string exception gets thrown if
 * google cannot find the location 
 * WORK CITED:  https://stackoverflow.com/questions/40561264/how-to-use-google-maps-distance-matrix-java-api-to-obtain-closest-distance-betwe
 * @author Ale
 *
 */
public class DistanceGoogle {

	  private static final String API_KEY = "AIzaSyBDKTv6rqlnRPePpq4glG0ZOH8_Zpzwbwc";

	  OkHttpClient client = new OkHttpClient();

	  public String run(String url) throws IOException {
	    Request request = new Request.Builder()
	        .url(url)
	        .build();

	    Response response = client.newCall(request).execute();
	    return response.body().string(); }

	  public static double distance(String origin, String destination) throws IOException{
	    DistanceGoogle request = new DistanceGoogle();
	    String url_request = "https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins="+origin+"&destinations=" +destination+"&key=" + API_KEY;
	    String response = request.run(url_request);
	    int index = response.indexOf("value") + 9;
	    String strdis = response.substring(index, index+5); 
	    String dis1 = ""; 
	    for(int i =0; i < strdis.length(); i++) 
	    		if(Character.isDigit(strdis.charAt(i))) dis1 = dis1 + strdis.charAt(i); 

	    double dis = Double.parseDouble(dis1) /1000; 
	    return dis;
	  
	  }
	  
	  }
