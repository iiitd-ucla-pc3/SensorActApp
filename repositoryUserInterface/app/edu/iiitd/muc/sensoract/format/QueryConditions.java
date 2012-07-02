package edu.iiitd.muc.sensoract.format;

import edu.iiitd.muc.sensoract.apis.SensorActAPI;

public class QueryConditions extends SensorActAPI {
	public long fromtime;
	public long totime;

	public QueryConditions(long fromtime, long totime) {

		this.fromtime = fromtime;
		this.totime = totime;
	}

}
