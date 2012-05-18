package edu.iiitd.muc.sensoract.apis;

import play.libs.WS;
import play.libs.WS.HttpResponse;
import edu.iiitd.muc.sensoract.constants.Const;
import edu.iiitd.muc.sensoract.exceptions.InvalidJsonException;
import edu.iiitd.muc.sensoract.format.APIResponse;
import edu.iiitd.muc.sensoract.format.LoginUserRequest;

/**
 * Contains methods to Login User sends in his username and password in json
 * format If successfull the secretkey is obtained and stored in the
 * usernameToSessionMap
 * 
 * @author nipun
 * 
 */
public class Login extends SensorActAPI {
	public final void doProcess(String loginBody) throws InvalidJsonException {

		LoginUserRequest loginUserRequest = null;
		try {
			loginUserRequest = gson.fromJson(loginBody, LoginUserRequest.class);

		} catch (Exception e) {
			renderJSON(gson.toJson(new APIResponse(Const.API_LOGIN, 1, e
					.toString())));
		}
		HttpResponse responseFromRepository = sendRequestToBroker(loginBody);
		System.out.println(responseFromRepository.getString());
		try {
			APIResponse apiResponse = gson.fromJson(
					responseFromRepository.getString(), APIResponse.class);
			if (apiResponse.statuscode == Const.SUCCESS) {
				session.put(Const.USERNAME, loginUserRequest.username);
				usernameToSecretKeyMap.put(loginUserRequest.username,
						apiResponse.message.toString());
				renderJSON(responseFromRepository.getString());

			} else {

				renderJSON(responseFromRepository.getString());
			}
		} catch (Exception e) {
			renderJSON(gson.toJson(new APIResponse(Const.API_LOGIN, 1, e
					.toString())));
		}

	}

	/**
	 * 
	 * @param body
	 *            containing username and password
	 * @return HTTP Response obtained from repository
	 */
	private HttpResponse sendRequestToBroker(String body) {
		HttpResponse response = null;
		try {
			response = WS.url(Const.URL_REPOSITORY_LOGIN_USER).body(body)
					.mimeType("application/json").post();
		} catch (Exception e) {
			renderJSON(gson.toJson(new APIResponse(Const.API_LOGIN, 1, e
					.toString())));

		}
		return response;
	}

}
