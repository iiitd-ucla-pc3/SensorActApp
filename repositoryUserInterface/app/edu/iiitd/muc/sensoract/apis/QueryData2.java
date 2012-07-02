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

		String secretkey = new SecretKey().getSecretKeyFromHashMap(session
				.get(Const.USERNAME));
		String queryBodyWithSecretKey = queryBody.replace(
				Const.FAKE_SECRET_KEY, secretkey);
		HttpResponse responseFromBroker = new SendHTTPRequest()
				.sendPostRequest(Const.URL_REPOSITORY_QUERY_DATA,
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