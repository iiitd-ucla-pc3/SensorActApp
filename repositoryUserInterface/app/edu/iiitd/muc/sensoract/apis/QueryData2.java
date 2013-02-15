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

import play.libs.WS.HttpResponse;
import edu.iiitd.muc.sensoract.constants.Const;
import edu.iiitd.muc.sensoract.format.APIResponse;
import edu.iiitd.muc.sensoract.format.ChartSeries;
import edu.iiitd.muc.sensoract.format.ChartSeriesArray;
import edu.iiitd.muc.sensoract.format.ChartSeriesStats;
import edu.iiitd.muc.sensoract.format.WaveSegmentArray;
import edu.iiitd.muc.sensoract.utilities.SecretKey;
import edu.iiitd.muc.sensoract.utilities.SendHTTPRequest;

public class QueryData2 extends SensorActAPI {

	public final void doProcess(String queryBody) {

		String secretkey = Global.VPDS_OWNER_KEY;
		String queryBodyWithSecretKey = queryBody.replace(
				Const.FAKE_SECRET_KEY, secretkey);
		HttpResponse responseFromBroker = new SendHTTPRequest()
				.sendPostRequest(Global.URL_REPOSITORY_QUERY_DATA,
						Const.MIME_TYPE_JSON, Const.API_QUERYDATA,
						queryBodyWithSecretKey);
		try {
			WaveSegmentArray wa = gson.fromJson(responseFromBroker.getString(),
					WaveSegmentArray.class);
			int numberOfWavesegs = wa.wavesegmentArray.size();
			int numberOfSeries = wa.wavesegmentArray.get(0).data.channels
					.size();

			ChartSeriesArray ca = new ChartSeriesArray();

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
						ca.chartSeries.get(j).data.add(d);
						// Min Value
						if (min > d[1])
							min = d[1];

						// Max Value
						if (max < d[1])
							max = d[1];

						// Avg Value
						avg += d[1];
					}

					ca.chartSeriesStats.get(j).min = min;
					ca.chartSeriesStats.get(j).max = max;
					ca.chartSeriesStats.get(j).avg = avg / numberOfReadings;
				}
			}

			System.out.println(gson.toJson(ca));
			renderJSON(gson.toJson(ca));
		} catch (Exception e) {
			renderJSON(gson.toJson(new APIResponse(Const.API_QUERYDATA, 1, e
					.toString())));
		}

	}
}
