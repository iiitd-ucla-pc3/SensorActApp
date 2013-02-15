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
import edu.iiitd.muc.sensoract.utilities.SecretKey;
import edu.iiitd.muc.sensoract.utilities.SendHTTPRequest;

public class ListAssocGuardRule extends SensorActAPI {
	
	/**
	 * Services the /listassocguardrule API.
	 * <p>
	 * Followings are the steps to be followed to add a new device profile
	 * successfully to the repository.
	 * <ol>
	 * <li>Gets the JSON string containing guard rule from UI
	 * <li>Since the validation had been performed at the UI,this request is
	 * just tunneled to the repository
	 * <li>Replaces the secret key with the actual secret key
	 * <li>It gets the list of all the guard rules associated with the user
	 * </ol>
	 * <p>
	 * 
	 * @param listGuardRuleJson
	 *            list guard rule request in Json
	 */
	public final void doProcess() {
		String secretkey = Global.VPDS_OWNER_KEY;

		String listAssocGuardRuleReq = "{\"secretkey\":" + secretkey + "}";

		logger.info(Const.API_LISTASSOCGUARDRULE, secretkey + " " + listAssocGuardRuleReq);

		HttpResponse responseFromVPDS = new SendHTTPRequest()
				.sendPostRequest(Global.URL_REPOSITORY_ASSOC_GUARD_RULE_LIST,
						Const.MIME_TYPE_JSON, Const.API_LISTGUARDRULE,
						listAssocGuardRuleReq);
		renderJSON(responseFromVPDS.getString());
	}
}
