package edu.iiitd.muc.sensoract.format;

import java.util.ArrayList;

import edu.iiitd.muc.sensoract.apis.SensorActAPI;

public class ChartSeriesArray extends SensorActAPI {
	public ArrayList<ChartSeries> chartSeries;
	public ArrayList<ChartSeriesStats> chartSeriesStats;

	public ChartSeriesArray() {
		this.chartSeries = new ArrayList<ChartSeries>();
		this.chartSeriesStats = new ArrayList<ChartSeriesStats>();

	}

}