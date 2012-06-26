package edu.iiitd.muc.sensoract.format;

import edu.iiitd.muc.sensoract.apis.SensorActAPI;

public class MicrocontrollerErrorResponse extends SensorActAPI {

	public String Description;
	public int ERRCODE;

	public MicrocontrollerErrorResponse(String description, int eRRCODE) {
		// super();
		this.Description = description;
		this.ERRCODE = eRRCODE;
	}

}
