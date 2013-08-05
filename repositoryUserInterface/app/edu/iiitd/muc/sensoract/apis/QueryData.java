/*******************************************************************************
 * /*
 * * Copyright (c) 2012, Indraprastha Institute of Information Technology,
 * * Delhi (IIIT-D) and The Regents of the University of California.
 * * All rights reserved.
 * *
 * * Redistribution and use in source and binary forms, with or without
 * * modification, are permitted provided that the following conditions
 * * are met:
 * * 1. Redistributions of source code must retain the above copyright
 * * notice, this list of conditions and the following disclaimer.
 * * 2. Redistributions in binary form must reproduce the above
 * * copyright notice, this list of conditions and the following
 * * disclaimer in the documentation and/or other materials provided
 * * with the distribution.
 * * 3. Neither the names of the Indraprastha Institute of Information
 * * Technology, Delhi and the University of California nor the names
 * * of their contributors may be used to endorse or promote products
 * * derived from this software without specific prior written permission.
 * *
 * * THIS SOFTWARE IS PROVIDED BY THE IIIT-D, THE REGENTS, AND CONTRIBUTORS
 * * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE IIITD-D, THE REGENTS
 * * OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * * SUCH DAMAGE.
 * *
 * *
 ******************************************************************************/
package edu.iiitd.muc.sensoract.apis;

import java.awt.BasicStroke;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
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
import edu.iiitd.muc.sensoract.format.APIResponse;
import edu.iiitd.muc.sensoract.format.ChartSeries;
import edu.iiitd.muc.sensoract.format.ChartSeriesArray;
import edu.iiitd.muc.sensoract.format.ChartSeriesStats;
import edu.iiitd.muc.sensoract.format.DeviceProfileFormat;
import edu.iiitd.muc.sensoract.format.GetAccessKeyResponseFormat;
import edu.iiitd.muc.sensoract.format.QueryRequest;
import edu.iiitd.muc.sensoract.format.QueryToRepo;
import edu.iiitd.muc.sensoract.format.WaveSegmentArray;
import edu.iiitd.muc.sensoract.utilities.SecretKey;
import edu.iiitd.muc.sensoract.utilities.SendHTTPRequest;

public class QueryData extends SensorActAPI {
	
	private static String userkey;

	public final void doProcess(String queryBody) {

		ArrayList<WaveSegmentArray> arrayOfResponses = new ArrayList<WaveSegmentArray>();
		QueryRequest queryRequest = gson
				.fromJson(queryBody, QueryRequest.class);
		
		String usertype = session.get(Const.USERTYPE);
		String secretkey = null;
		String vpdsURL = null;
		HttpResponse responseFromServer = null;
		
		if(usertype.equals(Const.USER)){
			
			/* Get accesskey from Broker */
			
			// From Json - vpdsname and secretkey
			String usersecretkey = new SecretKey().getSecretKeyFromHashMap(session
					.get(Const.USERNAME));
			String jsonGetAccessKey = "{\"secretkey\" : \"" + usersecretkey + "\",\"vpdsname\": \""+ queryRequest.vpdsname + "\"}";
			logger.info(Const.API_QUERYDATA, "For "+ usertype + " " +jsonGetAccessKey);
			
			// Make request
			responseFromServer = new SendHTTPRequest()
			.sendPostRequest(Const.URL_BROKER_GET_ACCESS_KEY,
					Const.MIME_TYPE_JSON, Const.API_QUERYDATA,
					jsonGetAccessKey);
			System.out.println("Get access key "+responseFromServer.getString());
			GetAccessKeyResponseFormat response = gson.fromJson(
					responseFromServer.getString(),GetAccessKeyResponseFormat.class);
			
			//Set secretkey as accesskey
			secretkey = userkey = response.accesskey;
			vpdsURL = response.vpdsurl;
		}
		else if(usertype.equals(Const.OWNER)){
			//Set secretkey as owner key
			secretkey = userkey = Global.VPDS_OWNER_KEY;			
		}

		int numberOfDevicesRequest = queryRequest.devicesArray.size();

		for (int i = 0; i < numberOfDevicesRequest; i++) {

			String devicename = queryRequest.devicesArray.get(i).device;
			int numberOfSensorsInDevice = queryRequest.devicesArray.get(i).sensorsArray
					.size();
			for (int j = 0; j < numberOfSensorsInDevice; j++) {
				QueryToRepo queryToRepo = new QueryToRepo(
						queryRequest.conditions,
						devicename,
						queryRequest.devicesArray.get(i).sensorsArray.get(j).sensorname,
						queryRequest.devicesArray.get(i).sensorsArray.get(j).sensorid,
						queryRequest.username, secretkey);
				String queryBodyWithSecretKey = gson.toJson(queryToRepo);
				logger.info(Const.API_QUERYDATA, "------Sending Request to VPDS--------");
				if(usertype.equals(Const.USER)){
					responseFromServer = new SendHTTPRequest()
					.sendPostRequest(vpdsURL + "data/query",
							Const.MIME_TYPE_JSON, Const.API_QUERYDATA,
							queryBodyWithSecretKey);
				}
				else {
					responseFromServer = new SendHTTPRequest()
					.sendPostRequest(Global.URL_REPOSITORY_QUERY_DATA,
							Const.MIME_TYPE_JSON, Const.API_QUERYDATA,
							queryBodyWithSecretKey);
				}
				logger.info(Const.API_QUERYDATA, "------Finished Receiving Data--------");
				//System.out.println("Data response: "+responseFromServer.getString());
				
				try {
					
					WaveSegmentArray wa = gson.fromJson(
							responseFromServer.getString(), WaveSegmentArray.class);
					/*
					 * If the size of WaveSegmentArray is 0, that is no data is found, then in that 
					 * case, it is not to be added to arrayOfResponses
					 */
					if (wa.wavesegmentArray.size()>0)
					{
						arrayOfResponses.add(wa);
					}
				}
				catch (NullPointerException e) {
					renderJSON(gson.toJson(new APIResponse(Const.API_QUERYDATA, 1, "Error retrieving data!")));
				}
				catch (Exception e) {
					e.printStackTrace();
					renderJSON(gson.toJson(new APIResponse(Const.API_QUERYDATA, 1, "Error retrieving data!")));
				}
				

			}

		}
		
		if (arrayOfResponses.size()==0)
		{
			renderText("No Data Found");
		}

		/*
		 * @TODO : Automatically do mode checking based on size of data
		 */
		if (queryRequest.interactive.equals("false")) {

			processNonInteractive(arrayOfResponses, vpdsURL);
		} else {
			processInteractive(arrayOfResponses, vpdsURL);
		}

	}

	public void processNonInteractive(
			ArrayList<WaveSegmentArray> arrayOfResponses, String vpdsURL) {
		long t1 = new Date().getTime();
		String a = createChart(arrayOfResponses, vpdsURL);		
		String re = "{\"filename\":\"" + a + ".png\"}";
		long t2 = new Date().getTime();
		logger.info(Const.API_QUERYDATA, "Time to create non-interactive graph:" + (t2-t1)/1000 + " seconds");
		renderJSON(re);

	}

	private void processInteractive(ArrayList<WaveSegmentArray> arrayOfResponses, String vpdsURL) {
		long t1 = new Date().getTime();
		int size = arrayOfResponses.size();
		int seriesOffset = 0;
		String usertype = session.get(Const.USERTYPE);
		HttpResponse responseFromServer = null;
		
		ChartSeriesArray ca = new ChartSeriesArray();
		logger.info(Const.API_QUERYDATA, "Interactive:: Data Size:" + size);
		
		for (int a = 0; a < size; a++) {
			//System.out.println("For waveseg " + Integer.toString(a) + " ");
			WaveSegmentArray wa = arrayOfResponses.get(a);
			
			String devicename = wa.wavesegmentArray.get(0).data.dname;
			// Get device details
			
			String getDeviceProfile = "{\"secretkey\" : \"" + userkey +"\", \"devicename\": \""+devicename+"\" }";
			//System.out.println("Get device request" + getDeviceProfile);
			if(usertype.equals(Const.USER)){
				responseFromServer = new SendHTTPRequest()
				.sendPostRequest(vpdsURL + "device/get",
						Const.MIME_TYPE_JSON, Const.API_GETDEVICE,
						getDeviceProfile);
			}
			else {
				responseFromServer = new SendHTTPRequest()
				.sendPostRequest(Global.URL_REPOSITORY_GET_DEVICE,
						Const.MIME_TYPE_JSON, Const.API_GETDEVICE,
						getDeviceProfile);
			}
			
			DeviceProfileFormat device = gson.fromJson(
					responseFromServer.getString(),DeviceProfileFormat.class);
			//System.out.println("Get device response" + responseFromServer.getString());

			int numberOfWavesegs = wa.wavesegmentArray.size();
			int numberOfSeries = wa.wavesegmentArray.get(0).data.channels
					.size();
			String sensorname = wa.wavesegmentArray.get(0).data.sname;

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
			// Get sensor index of the sensor whose data is being processed
			// to compare it to the device profile retrieved from the VPDS
			
			int sindex = 0;
			for (int i = 0; i< device.sensors.size(); i++)
				if (device.sensors.get(i).name.equals(sensorname))
					sindex = i;

			for (int i = 0; i < numberOfWavesegs; i++) {
				long timestamp = wa.wavesegmentArray.get(i).data.timestamp * 1000;
				
				//initialization of sampling period
				int samplingPeriod = 1;
				//System.out.println(timestamp);

				for (int j = 0; j < numberOfSeries; j++) {
					try{

						int numberOfReadings = wa.wavesegmentArray.get(i).data.channels
								.get(j).readings.size();
						String channelname = wa.wavesegmentArray.get(i).data.channels
								.get(j).cname; 

						Double min = wa.wavesegmentArray.get(i).data.channels
								.get(j).readings.get(0);
						Double max = wa.wavesegmentArray.get(i).data.channels
								.get(j).readings.get(0);
						Double avg = 0.0;
						if (device.sensors.get(sindex).channels.get(j).name.equals(channelname))
							samplingPeriod = device.sensors.get(sindex).channels.get(j).samplingperiod;
						
						System.out.println();

						for (int k = 0; k < numberOfReadings; k++) {
							double[] d = new double[2];
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
					catch(NullPointerException e){
						logger.error(Const.API_QUERYDATA + ": NullPointerError: Check packet. Possible error - readings missing");
					}
				}
			}
			seriesOffset += numberOfSeries;

		}
		// System.out.println(gson.toJson(ca));
		long t2 = new Date().getTime();
		logger.info(Const.API_QUERYDATA, "Time to create interactive graph:" + (t2-t1)/1000 +" seconds");
		renderJSON(gson.toJson(ca));

	}

	public String createChart(ArrayList<WaveSegmentArray> arrayOfResponses, String vpdsURL) {
		logger.info(Const.API_QUERYDATA, "Static Graph:: Data Size:" + arrayOfResponses.size());	
		XYDataset dataset = createDataset(arrayOfResponses, vpdsURL);
		JFreeChart chart = createJFreeChart(dataset);
		String uuid = UUID.randomUUID().toString();

		try {
			String path = QueryData.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			String decodedPath = URLDecoder.decode(path, "UTF-8");
			ChartUtilities.saveChartAsPNG(new File(decodedPath+"/"+Const.BASE_IMAGE_URL + uuid
					+ ".png").getCanonicalFile(), chart, 800, 800);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return uuid;

	}

	public JFreeChart createJFreeChart(XYDataset dataset) {
		//System.out.println(dataset);
		JFreeChart chart = ChartFactory.createTimeSeriesChart("Visualization", // title
				"Date-Time", // x-axis label
				"Readings", // y-axis label
				dataset, // data
				true, // create legend?
				true, // generate tooltips?
				false // generate URLs?
				);

		chart.setBackgroundPaint(Color.white);

		XYPlot plot = (XYPlot) chart.getPlot();
		plot.setBackgroundPaint(Color.white);
		plot.setDomainGridlinePaint(Color.black);
		plot.setRangeGridlinePaint(Color.black);
		plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
		plot.setDomainCrosshairVisible(true);
		plot.setRangeCrosshairVisible(true);

		XYItemRenderer r = plot.getRenderer();
		if (r instanceof XYLineAndShapeRenderer) {
			XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
			//renderer.setSeriesStroke(0, new BasicStroke(1.1f));
			//plot.setRenderer(renderer);
		}

		DateAxis axis = (DateAxis) plot.getDomainAxis();
		axis.setDateFormatOverride(new SimpleDateFormat("d-M-yy H:m:s"));
		
		return chart;
	}

	public XYDataset createDataset(ArrayList<WaveSegmentArray> arrayOfResponses, String vpdsURL) {
		TimeSeriesCollection dataset = new TimeSeriesCollection();
		
						
		int numberOfResponses = arrayOfResponses.size();
		String usertype = session.get(Const.USERTYPE);
		HttpResponse responseFromServer = null;
		
		if (numberOfResponses>0)
		for (int i = 0; i < numberOfResponses; i++) {
			WaveSegmentArray wa = arrayOfResponses.get(i);
			
			String devicename = wa.wavesegmentArray.get(0).data.dname;
			// Get device details
			
			String getDeviceProfile = "{\"secretkey\" : \"" + userkey +"\", \"devicename\": \""+devicename+"\" }";
			System.out.println("Get device request" + getDeviceProfile);
			if(usertype.equals(Const.USER)){
				responseFromServer = new SendHTTPRequest()
				.sendPostRequest(vpdsURL + "device/get",
						Const.MIME_TYPE_JSON, Const.API_GETDEVICE,
						getDeviceProfile);
			}
			else {
				responseFromServer = new SendHTTPRequest()
				.sendPostRequest(Global.URL_REPOSITORY_GET_DEVICE,
						Const.MIME_TYPE_JSON, Const.API_GETDEVICE,
						getDeviceProfile);
			}
			
			DeviceProfileFormat device = gson.fromJson(
					responseFromServer.getString(),DeviceProfileFormat.class);
			System.out.println("Get device response" + responseFromServer.getString());

			
			int numberOfSeries = wa.wavesegmentArray.get(0).data.channels
					.size();
			int numberOfWavesegs = wa.wavesegmentArray.size();
			String sensorname = wa.wavesegmentArray.get(0).data.sname;
			
			TimeSeries s1[] = new TimeSeries[numberOfSeries];
			
			for (int j = 0; j < numberOfSeries; j++) {
				s1[j] = new TimeSeries(
						wa.wavesegmentArray.get(0).data.channels.get(j).cname
								+ " " + wa.wavesegmentArray.get(0).data.sname
								+ " " + wa.wavesegmentArray.get(0).data.dname,
						Millisecond.class);

			}
			
			// Get sensor index of the sensor whose data is being processed
			// to compare it to the device profile retrieved from the VPDS

			int sindex = 0;
			for (int idx = 0; idx < device.sensors.size(); idx++)
				if (device.sensors.get(i).name.equals(sensorname))
					sindex = idx;

			for (int a = 0; a < numberOfWavesegs; a++) {

				long timestamp = wa.wavesegmentArray.get(a).data.timestamp * 1000;
				
				// initialization of sampling period
				int samplingPeriod = 1;

				for (int j = 0; j < numberOfSeries; j++) {
					
					try{

						int numberOfReadings = wa.wavesegmentArray.get(a).data.channels
								.get(j).readings.size();
						String channelname = wa.wavesegmentArray.get(i).data.channels
								.get(j).cname; 
						if (device.sensors.get(sindex).channels.get(j).name.equals(channelname))
							samplingPeriod = device.sensors.get(sindex).channels.get(j).samplingperiod;

						for (int k = 0; k < numberOfReadings; k++) {

							Millisecond x = new Millisecond(new Date(
									new Double((timestamp + k * samplingPeriod
											* 1000)).longValue()));

							double y = wa.wavesegmentArray.get(a).data.channels
									.get(j).readings.get(k);

							s1[j].addOrUpdate(x, y);


						}
					}
					catch(NullPointerException e){
						logger.error(Const.API_QUERYDATA + ": Null Pointer: Check packet. Possible error - readings missing");
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
