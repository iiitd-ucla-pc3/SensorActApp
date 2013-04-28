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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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
import org.jfree.util.Log;

import play.libs.WS.HttpResponse;
import edu.iiitd.muc.sensoract.constants.Const;
import edu.iiitd.muc.sensoract.format.APIResponse;
import edu.iiitd.muc.sensoract.format.ChartSeries;
import edu.iiitd.muc.sensoract.format.ChartSeriesArray;
import edu.iiitd.muc.sensoract.format.ChartSeriesStats;
import edu.iiitd.muc.sensoract.format.DownloadDataRequest;
import edu.iiitd.muc.sensoract.format.GetAccessKeyResponseFormat;
import edu.iiitd.muc.sensoract.format.QueryRequest;
import edu.iiitd.muc.sensoract.format.QueryToRepo;
import edu.iiitd.muc.sensoract.format.WaveSegmentArray;
import edu.iiitd.muc.sensoract.utilities.SecretKey;
import edu.iiitd.muc.sensoract.utilities.SendHTTPRequest;

/** 
 * /downloadata API: Retrieves data values and generates a CSV for each sensor request
 * input: request containing the time period and the sensor info
 * output: zip file containing all the CSVs for the sensors as per request
 * 
 * @author: Manaswi Saha
 * 
 */

public class DownloadData extends SensorActAPI {

	public final void doProcess(String queryBody) {

		ArrayList<WaveSegmentArray> arrayOfResponses = new ArrayList<WaveSegmentArray>();
		DownloadDataRequest queryRequest = gson
				.fromJson(queryBody, DownloadDataRequest.class);
		
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
			logger.info(Const.API_DOWNLOADATA, "For "+ usertype + " " +jsonGetAccessKey);
			
			// Make request
			responseFromServer = new SendHTTPRequest()
			.sendPostRequest(Const.URL_BROKER_GET_ACCESS_KEY,
					Const.MIME_TYPE_JSON, Const.API_DOWNLOADATA,
					jsonGetAccessKey);
			System.out.println("Get access key "+responseFromServer.getString());
			GetAccessKeyResponseFormat response = gson.fromJson(
					responseFromServer.getString(),GetAccessKeyResponseFormat.class);
			
			//Set secretkey as accesskey
			secretkey = response.accesskey;
			vpdsURL = response.vpdsurl;
		}
		else if(usertype.equals(Const.OWNER)){
			//Set secretkey as owner key
			secretkey = Global.VPDS_OWNER_KEY;			
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
				logger.info(Const.API_DOWNLOADATA, "------Sending Request to VPDS--------");
				if(usertype.equals(Const.USER)){
					responseFromServer = new SendHTTPRequest()
					.sendPostRequest(vpdsURL + "data/query",
							Const.MIME_TYPE_JSON, Const.API_DOWNLOADATA,
							queryBodyWithSecretKey);
				}
				else {
					responseFromServer = new SendHTTPRequest()
					.sendPostRequest(Global.URL_REPOSITORY_QUERY_DATA,
							Const.MIME_TYPE_JSON, Const.API_DOWNLOADATA,
							queryBodyWithSecretKey);
				}
				logger.info(Const.API_DOWNLOADATA, "------Finished Receiving Data--------");
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
					renderJSON(gson.toJson(new APIResponse(Const.API_DOWNLOADATA, 1, "Error retrieving data!")));
				}
				

			}

		}
		
		if (arrayOfResponses.size()==0)
		{
			renderText("No Data Found");
		}
		
		createCSV(arrayOfResponses);
	}
	
	/** Step 1: Create CSV for each sensor data response **/
	private void createCSV(ArrayList<WaveSegmentArray> arrayOfResponses) {
		long t1 = new Date().getTime();
		int size = arrayOfResponses.size();
		logger.info("NoOfSensors:" + size);
		ArrayList<String> fileList = new ArrayList<String>();
		
		for (int sIndex = 0; sIndex < size; sIndex++){			
			
			WaveSegmentArray wa = arrayOfResponses.get(sIndex);
			int numberOfWavesegs = wa.wavesegmentArray.size();
			int numberOfChannels = wa.wavesegmentArray.get(0).data.channels
					.size();
			String filename = session.get(Const.USERNAME) + "_" + wa.wavesegmentArray.get(0).data.dname + "_" + 
					wa.wavesegmentArray.get(0).data.sname + "_" + wa.wavesegmentArray.get(0).data.sid +".csv";
			
			try {
				
				String path = DownloadData.class.getProtectionDomain().getCodeSource().getLocation().getPath();
				String decodedPath = URLDecoder.decode(path, "UTF-8");
				String sFileName = decodedPath + "/" + Const.BASE_OUTPUTCSV_URL + filename ;
				FileWriter writer = new FileWriter(sFileName);
				writer.append("timestamp,channel,value");
			    writer.append('\n');
				
				for (int i = 0; i < numberOfWavesegs; i++) {
					long timestamp = wa.wavesegmentArray.get(i).data.timestamp;
					//TODO: get it from the UI client side
					int samplingPeriod = 1;
					
					for (int j = 0; j < numberOfChannels; j++) {
						try{
							int numberOfReadings = wa.wavesegmentArray.get(i).data.channels
									.get(j).readings.size();
						
							// From readings array, separate out into a single record for each second
							for (int k = 0; k < numberOfReadings; k++) {							

								long timestampSensorValue = timestamp + k * samplingPeriod; 	//timestamp value
								double value = wa.wavesegmentArray.get(i).data.channels.get(j).readings
										.get(k);						//single reading							
								writer.append(String.valueOf(timestampSensorValue) + ',' +
										wa.wavesegmentArray.get(i).data.channels.get(j).cname + ',' +
										String.valueOf(value));
								writer.append('\n');							
							}
						}
						catch(NullPointerException e){
							logger.error(Const.API_DOWNLOADATA + ": Null Pointer: Check packet. Possible error - readings missing");
						}
					}					
				}
				writer.flush();
			    writer.close();
			}
			catch (IOException e){
			     e.printStackTrace();
			}	
			fileList.add(filename);
		}
		long t2 = new Date().getTime();
		logger.info(Const.API_DOWNLOADATA, "Time to create csv files:" + (t2-t1)/1000 + " seconds");
		createAndSendZipFile(fileList);		
	}

	private void createAndSendZipFile(ArrayList<String> fileList) {
		// Create zip file and delete associated csv files
		long t1 = new Date().getTime();
		try {
			String path = DownloadData.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			String decodedPath = URLDecoder.decode(path, "UTF-8");
			String csvFilePath = decodedPath + "/" + Const.BASE_OUTPUTCSV_URL;
			String uuid = UUID.randomUUID().toString();
			String zipFile = session.get(Const.USERNAME) + uuid + ".zip";
			try {
				FileOutputStream fos = new FileOutputStream(csvFilePath + zipFile);
				ZipOutputStream zos = new ZipOutputStream(fos);
				
				for(int i = 0; i < fileList.size(); i++)
					addToZipFile(Const.BASE_OUTPUTCSV_URL + fileList.get(i), zos);
				
				zos.close();
				fos.close();

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			String re = "{\"filename\":\""+ zipFile+ "\"}";
			long t2 = new Date().getTime();
			logger.info(Const.API_DOWNLOADATA, "Time to create zip file + " + zipFile + ": " + (t2-t1)/1000 + " seconds");
			renderJSON(re);
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
	}

	private void addToZipFile(String fileName, ZipOutputStream zos) throws FileNotFoundException, IOException {
		
		File file = new File(fileName);
		String fileEntry = fileName.replace(Const.BASE_OUTPUTCSV_URL, "");
		FileInputStream fis = new FileInputStream(file);
		ZipEntry zipEntry = new ZipEntry(fileEntry);
		zos.putNextEntry(zipEntry);

		byte[] bytes = new byte[1024];
		int length;
		while ((length = fis.read(bytes)) >= 0) {
			zos.write(bytes, 0, length);
		}

		zos.closeEntry();
		fis.close();
		file.delete();
	}
}
