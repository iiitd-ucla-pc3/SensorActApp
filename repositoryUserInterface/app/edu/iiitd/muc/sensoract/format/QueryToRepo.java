package edu.iiitd.muc.sensoract.format;

import edu.iiitd.muc.sensoract.apis.SensorActAPI;

public class QueryToRepo extends SensorActAPI {
	public QueryConditions conditions;
	public String devicename;
	public String sensorname;
	public String username;

	public QueryToRepo(QueryConditions conditions, String devicename,
			String sensorname, String username) {
		this.conditions = conditions;
		this.devicename = devicename;
		this.sensorname = sensorname;
		this.username = username;
	}

}
