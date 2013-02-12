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
import edu.iiitd.muc.sensoract.format.RegisterUserRequest;

/**
 * Used to register a user to the repository User provides the following details
 * 1.username 2.password 3.email id
 * 
 * @author nipun
 * @author manaswi
 * 
 */
public class RegisterUser extends SensorActAPI {

	/**
	 * 
	 * @param body
	 */

	public final void doProcess(String body) {
		System.out.println(body+"Body");
		RegisterUserRequest registerUserRequest = null;
		try {
			registerUserRequest = gson
					.fromJson(body, RegisterUserRequest.class);
		} catch (Exception e) {
			renderJSON(gson.toJson(new APIResponse(Const.API_ADDUSER, 1, e
					.toString())));

		}

		HttpResponse responseFromRepository = sendRequestToBroker(body);
		APIResponse responseStatus = getStatusFromResponse(responseFromRepository);
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

		} else {
			renderJSON(responseFromRepository.getString());
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
