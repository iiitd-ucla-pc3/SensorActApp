package edu.iiitd.muc.sensoract.format;

import java.util.ArrayList;

public class ChartSeries {
	public String name = null;
	public ArrayList<double[]> data = new ArrayList<double[]>();

	public ChartSeries(String name, ArrayList<double[]> readings) {

		this.name = name;
		this.data.addAll(readings);
	}

	public ChartSeries(String name) {
		this.name = name;
	}

}
