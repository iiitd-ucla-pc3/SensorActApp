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

import play.libs.WS.HttpResponse;
import edu.iiitd.muc.sensoract.constants.Const;
import edu.iiitd.muc.sensoract.format.DeviceGetRequest;
import edu.iiitd.muc.sensoract.format.DeviceRequest;
import edu.iiitd.muc.sensoract.format.GetAccessKeyResponseFormat;
import edu.iiitd.muc.sensoract.format.QueryRequest;
import edu.iiitd.muc.sensoract.format.QueryToRepo;
import edu.iiitd.muc.sensoract.utilities.SecretKey;
import edu.iiitd.muc.sensoract.utilities.SendHTTPRequest;

/**
 * Description:This API is invoked by the client UI to get a device details
 * 
 * 
 * @author Manaswi Saha
 */
public class GetDevice extends SensorActAPI {

	public final void doProcess(String getDeviceBody) {

		DeviceRequest deviceRequest = gson
				.fromJson(getDeviceBody, DeviceRequest.class);
		
		String usertype = session.get(Const.USERTYPE);
		String secretkey = null;
		String vpdsURL = null;
		HttpResponse responseFromServer = null;
		
		if(usertype.equals(Const.USER)){
			
			/* Get accesskey from Broker */
			
			// From Json - vpdsname and secretkey
			String usersecretkey = new SecretKey().getSecretKeyFromHashMap(session
					.get(Const.USERNAME));
			String jsonGetAccessKey = "{\"secretkey\" : \"" + usersecretkey + "\",\"vpdsname\": \""+ deviceRequest.vpdsname + "\"}";
			logger.info(Const.API_QUERYDATA, "For "+ usertype + " " +jsonGetAccessKey);
			
			// Make request
			responseFromServer = new SendHTTPRequest()
			.sendPostRequest(Const.URL_BROKER_GET_ACCESS_KEY,
					Const.MIME_TYPE_JSON, Const.API_QUERYDATA,
					jsonGetAccessKey);
			System.out.println("Get access key "+responseFromServer.getString());
			GetAccessKeyResponseFormat response = gson.fromJson(
					responseFromServer.getString(),GetAccessKeyResponseFormat.class);
			
			//Set secretkey as accesskey
			secretkey = response.accesskey;
			vpdsURL = response.vpdsurl;
		}
		else if(usertype.equals(Const.OWNER)){
			//Set secretkey as owner key
			secretkey = Global.VPDS_OWNER_KEY;			
		}
		DeviceGetRequest queryToRepo = new DeviceGetRequest(deviceRequest.devicename,secretkey);
		String queryBodyWithSecretKey = gson.toJson(queryToRepo);
		
		if(usertype.equals(Const.USER)){
			responseFromServer = new SendHTTPRequest()
			.sendPostRequest(vpdsURL + "device/get",
					Const.MIME_TYPE_JSON, Const.API_GETDEVICE,
					queryBodyWithSecretKey);
		}
		else {
			responseFromServer = new SendHTTPRequest()
			.sendPostRequest(Global.URL_REPOSITORY_GET_DEVICE,
					Const.MIME_TYPE_JSON, Const.API_GETDEVICE,
					queryBodyWithSecretKey);
		}

		logger.info(Const.API_GETDEVICE, secretkey + " " + queryBodyWithSecretKey);

		renderJSON(responseFromServer.getString());
	}
}
