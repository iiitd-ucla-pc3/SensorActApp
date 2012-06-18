package edu.iiitd.muc.sensoract.format;

import java.util.ArrayList;

public class ChartSeries {
	public String name = null;
	public ArrayList<Double[]> data = new ArrayList<Double[]>();

	public ChartSeries(String name, ArrayList<Double[]> readings) {

		this.name = name;
		this.data.addAll(readings);
	}

	public ChartSeries(String name) {
		this.name = name;
	}

}
