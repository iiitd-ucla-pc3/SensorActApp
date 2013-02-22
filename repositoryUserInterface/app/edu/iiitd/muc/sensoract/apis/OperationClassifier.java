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
import play.mvc.Http;
import edu.iiitd.muc.sensoract.constants.Const;
import edu.iiitd.muc.sensoract.exceptions.InvalidJsonException;
import edu.iiitd.muc.sensoract.format.APIResponse;
import edu.iiitd.muc.sensoract.format.ManageVPDSRequest;
import edu.iiitd.muc.sensoract.format.OperationClassifierFormat;
import edu.iiitd.muc.sensoract.utilities.SecretKey;
import edu.iiitd.muc.sensoract.utilities.SendHTTPRequest;

/**
 * Redirects to the appropriate page and puts relevant variables in the session
 * 
 * @author Manaswi Saha
 * 
 */
public class OperationClassifier extends SensorActAPI {

	/**
	 * 
	 * @param body
	 */

	public final void doProcess(String body) {
		
		logger.info(Const.API_OPCLASSIFIER, body);
		
		OperationClassifierFormat oprequest = null;
		try {
			oprequest = gson
					.fromJson(body, OperationClassifierFormat.class);
		} catch (Exception e) {
			renderJSON(gson.toJson(new APIResponse(Const.API_OPCLASSIFIER, 1, e
					.toString())));
		}
		
		flash.put(Const.VPDSNAME,oprequest.vpdsname);
		flash.put(Const.DEVICENAME,oprequest.devicename);
		
		if(oprequest.origin.equals("visualize")) {
			flash.put(Const.SENSORNAME,oprequest.sensorname);
			flash.put(Const.SENSORID,oprequest.sensorid);
			renderJSON("{\"url\": \"" + Http.Request.current().getBase() + "/userdisplay\"}");
		}
		else if (oprequest.origin.equals("actuate"))  {
			flash.put(Const.ACTUATORNAME,oprequest.actuatorname);
			flash.put(Const.ACTUATORID,oprequest.actuatorid);
			renderJSON("{\"url\": \"" + Http.Request.current().getBase() + "/useractuate\"}");
		}
		else{
			flash.put(Const.ACTUATORNAME,oprequest.actuatorname);
			flash.put(Const.ACTUATORID,oprequest.actuatorid);
			renderJSON("{\"url\": \"" + Http.Request.current().getBase() + "/userpresenceactuate\"}");
		}
		

		

	}
	

}