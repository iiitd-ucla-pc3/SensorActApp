package edu.iiitd.muc.sensoract.format;

import java.util.ArrayList;

import edu.iiitd.muc.sensoract.apis.SensorActAPI;

public class QueryRequest extends SensorActAPI {
	public QueryConditions conditions;
	public ArrayList<QueryDevice> devicesArray;
	public String username;
	public String interactive;

}
