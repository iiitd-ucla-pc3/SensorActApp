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
			logger.info(Const.API_LOGIN, loginUserRequest.username);

		} catch (Exception e) {
			renderJSON(gson.toJson(new APIResponse(Const.API_LOGIN, 1, e
					.toString())));
		}
		HttpResponse responseFromRepository = sendRequestToBroker(loginBody);
		try {
			APIResponse apiResponse = gson.fromJson(
					responseFromRepository.getString(), APIResponse.class);
			if (apiResponse.statuscode == Const.SUCCESS) {
				logger.info(Const.LOGGER_INFO_LOGIN_SUCCESSFULL
						+ loginUserRequest.username);
				session.put(Const.USERNAME, loginUserRequest.username);
				usernameToSecretKeyMap.put(loginUserRequest.username,
						apiResponse.message.toString());
				renderJSON(responseFromRepository.getString());

			} else {

				logger.info(Const.LOGGER_INFO_LOGIN_FAILURE
						+ loginUserRequest.username);
				renderJSON(responseFromRepository.getString());
			}
		} catch (Exception e) {
			logger.error(Const.API_LOGIN, Const.LOGGER_ERROR_JSON_PARSE
					+ responseFromRepository.getString());
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
			logger.error(Const.LOGGER_ERROR_CONNECTION_FAILURE + body);
			renderJSON(gson.toJson(new APIResponse(Const.API_LOGIN,
					Const.ERROR_CONNECTION_FAILURE,
					Const.ERROR_MESSAGE_CONNECTION_FAILURE)));

		}
		return response;
	}

}
