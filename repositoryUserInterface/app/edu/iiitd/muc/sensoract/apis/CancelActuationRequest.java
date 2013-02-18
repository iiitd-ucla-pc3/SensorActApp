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
 * Name: ListActuationRequest.java
 * Project: SensorAct, MUC@IIIT-Delhi 
 * Version: 1.0
 * Date: 2012-12-23
 * Author: Manaswi Saha
 */

package edu.iiitd.muc.sensoract.apis;

/*
 * Standard play imports
 */
import play.libs.WS.HttpResponse;
import edu.iiitd.muc.sensoract.constants.Const;
import edu.iiitd.muc.sensoract.format.CancelActuateRequestFormat;
import edu.iiitd.muc.sensoract.format.ActuateVPDSRequest;
import edu.iiitd.muc.sensoract.format.CancelActuationVPDSRequest;
import edu.iiitd.muc.sensoract.format.GetAccessKeyResponseFormat;
import edu.iiitd.muc.sensoract.utilities.SecretKey;
import edu.iiitd.muc.sensoract.utilities.SendHTTPRequest;

public class CancelActuationRequest extends SensorActAPI {
	
	/**
	 * Services the listactuationrequests API.
	 * <p>
	 * Followings are the steps to be followed to add a new device profile
	 * successfully to the repository.
	 * <ol>
	 * <li>Gets the JSON string containing list actuation request from UI
	 * <li>Since the validation had been performed at the UI,this request is
	 * just tunneled to the repository
	 * <li>Replaces the secret key with the actual secret key
	 * <li>If the list request is successful,the successful Response
	 * format is sent to the UI which interprets the same and reloads the page
	 * <li>If the list request fails then corresponding error
	 * message is sent to the UI
	 * </ol>
	 * <p>
	 * 
	 * @param listActnRequest
	 *            actuation request in Json
	 */
	public final void doProcess(String cancelActReqList) {
		
		String usertype = session.get(Const.USERTYPE);
		String secretkey = null;
		String vpdsURL = null;
		HttpResponse responseFromBroker = null;	
		
		CancelActuateRequestFormat actRequest = gson
				.fromJson(cancelActReqList, CancelActuateRequestFormat.class);
		
		if(usertype.equals(Const.USER)){
			
			/* Get accesskey from Broker */
			
			// From Json - vpdsname and secretkey
			String usersecretkey = new SecretKey().getSecretKeyFromHashMap(session
					.get(Const.USERNAME));
			String jsonGetAccessKey = "{\"secretkey\" : \"" + usersecretkey + "\",\"vpdsname\": \""+ actRequest.vpdsname + "\"}";
			logger.info(Const.API_CANCELACTUATIONREQUEST, "For "+ usertype + " " +jsonGetAccessKey);
			
			// Make request
			responseFromBroker = new SendHTTPRequest()
			.sendPostRequest(Const.URL_BROKER_GET_ACCESS_KEY,
					Const.MIME_TYPE_JSON, Const.API_ACTUATEDEVICE,
					jsonGetAccessKey);
			System.out.println("Get access key "+responseFromBroker.getString());
			
			GetAccessKeyResponseFormat response = gson.fromJson(
					responseFromBroker.getString(),GetAccessKeyResponseFormat.class);
			
			//Set secretkey as accesskey
			actRequest.secretkey = response.accesskey;
			vpdsURL = response.vpdsurl;
		}
		else if(usertype.equals(Const.OWNER)){
			//Set secretkey as owner key
			actRequest.secretkey = Global.VPDS_OWNER_KEY;			
		}
		
		CancelActuationVPDSRequest toSend = new CancelActuationVPDSRequest(
				actRequest.secretkey, actRequest.cancelRequestList);
		
		String jsonToSend = gson.toJson(toSend);

		logger.info(Const.API_CANCELACTUATIONREQUEST, secretkey + " " + jsonToSend);
		
		if(usertype.equals(Const.USER)){
			responseFromBroker = new SendHTTPRequest()
			.sendPostRequest(vpdsURL + "device/actuationrequest/cancel",
					Const.MIME_TYPE_JSON, Const.API_CANCELACTUATIONREQUEST,
					jsonToSend);
		}
		else if(usertype.equals(Const.OWNER)){
			responseFromBroker = new SendHTTPRequest()
			.sendPostRequest(Global.URL_REPOSITORY_CANCEL_ACTUATION_REQUEST,
					Const.MIME_TYPE_JSON, Const.API_CANCELACTUATIONREQUEST,
					jsonToSend);
		}		

		renderJSON(responseFromBroker.getString());
	}
}
