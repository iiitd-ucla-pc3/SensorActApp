package edu.iiitd.muc.sensoract.apis;

import play.libs.WS.HttpResponse;
import edu.iiitd.muc.sensoract.constants.Const;
import edu.iiitd.muc.sensoract.utilities.SecretKey;
import edu.iiitd.muc.sensoract.utilities.SendHTTPRequest;

public class QueryData extends SensorActAPI {

	public final void doProcess(String queryBody) {
		System.out.println(" Here");
		String secretkey = new SecretKey().getSecretKeyFromHashMap(session
				.get(Const.USERNAME));
		String queryBodyWithSecretKey = queryBody.replace(
				Const.FAKE_SECRET_KEY, secretkey);
		HttpResponse responseFromBroker = new SendHTTPRequest()
				.sendPostRequest(Const.URL_REPOSITORY_QUERY_DATA,
						Const.MIME_TYPE_JSON, Const.API_QUERYDATA,
						queryBodyWithSecretKey);

		renderJSON(responseFromBroker.getString());

	}
}