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
public class DeleteDevice extends SensorActAPI {

	/**
	 * Deletes the device and renders the response from broker,else throws an
	 * exception
	 * 
	 * @param deleteDeviceRequest
	 */
	public final void doProcess(String deleteDeviceRequest) {
		String secretkey = new SecretKey().getSecretKeyFromHashMap(session
				.get(Const.USERNAME));
		String deleteRequestWithSecretKey = deleteDeviceRequest.replace(
				Const.FAKE_SECRET_KEY, secretkey);
		logger.info(Const.API_DELETEDEVICE, secretkey + " "
				+ deleteRequestWithSecretKey);

		HttpResponse responseFromBroker = new SendHTTPRequest()
				.sendPostRequest(Const.URL_REPOSITORY_DELETE_DEVICE,
						Const.MIME_TYPE_JSON, Const.API_DELETEDEVICE,
						deleteRequestWithSecretKey);
		renderJSON(responseFromBroker.getString());
	}

}
