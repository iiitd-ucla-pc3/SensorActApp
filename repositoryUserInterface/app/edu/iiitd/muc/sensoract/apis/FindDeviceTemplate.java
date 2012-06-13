package edu.iiitd.muc.sensoract.apis;

import play.libs.WS.HttpResponse;
import edu.iiitd.muc.sensoract.constants.Const;
import edu.iiitd.muc.sensoract.utilities.SecretKey;
import edu.iiitd.muc.sensoract.utilities.SendHTTPRequest;

/**
 * Contains methods to delete a device
 * 
 * @author nipun Date:12/5/2012
 * 
 */
public class FindDeviceTemplate extends SensorActAPI {

	/**
	 * Deletes the device and renders the response from broker,else throws an
	 * exception
	 * 
	 * @param findDeviceRequest
	 */
	public final void doProcess(String findDeviceRequest) {
		String secretkey = new SecretKey().getSecretKeyFromHashMap(session
				.get(Const.USERNAME));
		String findDeviceRequestWithSecretKey = findDeviceRequest.replace(
				Const.FAKE_SECRET_KEY, secretkey);
		logger.info(Const.API_FINDDEVICETEMPLATE, secretkey + " "
				+ findDeviceRequestWithSecretKey);

		HttpResponse responseFromBroker = new SendHTTPRequest()
				.sendPostRequest(Const.URL_REPOSITORY_FIND_DEVICE_TEMPLATE,
						Const.MIME_TYPE_JSON, Const.API_FINDDEVICETEMPLATE,
						findDeviceRequestWithSecretKey);
		renderJSON(responseFromBroker.getString());
	}

}
