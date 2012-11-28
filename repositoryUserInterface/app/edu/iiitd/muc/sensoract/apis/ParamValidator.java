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

import edu.iiitd.muc.sensoract.constants.Const;


public class ParamValidator extends SensorActAPI {

	public void validateUserName(String username) {

		validation.required(username).message( Const.PARAM_USERNAME + Const.MSG_REQUIRED );

		validation.minSize(username, Const.USERNAME_MIN_LENGTH).message(Const.PARAM_USERNAME + Const.MSG_MIN_LENGTH + Const.USERNAME_MIN_LENGTH );

		validation.maxSize(username, Const.USERNAME_MAX_LENGTH).message(Const.PARAM_USERNAME + Const.MSG_MAX_LENGTH + Const.USERNAME_MAX_LENGTH );		
	}

	public void validatePassword(String password) {

		validation.required(password).message( Const.PARAM_PASSWORD + Const.MSG_REQUIRED );
		
		validation.minSize(password, Const.PASSWORD_MIN_LENGTH)
				  .message(Const.PARAM_PASSWORD + Const.MSG_MIN_LENGTH + Const.PASSWORD_MIN_LENGTH );
		
		validation.maxSize(password, Const.PASSWORD_MAX_LENGTH)
		  		  .message(Const.PARAM_PASSWORD + Const.MSG_MAX_LENGTH + Const.PASSWORD_MAX_LENGTH );		
	}

	public void validateEmail(String email) {
		
		validation.required(email).message( Const.PARAM_EMAIL + Const.MSG_REQUIRED );		
		validation.email(email).message( Const.PARAM_EMAIL + Const.MSG_INVALID );
		
		validation.minSize(email, Const.EMAIL_MIN_LENGTH)
				  .message(Const.PARAM_EMAIL + Const.MSG_MIN_LENGTH + Const.EMAIL_MIN_LENGTH );
		
		validation.maxSize(email, Const.EMAIL_MAX_LENGTH)
		  		  .message(Const.PARAM_EMAIL + Const.MSG_MAX_LENGTH + Const.EMAIL_MAX_LENGTH );		
	}

	public void validateSecretKey(String secretkey) {
		
		validation.required(secretkey).message( Const.PARAM_SECRETKEY + Const.MSG_REQUIRED );	
						
		validation.minSize(secretkey, Const.SECRETKEY_LENGTH)
				  .message(Const.PARAM_SECRETKEY + Const.MSG_LENGTH + Const.SECRETKEY_LENGTH );
		
		validation.maxSize(secretkey, Const.SECRETKEY_LENGTH)
		  		  .message(Const.PARAM_SECRETKEY + Const.MSG_LENGTH + Const.SECRETKEY_LENGTH );		
	}

	
	public void validateDeviceName(String devicename) {
		
		validation.required(devicename).message( Const.PARAM_DEVICENAME + Const.MSG_REQUIRED );	
						
		validation.minSize(devicename, Const.DEVICENAME_MIN_LENGTH)
				  .message(Const.PARAM_DEVICENAME + Const.MSG_MIN_LENGTH + Const.DEVICENAME_MIN_LENGTH );
		
		validation.maxSize(devicename, Const.DEVICENAME_MAX_LENGTH)
		  		  .message(Const.PARAM_DEVICENAME + Const.MSG_MAX_LENGTH + Const.DEVICENAME_MAX_LENGTH );		
	}

}

