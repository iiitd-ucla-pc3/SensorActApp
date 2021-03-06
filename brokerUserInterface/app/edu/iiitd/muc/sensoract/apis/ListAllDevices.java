package edu.iiitd.muc.sensoract.apis;

import play.libs.WS;
import play.libs.WS.HttpResponse;
import edu.iiitd.muc.sensoract.constants.Const;
import edu.iiitd.muc.sensoract.format.APIResponse;

/**
 * Description:This API is invoked by the client UI to get a list of all the
 * devices
 * 
 * I/P:Secret Key O/P:Makes a get request to the broker with the secretkey
 * 
 * @author nipun
 */
public class ListAllDevices extends SensorActAPI {

	public final void doProcess() {

		HttpResponse responseFromBroker = sendRequestToBroker();
		renderJSON(responseFromBroker.getString());
	}

	/**
	 * Makes a request to the repository to get a list of devices
	 * 
	 * @return HttpResponse
	 */
	private HttpResponse sendRequestToBroker() {
		HttpResponse response = null;
		String secretkey = null;
		try {
			secretkey = usernameToSecretKeyMap.get(session.get(Const.USERNAME));
		} catch (Exception e) {
			renderJSON(gson.toJson(new APIResponse(Const.API_LISTALLDEVICES, 1,
					"Session Expired.Login Again")));

		}
		String reqStr = "{\"secretkey\":" + secretkey + "}";

		try {
			response = WS.url(Const.URL_BROKER_LIST_ALL_DEVICES)
					.body(reqStr).mimeType("application/json").post();
		} catch (Exception e) {
			renderJSON(gson.toJson(new APIResponse(Const.API_LISTALLDEVICES, 1,
					e.toString())));
		}

		return response;

	}
}
