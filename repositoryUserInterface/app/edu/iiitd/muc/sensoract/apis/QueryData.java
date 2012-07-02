package edu.iiitd.muc.sensoract.apis;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RectangleInsets;

import play.libs.WS.HttpResponse;
import edu.iiitd.muc.sensoract.constants.Const;
import edu.iiitd.muc.sensoract.format.ChartSeries;
import edu.iiitd.muc.sensoract.format.ChartSeriesArray;
import edu.iiitd.muc.sensoract.format.ChartSeriesStats;
import edu.iiitd.muc.sensoract.format.QueryRequest;
import edu.iiitd.muc.sensoract.format.QueryToRepo;
import edu.iiitd.muc.sensoract.format.WaveSegmentArray;
import edu.iiitd.muc.sensoract.utilities.SendHTTPRequest;

public class QueryData extends SensorActAPI {

	public final void doProcess(String queryBody) {

		ArrayList<WaveSegmentArray> arrayOfResponses = new ArrayList<WaveSegmentArray>();
		QueryRequest queryRequest = gson
				.fromJson(queryBody, QueryRequest.class);

		int numberOfDevicesRequest = queryRequest.devicesArray.size();

		for (int i = 0; i < numberOfDevicesRequest; i++) {

			String devicename = queryRequest.devicesArray.get(i).device;
			int numberOfSensorsInDevice = queryRequest.devicesArray.get(i).sensorsArray
					.size();
			for (int j = 0; j < numberOfSensorsInDevice; j++) {
				QueryToRepo queryToRepo = new QueryToRepo(
						queryRequest.conditions,
						devicename,
						queryRequest.devicesArray.get(i).sensorsArray.get(j).sensor,
						queryRequest.username);
				String queryBodyWithSecretKey = gson.toJson(queryToRepo);
				HttpResponse responseFromBroker = new SendHTTPRequest()
						.sendPostRequest(Const.URL_REPOSITORY_QUERY_DATA,
								Const.MIME_TYPE_JSON, Const.API_QUERYDATA,
								queryBodyWithSecretKey);
				WaveSegmentArray wa = gson.fromJson(
						responseFromBroker.getString(), WaveSegmentArray.class);
				arrayOfResponses.add(wa);

			}

		}

		System.out.println(queryRequest.interactive);
		System.out.println(queryRequest.equals("true"));

		if (queryRequest.interactive.equals("false")) {

			processNonInteractive(arrayOfResponses);
		} else {
			processInteractive(arrayOfResponses);
		}

	}

	public void processNonInteractive(
			ArrayList<WaveSegmentArray> arrayOfResponses) {
		String a = createChart(arrayOfResponses);
		renderText(a);

	}

	private void processInteractive(ArrayList<WaveSegmentArray> arrayOfResponses) {
		int size = arrayOfResponses.size();
		int seriesOffset = 0;
		ChartSeriesArray ca = new ChartSeriesArray();
		for (int a = 0; a < size; a++) {
			WaveSegmentArray wa = arrayOfResponses.get(a);

			int numberOfWavesegs = wa.wavesegmentArray.size();
			int numberOfSeries = wa.wavesegmentArray.get(0).data.channels
					.size();

			for (int i = 0; i < numberOfSeries; i++) {
				ca.chartSeries.add(new ChartSeries(
						wa.wavesegmentArray.get(0).data.channels.get(i).cname
								+ " " + wa.wavesegmentArray.get(0).data.sname
								+ " " + wa.wavesegmentArray.get(0).data.dname));

				ca.chartSeriesStats.add(new ChartSeriesStats(
						wa.wavesegmentArray.get(0).data.channels.get(i).cname
								+ " " + wa.wavesegmentArray.get(0).data.sname
								+ " " + wa.wavesegmentArray.get(0).data.dname));

			}

			for (int i = 0; i < numberOfWavesegs; i++) {
				long timestamp = wa.wavesegmentArray.get(i).data.timestamp;
				int samplingPeriod = 1;

				for (int j = 0; j < numberOfSeries; j++) {

					int numberOfReadings = wa.wavesegmentArray.get(i).data.channels
							.get(j).readings.size();

					Double min = wa.wavesegmentArray.get(i).data.channels
							.get(j).readings.get(0);
					Double max = wa.wavesegmentArray.get(i).data.channels
							.get(j).readings.get(0);
					Double avg = 0.0;

					double[] d = new double[2];
					for (int k = 0; k < numberOfReadings; k++) {

						d[0] = timestamp + k * samplingPeriod * 1000;
						d[1] = wa.wavesegmentArray.get(i).data.channels.get(j).readings
								.get(k);
						ca.chartSeries.get(j + seriesOffset).data.add(d);
						// Min Value
						if (min > d[1])
							min = d[1];

						// Max Value
						if (max < d[1])
							max = d[1];

						// Avg Value
						avg += d[1];
					}

					ca.chartSeriesStats.get(j + seriesOffset).min = min;
					ca.chartSeriesStats.get(j + seriesOffset).max = max;
					ca.chartSeriesStats.get(j + seriesOffset).avg = avg
							/ numberOfReadings;
				}
			}
			seriesOffset += numberOfSeries;

		}
		System.out.println(gson.toJson(ca));
		renderJSON(gson.toJson(ca));

	}

	public String createChart(ArrayList<WaveSegmentArray> arrayOfResponses) {
		XYDataset dataset = createDataset(arrayOfResponses);
		JFreeChart chart = createJFreeChart(dataset);
		String uuid = UUID.randomUUID().toString();

		try {
			System.out.println("Creating image");
			ChartUtilities.saveChartAsPNG(new File(
					"/home/nipun/git/SensorActApp/repositoryUserInterface/public/"
							+ uuid + ".png"), chart, 800, 800);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return uuid;

	}

	public JFreeChart createJFreeChart(XYDataset dataset) {
		JFreeChart chart = ChartFactory.createTimeSeriesChart("Visulization", // title
				"Date-Time", // x-axis label
				"Readings", // y-axis label
				dataset, // data
				true, // create legend?
				true, // generate tooltips?
				false // generate URLs?
				);

		chart.setBackgroundPaint(Color.white);

		XYPlot plot = (XYPlot) chart.getPlot();
		plot.setBackgroundPaint(Color.lightGray);
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinePaint(Color.white);
		plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
		plot.setDomainCrosshairVisible(true);
		plot.setRangeCrosshairVisible(true);

		XYItemRenderer r = plot.getRenderer();
		if (r instanceof XYLineAndShapeRenderer) {
			XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;

		}

		DateAxis axis = (DateAxis) plot.getDomainAxis();
		axis.setDateFormatOverride(new SimpleDateFormat("d-M-yy H:m:s"));

		return chart;
	}

	public XYDataset createDataset(ArrayList<WaveSegmentArray> arrayOfResponses) {
		TimeSeriesCollection dataset = new TimeSeriesCollection();
		int numberOfResponses = arrayOfResponses.size();
		for (int i = 0; i < numberOfResponses; i++) {
			WaveSegmentArray wa = arrayOfResponses.get(i);
			int numberOfSeries = wa.wavesegmentArray.get(0).data.channels
					.size();
			int numberOfWavesegs = wa.wavesegmentArray.size();
			TimeSeries s1[] = new TimeSeries[numberOfSeries];
			for (int j = 0; j < numberOfSeries; j++) {
				s1[j] = new TimeSeries(
						wa.wavesegmentArray.get(0).data.channels.get(j).cname
								+ " " + wa.wavesegmentArray.get(0).data.sname
								+ " " + wa.wavesegmentArray.get(0).data.dname,
						Millisecond.class);

			}

			for (int a = 0; a < numberOfWavesegs; a++) {

				long timestamp = wa.wavesegmentArray.get(a).data.timestamp;
				int samplingPeriod = 1;

				for (int j = 0; j < numberOfSeries; j++) {

					int numberOfReadings = wa.wavesegmentArray.get(a).data.channels
							.get(j).readings.size();

					for (int k = 0; k < numberOfReadings; k++) {

						Millisecond x = new Millisecond(new Date(
								new Double((timestamp + k * samplingPeriod
										* 1000)).longValue()));

						double y = wa.wavesegmentArray.get(a).data.channels
								.get(j).readings.get(k);

						s1[j].addOrUpdate(x, y);

					}

				}

			}
			for (int j = 0; j < numberOfSeries; j++) {
				dataset.addSeries(s1[j]);
			}

		}

		// TODO Auto-generated method stub
		return dataset;
	}
}