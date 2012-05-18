package edu.iiitd.muc.sensoract.format;

import edu.iiitd.muc.sensoract.apis.SensorActAPI;

public class APIResponse extends SensorActAPI {

	public String apiname;
	public int statuscode;
	public String message;
	public APIResponse(){
		
	}
	public APIResponse(String apiname, int statuscode, String message) {
		this.apiname=apiname;
		this.statuscode=statuscode;
		this.message=message;
	}
	
}
