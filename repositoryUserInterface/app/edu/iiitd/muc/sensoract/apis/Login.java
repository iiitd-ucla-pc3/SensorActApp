/*******************************************************************************
 * /*
 * * Copyright (c) 2012, Indraprastha Institute of Information Technology,
 * * Delhi (IIIT-D) and The Regents of the University of California.
 * * All rights reserved.
 * *
 * * Redistribution and use in source and binary forms, with or without
 * * modification, are permitted provided that the following conditions
 * * are met:
 * * 1. Redistributions of source code must retain the above copyright
 * * notice, this list of conditions and the following disclaimer.
 * * 2. Redistributions in binary form must reproduce the above
 * * copyright notice, this list of conditions and the following
 * * disclaimer in the documentation and/or other materials provided
 * * with the distribution.
 * * 3. Neither the names of the Indraprastha Institute of Information
 * * Technology, Delhi and the University of California nor the names
 * * of their contributors may be used to endorse or promote products
 * * derived from this software without specific prior written permission.
 * *
 * * THIS SOFTWARE IS PROVIDED BY THE IIIT-D, THE REGENTS, AND CONTRIBUTORS
 * * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE IIITD-D, THE REGENTS
 * * OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * * SUCH DAMAGE.
 * *
 * *
 ******************************************************************************/
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
