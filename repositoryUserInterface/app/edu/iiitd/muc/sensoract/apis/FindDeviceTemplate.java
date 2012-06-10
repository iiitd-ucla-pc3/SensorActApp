package edu.iiitd.muc.sensoract.apis;

import play.libs.WS;
import play.libs.WS.HttpResponse;
import edu.iiitd.muc.sensoract.constants.Const;
import edu.iiitd.muc.sensoract.format.APIResponse;

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
		System.out.println(findDeviceRequest);
		HttpResponse responseFromRepository = sendRequestToRepository(findDeviceRequest);
		renderJSON(responseFromRepository.getString());
	}

	/**
	 * Makes the delete request to the repository
	 * 
	 * @param findDeviceRequest
	 * @return HttpResponse
	 */
	private HttpResponse sendRequestToRepository(String findDeviceRequest) {

		HttpResponse response = null;
		String secretkey = null;
		try {
			secretkey = usernameToSecretKeyMap.get(session.get(Const.USERNAME));
		} catch (Exception e) {
			renderJSON(gson.toJson(new APIResponse(Const.API_DELETEDEVICE, 1,
					"Session Expired.Login Again")));

		}
		String findDeviceRequestWithSecretKey = findDeviceRequest.replace(
				Const.FAKE_SECRET_KEY, secretkey);
		try {
			response = WS.url(Const.URL_REPOSITORY_FIND_DEVICE_TEMPLATE)
					.body(findDeviceRequestWithSecretKey)
					.mimeType("application/json").post();
		} catch (Exception e) {
			renderJSON(gson.toJson(new APIResponse(Const.API_DELETEDEVICE, 1, e
					.toString())));

		}
		return response;
	}

}