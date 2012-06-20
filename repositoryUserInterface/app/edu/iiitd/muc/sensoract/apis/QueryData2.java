package edu.iiitd.muc.sensoract.apis;

import play.libs.WS.HttpResponse;
import edu.iiitd.muc.sensoract.constants.Const;
import edu.iiitd.muc.sensoract.format.APIResponse;
import edu.iiitd.muc.sensoract.format.ChartSeries;
import edu.iiitd.muc.sensoract.format.ChartSeriesArray;
import edu.iiitd.muc.sensoract.format.WaveSegmentArray;
import edu.iiitd.muc.sensoract.utilities.SecretKey;
import edu.iiitd.muc.sensoract.utilities.SendHTTPRequest;

/*
 * Server side processing for query data
 */
public class QueryData2 extends SensorActAPI {

	public final void doProcess(String queryBody) {
		System.out.println(" I am here in this file");
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
								+ " " + wa.wavesegmentArray.get(0).data.sname));

			}

			for (int i = 0; i < numberOfWavesegs; i++) {
				long timestamp = wa.wavesegmentArray.get(i).data.timestamp;
				int samplingPeriod = 1;

				for (int j = 0; j < numberOfSeries; j++) {

					int numberOfReadings = wa.wavesegmentArray.get(i).data.channels
							.get(j).readings.size();
					for (int k = 0; k < numberOfReadings; k++) {
						Double[] d = new Double[2];
						d[0] = (double) (timestamp + k * samplingPeriod * 1000);
						d[1] = wa.wavesegmentArray.get(i).data.channels.get(j).readings
								.get(k);
						ca.chartSeries.get(j).data.add(d);
					}
				}
			}

			renderJSON(gson.toJson(ca));
		} catch (Exception e) {
			renderJSON(gson.toJson(new APIResponse(Const.API_QUERYDATA, 1, e
					.toString())));
		}

	}
}