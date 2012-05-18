package edu.iiitd.muc.sensoract.apis;

import play.libs.WS;
import play.libs.WS.HttpResponse;
import edu.iiitd.muc.sensoract.constants.Const;
import edu.iiitd.muc.sensoract.exceptions.InvalidJsonException;
import edu.iiitd.muc.sensoract.format.APIResponse;
import edu.iiitd.muc.sensoract.format.LoginUserRequest;
import edu.iiitd.muc.sensoract.format.RegisterUserRequest;

/**
 * Used to register a user to the repository User provides the following details
 * 1.username 2.password 3.email id
 * 
 * @author nipun
 * 
 */
public class RegisterUser extends SensorActAPI {

	/**
	 * 
	 * @param body
	 */

	public final void doProcess(String body) {
		RegisterUserRequest registerUserRequest = null;
		try {
			registerUserRequest = gson
					.fromJson(body, RegisterUserRequest.class);
		} catch (Exception e) {
			renderJSON(gson.toJson(new APIResponse(Const.API_ADDUSER, 1, e
					.toString())));

		}

		HttpResponse responseFromBroker = sendRequestToBroker(body);
		APIResponse responseStatus = getStatusFromResponse(responseFromBroker);
		if (responseStatus.statuscode == Const.SUCCESS)

		{
			try {

				login.doProcess((gson.toJson(new LoginUserRequest(
						registerUserRequest.username,
						registerUserRequest.password))));
			} catch (InvalidJsonException e) {
				renderJSON(gson.toJson(new APIResponse(Const.API_ADDUSER, 1, e
						.toString())));

			}

		}

	}

	/**
	 * 
	 * @param responseFromBroker
	 * @return APIResponse
	 */

	private APIResponse getStatusFromResponse(HttpResponse responseFromBroker) {
		APIResponse apiResponse = null;
		try {
			apiResponse = api.gson.fromJson(responseFromBroker.getString(),
					APIResponse.class);
		} catch (Exception e) {
			renderJSON(gson.toJson(new APIResponse(Const.API_ADDUSER, 1, e
					.toString())));

		}
		return apiResponse;
	}

	private HttpResponse sendRequestToBroker(String bodyToSendToBroker) {
		HttpResponse response = null;
		try {
			response = WS.url(Const.URL_BROKER_REGISTER_USER)
					.body(bodyToSendToBroker).mimeType("application/json")
					.post();
		} catch (Exception e) {
			renderJSON(gson.toJson(new APIResponse(Const.API_ADDUSER, 1, e
					.toString())));
		}
		return response;
	}

}
