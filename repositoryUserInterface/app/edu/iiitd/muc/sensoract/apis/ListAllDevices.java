package edu.iiitd.muc.sensoract.apis;

import play.libs.WS.HttpResponse;
import edu.iiitd.muc.sensoract.constants.Const;
import edu.iiitd.muc.sensoract.utilities.SecretKey;
import edu.iiitd.muc.sensoract.utilities.SendHTTPRequest;

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

		String secretkey = new SecretKey().getSecretKeyFromHashMap(session
				.get(Const.USERNAME));
		String reqStr = "{\"secretkey\":" + secretkey + "}";

		logger.info(Const.API_LISTALLDEVICES, secretkey + " " + reqStr);

		HttpResponse responseFromBroker = new SendHTTPRequest()
				.sendPostRequest(Const.URL_REPOSITORY_LIST_ALL_DEVICES,
						Const.MIME_TYPE_JSON, Const.API_LISTALLDEVICES, reqStr);
		renderJSON(responseFromBroker.getString());
	}
}
