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
/**
 * 
 */
package edu.iiitd.muc.sensoract.constants;

/**
 * Defines all constants for SensorAct
 * 
 * 
 * @author Nipun
 * 
 */

public class Const {

	/*
	 * API names
	 */
	public static final String API_LOGIN = "login";
	public static final String API_ADDUSER = "adduser";
	public static final String API_PUBLISHDATA = "pubishdata";
	public static final String API_ADDDEVICE = "adddevice";
	public static final String API_DELETEDEVICE = "deletedevice";
	public static final String API_GETDEVICE = "getdevice";
	public static final String API_LISTALLDEVICES = "listalldevices";
	public static final String API_QUERYDATA = "querydata";
	public static final String API_ADDDEVICETEMPLATE = "adddevicetemplate";
	public static final String API_DELETEDEVICETEMPLATE = "deletedevicetemplate";
	public static final String API_FINDDEVICE = "finddevice";
	public static final String API_FINDDEVICETEMPLATE = "finddevicetemplate";
	public static final String API_GENERATESECRETKEY = "generatesecretkey";
	public static final String API_LISTALLDEVICETEMPLATES = "listalldevicetemplates";

	/*
	 * API parameters
	 */
	public static final String PARAM_USERNAME = "username";
	public static final String PARAM_PASSWORD = "password";
	public static final String PARAM_EMAIL = "email";
	public static final String PARAM_SECRETKEY = "secretkey";

	public static final String PARAM_APIKEY = "apikey";
	public static final String PARAM_DATA = "data";
	public static final String PARAM_LOCATION = "location";
	public static final String PARAM_CHANNEL = "channel";
	public static final String PARAM_EPOCTIME = "epoctime";
	public static final String PARAM_READINGS = "readings";

	public static final String PARAM_DEVICENAME = "devicename";

	/*
	 * API parameter validation messages
	 */
	public static final String MSG_REQUIRED = " is requird";
	public static final String MSG_LENGTH = " length must be ";
	public static final String MSG_MIN_LENGTH = " minimum length is ";
	public static final String MSG_MAX_LENGTH = " maximum length is ";
	public static final String MSG_INVALID = " is invalid";

	/*
	 * API parameter validation limits
	 */
	public static final int USERNAME_MIN_LENGTH = 8;
	public static final int USERNAME_MAX_LENGTH = 20;

	public static final int PASSWORD_MIN_LENGTH = 8;
	public static final int PASSWORD_MAX_LENGTH = 20;

	public static final int EMAIL_MIN_LENGTH = 8;
	public static final int EMAIL_MAX_LENGTH = 40;

	public static final int SECRETKEY_LENGTH = 20;

	public static final int DEVICENAME_MIN_LENGTH = 2;
	public static final int DEVICENAME_MAX_LENGTH = 20;

	/*
	 * API status messages
	 */
	public static final int SUCCESS = 0;
	public static final int FAILED = -1;

	/*
	 * API Error messages
	 */
	public static final String JSON_INVALIDOBJECT = "Invalid JSON object";
	public static final String JSON_EMPTYOBJECT = "Empty JSON object";

	public static final String ADDUSER_CREATED = "New Userprofile created";
	public static final String ADDUSER_ALREADYEXISTS = "Userprofile already exists";

	public static final String ADDDEVICE_CREATED = "New device created";
	public static final String ADDDEVICE_ALREADYEXISTS = "Device already exists";

	public static final String GETDEVICE_NOTFOUND = "Device not found";

	public static final String DELETEDEVICE_NOTFOUND = "Device not found";
	public static final String DELETEDEVICE_DELETED = "Device successfully deleted";

	public static final String LISTALLDEVICES_NOTFOUND = "No device found";

	public static final String UNREGISTERED_SECRETKEY = "Unregistered secretkey";

	/*
	 * URL's
	 */
	public static final String URL_UI_SERVER = "http://localhost:9003/";
	public static final String URL_BROKER_SERVER = "http://10.0.0.5:9001/";
	public static final String URL_REPOSITORY_SERVER = "http://localhost:9000/";
	public static final String URL_REPOSITORY_REGISTER_USER = URL_REPOSITORY_SERVER
			+ "user/register";
	public static final String URL_REPOSITORY_LOGIN_USER = URL_REPOSITORY_SERVER
			+ "user/login";
	public static final String URL_REPOSITORY_ADD_DEVICE = URL_REPOSITORY_SERVER
			+ "device/add";
	public static final String URL_REPOSITORY_ADD_DEVICE_TEMPLATE = URL_REPOSITORY_SERVER
			+ "device/template/add";

	public static final String URL_REPOSITORY_FIND_DEVICE = URL_REPOSITORY_SERVER
			+ "device/get";
	public static final String URL_REPOSITORY_FIND_DEVICE_TEMPLATE = URL_REPOSITORY_SERVER
			+ "device/template/get";

	public static final String URL_REPOSITORY_DELETE_DEVICE = URL_REPOSITORY_SERVER
			+ "device/delete";
	public static final String URL_REPOSITORY_DELETE_DEVICE_TEMPLATE = URL_REPOSITORY_SERVER
			+ "device/template/delete";

	public static final String URL_REPOSITORY_LIST_ALL_DEVICES = URL_REPOSITORY_SERVER
			+ "device/list";
	public static final String URL_REPOSITORY_LIST_ALL_DEVICE_TEMPLATES = URL_REPOSITORY_SERVER
			+ "device/template/list";

	public static final String URL_REPOSITORY_QUERY_DATA = URL_REPOSITORY_SERVER
			+ "data/query";
	public static final String URL_REPOSITORY_GET_INFO = URL_REPOSITORY_SERVER
			+ "repo/info";
	public static final String URL_REPOSITORY_GENERATE_SECRET_KEY = URL_REPOSITORY_SERVER
			+ "user/generate/repokey";

	public static final String URL_BROKER_ADD_USER = URL_BROKER_SERVER
			+ "registeruser";
	// public static final String URL_REPSOSITORY_ADD_DEVICE =
	// URL_BROKER_SERVER+"adddevice";
	public static final String URL_BROKER_DELETE_DEVICE = URL_BROKER_SERVER
			+ "deletedevice";
	public static final String URL_BROKER_QUERY_DATA = URL_REPOSITORY_SERVER
			+ "querydata";
	public static final String URL_BROKER_LOGIN = URL_BROKER_SERVER + "login";
	public static final String URL_HOME = URL_UI_SERVER + "home";
	public static final String URL_LOGIN = URL_UI_SERVER + "login";

	public static final String URL_BROKER_LIST_ALL_DEVICES = URL_BROKER_SERVER
			+ "listalldevices";

	/*
	 * Parameters
	 */

	public static final String USERNAME = "username";
	public static final String SECRETKEY = "secretkey";
	public static final String REQUEST_BODY = "body";
	public static final String FAKE_SECRET_KEY = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

	/**
	 * Defines the constants for SensorAct error messages
	 * 
	 * @author samy
	 * 
	 */
	public static final String ERROR_MESSAGE_CONNECTION_FAILURE = "Connection Failed.Try again later";
	public static final int ERROR_CONNECTION_FAILURE = 2;

	/*
	 * Logger info constants
	 */
	public static final String LOGGER_INFO_LOGIN_SUCCESSFULL = "Login Successful: ";
	public static final String LOGGER_INFO_LOGIN_FAILURE = "Login Failure: ";
	public static final String LOGGER_INFO_SESSION_EXPIRED = "Session Expired: ";

	/*
	 * Logger Error constants
	 */
	public static final String LOGGER_ERROR_JSON_PARSE = "JSON Parse error: ";
	public static final String LOGGER_ERROR_CONNECTION_FAILURE = "Connection Failure: ";

	/*
	 * 
	 * Mime types
	 */
	public static final String MIME_TYPE_JSON = "application/json";

	/*
	 * Sphinx and Static Image URL's
	 */
	public static final String BASE_WAVEFILE_URL = "../../../../../app/edu/cmu/sphinx/demo/transcriber/";
	public static final String BASE_OUTPUTWAVEFILE_URL = "../../../../../../public/javascripts";
	public static final String BASE_IMAGE_URL = "public/";

	public class Error {

		public static final String E404 = "Not Found!";
	}

	/**
	 * Defines the constants for SensorAct warning messages
	 * 
	 * @author samy
	 * 
	 */
	public class Warnings {

		public static final String WARNING1 = "warning1";
	}

}
