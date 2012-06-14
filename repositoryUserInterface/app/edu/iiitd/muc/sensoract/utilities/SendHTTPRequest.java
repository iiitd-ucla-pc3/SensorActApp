package edu.iiitd.muc.sensoract.utilities;

import play.libs.WS;
import play.libs.WS.HttpResponse;
import edu.iiitd.muc.sensoract.apis.SensorActAPI;
import edu.iiitd.muc.sensoract.constants.Const;
import edu.iiitd.muc.sensoract.format.APIResponse;

public class SendHTTPRequest extends SensorActAPI {

	public HttpResponse sendPostRequest(String url, String mimetype,
			String invokingApiname, String content) {
		HttpResponse response = null;
		try {
			logger.info(invokingApiname, content);

			response = WS.url(url).body(content).mimeType(mimetype).post();
		} catch (Exception e) {
			renderJSON(gson.toJson(new APIResponse(invokingApiname, 1,
					Const.ERROR_MESSAGE_CONNECTION_FAILURE)));

		}
		return response;

	}

}
