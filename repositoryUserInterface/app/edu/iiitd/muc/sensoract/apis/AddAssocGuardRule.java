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
 * Name: PresenceActuateDevice.java
 * Project: SensorAct, MUC@IIIT-Delhi 
 * Version: 1.0
 * Date: 2012-12-26
 * Author: Manaswi Saha
 */

package edu.iiitd.muc.sensoract.apis;

/*
 * Standard play imports
 */
import play.libs.WS.HttpResponse;
import edu.iiitd.muc.sensoract.constants.Const;
import edu.iiitd.muc.sensoract.format.AddAssocRequest;
import edu.iiitd.muc.sensoract.format.AssocGuardRuleFormat;
import edu.iiitd.muc.sensoract.utilities.SecretKey;
import edu.iiitd.muc.sensoract.utilities.SendHTTPRequest;

public class AddAssocGuardRule extends SensorActAPI {
	
	/**
	 * Services the /addassocguardrule API.
	 * <p>
	 * Followings are the steps to be followed to add a new device profile
	 * successfully to the repository.
	 * <ol>
	 * <li>Gets the JSON string containing guard rule from UI
	 * <li>Since the validation had been performed at the UI,this request is
	 * just tunneled to the repository
	 * <li>Replaces the secret key with the actual secret key
	 * <li>If the addition has been successful,the successful Response
	 * format is sent to the UI which interprets the same and reloads the page
	 * <li>If the addition fails then corresponding error
	 * message is sent to the UI
	 * </ol>
	 * <p>
	 * 
	 * @param addAssocGuardRuleJson
	 *            add association request in Json
	 */
	public final void doProcess(String addAssocGuardRuleJson) {
		String secretkey = Global.VPDS_OWNER_KEY;
		HttpResponse responseFromVPDS = null;
		logger.info(Const.API_ADDASSOCGUARDRULE, secretkey + " " + addAssocGuardRuleJson);
		
		AddAssocRequest addAssocRequest = gson
				.fromJson(addAssocGuardRuleJson, AddAssocRequest.class);

		int numberOfDevicesRequest = addAssocRequest.devicesArray.size();

		for (int i = 0; i < numberOfDevicesRequest; i++) {

			String devicename = addAssocRequest.devicesArray.get(i).devicename;
			
			if(addAssocRequest.devicesArray.get(i).sensorsArray.size() > 0) {
				int numberOfSensorsInDevice = addAssocRequest.devicesArray.get(i).sensorsArray
						.size();
				for (int j = 0; j < numberOfSensorsInDevice; j++) {
					AssocGuardRuleFormat toSend = new AssocGuardRuleFormat(
							secretkey,
							addAssocRequest.rulename,
							devicename,
							addAssocRequest.devicesArray.get(i).sensorsArray.get(j).sensorname,
							addAssocRequest.devicesArray.get(i).sensorsArray.get(j).sensorid,
							null,
							null);
					String addAssocBodyWithSecretKey = gson.toJson(toSend);
					responseFromVPDS = new SendHTTPRequest()
					.sendPostRequest(Global.URL_REPOSITORY_ASSOC_GUARD_RULE_ADD,
							Const.MIME_TYPE_JSON, Const.API_ADDASSOCGUARDRULE,
							addAssocBodyWithSecretKey);

				}
				
			}
			
			if(addAssocRequest.devicesArray.get(i).actuatorsArray.size() > 0) {
				int numberOfActuatorsInDevice = addAssocRequest.devicesArray.get(i).actuatorsArray
						.size();
				for (int j = 0; j < numberOfActuatorsInDevice; j++) {
					AssocGuardRuleFormat toSend = new AssocGuardRuleFormat(
							secretkey,
							addAssocRequest.rulename,
							devicename,
							null,
							null,
							addAssocRequest.devicesArray.get(i).actuatorsArray.get(j).actuatorname,
							addAssocRequest.devicesArray.get(i).actuatorsArray.get(j).actuatorid
							);
					String addAssocBodyWithSecretKey = gson.toJson(toSend);
					responseFromVPDS = new SendHTTPRequest()
					.sendPostRequest(Global.URL_REPOSITORY_ASSOC_GUARD_RULE_ADD,
							Const.MIME_TYPE_JSON, Const.API_ADDASSOCGUARDRULE,
							addAssocBodyWithSecretKey);

				}
				
			}

		}
		renderJSON(responseFromVPDS.getString());
		
	}
}
