package edu.iiitd.muc.sensoract.format;


public class ChartSeriesStats {

	public String name;
	public Double min;
	public Double max;
	public Double avg;

	public ChartSeriesStats(String name, Double min, Double max, Double avg) {

		this.name = name;
		this.min = min;
		this.max = max;
		this.avg = avg;
	}

	public ChartSeriesStats(String name) {

		this.name = name;
	}

}
