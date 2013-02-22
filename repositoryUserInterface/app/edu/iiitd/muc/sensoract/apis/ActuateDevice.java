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
/*
 * Name: ActuateDevice.java
 * Project: SensorAct, MUC@IIIT-Delhi 
 * Version: 1.0
 * Date: 2012-12-14
 * Author: Manaswi Saha
 */

package edu.iiitd.muc.sensoract.apis;

/*
 * Standard play imports
 */
import java.util.Map;

import play.libs.WS.HttpResponse;
import edu.iiitd.muc.sensoract.constants.Const;
import edu.iiitd.muc.sensoract.format.ActuateRequest;
import edu.iiitd.muc.sensoract.format.ActuateVPDSRequest;
import edu.iiitd.muc.sensoract.format.DeviceActuateFormat;
import edu.iiitd.muc.sensoract.format.GetAccessKeyResponseFormat;
import edu.iiitd.muc.sensoract.format.QueryRequest;
import edu.iiitd.muc.sensoract.utilities.SecretKey;
import edu.iiitd.muc.sensoract.utilities.SendHTTPRequest;

public class ActuateDevice extends SensorActAPI {
	
	/**
	 * Services the actuatedevice API.
	 * <p>
	 * Followings are the steps to be followed to add a new device profile
	 * successfully to the repository.
	 * <ol>
	 * <li>Gets the JSON string containing tasklet from UI
	 * <li>Since the validation had been performed at the UI,this request is
	 * just tunneled to the repository
	 * <li>Replaces the secret key with the actual secret key
	 * <li>If the tasklet has been successful,the successful Response
	 * format is sent to the UI which interprets the same and reloads the page
	 * <li>If the tasklet schedule fails then corresponding error
	 * message is sent to the UI
	 * </ol>
	 * <p>
	 * 
	 * @param deviceActuateBody
	 *            Device profile in Json
	 */
	public final void doProcess(String deviceActuateBody) {
		
		String usertype = session.get(Const.USERTYPE);
		String secretkey = null;
		String vpdsURL = null;
		HttpResponse responseFromBroker = null;	
		
		ActuateRequest actRequest = gson
				.fromJson(deviceActuateBody, ActuateRequest.class);
		
		if(usertype.equals(Const.USER)){
			
			/* Get accesskey from Broker */
			
			// From Json - vpdsname and secretkey
			String usersecretkey = new SecretKey().getSecretKeyFromHashMap(session
					.get(Const.USERNAME));
			String jsonGetAccessKey = "{\"secretkey\" : \"" + usersecretkey + "\",\"vpdsname\": \""+ actRequest.vpdsname + "\"}";
			logger.info(Const.API_ACTUATEDEVICE, "For "+ usertype + " " +jsonGetAccessKey);
			
			// Make request
			responseFromBroker = new SendHTTPRequest()
			.sendPostRequest(Const.URL_BROKER_GET_ACCESS_KEY,
					Const.MIME_TYPE_JSON, Const.API_ACTUATEDEVICE,
					jsonGetAccessKey);			
			
			System.out.println("Get access key "+responseFromBroker.getString());
			
			GetAccessKeyResponseFormat response = gson.fromJson(
					responseFromBroker.getString(),GetAccessKeyResponseFormat.class);
			
			//Set secretkey as accesskey
			secretkey = response.accesskey;
			vpdsURL = response.vpdsurl;
		}
		else if(usertype.equals(Const.OWNER)){
			//Set secretkey as owner key
			secretkey = Global.VPDS_OWNER_KEY;			
		}
		
		ActuateVPDSRequest toSend = new ActuateVPDSRequest(
				secretkey, actRequest.taskletname, actRequest.desc, actRequest.param,
				actRequest.input, actRequest.when, actRequest.execute);
		
		String jsonToSend = gson.toJson(toSend);

		logger.info(Const.API_ACTUATEDEVICE, secretkey + " " + jsonToSend);
		
		if(usertype.equals(Const.USER)){
			responseFromBroker = new SendHTTPRequest()
			.sendPostRequest(vpdsURL + "device/actuate",
					Const.MIME_TYPE_JSON, Const.API_ACTUATEDEVICE,
					jsonToSend);
		}
		else {
			responseFromBroker = new SendHTTPRequest()
			.sendPostRequest(Global.URL_REPOSITORY_ACTUATE_DEVICE,
					Const.MIME_TYPE_JSON, Const.API_ACTUATEDEVICE,
					jsonToSend);
		}
		
		renderJSON(responseFromBroker.getString());
	}
}
