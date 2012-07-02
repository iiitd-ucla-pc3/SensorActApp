package edu.iiitd.muc.sensoract.format;

import java.util.ArrayList;

import edu.iiitd.muc.sensoract.apis.SensorActAPI;

public class QueryDevice extends SensorActAPI {
	public String device;
	public ArrayList<QueryDeviceSensors> sensorsArray;

}
