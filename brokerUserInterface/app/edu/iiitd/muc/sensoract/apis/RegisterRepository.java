package edu.iiitd.muc.sensoract.apis;

import play.libs.WS;
import play.libs.WS.HttpResponse;
import edu.iiitd.muc.sensoract.constants.Const;
import edu.iiitd.muc.sensoract.format.APIResponse;

/**
 * Used to register a user to the repository User provides the following details
 * 1.username 2.password 3.email id
 * 
 * @author nipun
 * 
 */
public class RegisterRepository extends SensorActAPI {

	/**
	 * 
	 * @param body
	 */

	public final void doProcess(String body) {
		String secretkey = null;
		try {
			secretkey = usernameToSecretKeyMap.get(session.get(Const.USERNAME));
		} catch (Exception e) {
			renderJSON(gson.toJson(new APIResponse(Const.API_LISTALLDEVICES, 1,
					"Session Expired.Login Again")));

		}
		String bodyWithSecretKey = body.replace(Const.FAKE_SECRET_KEY,
				secretkey);

		HttpResponse responseFromBroker = sendRequestToBroker(bodyWithSecretKey);
		renderJSON(responseFromBroker.getString());

	}

	private HttpResponse sendRequestToBroker(String bodyToSendToBroker) {
		HttpResponse response = null;
		try {
			response = WS.url(Const.URL_BROKER_REGISTER_REPOSITORY)
					.body(bodyToSendToBroker).mimeType("application/json")
					.post();
		} catch (Exception e) {
			renderJSON(gson.toJson(new APIResponse(Const.API_ADDUSER, 1, e
					.toString())));
		}
		return response;
	}

}
