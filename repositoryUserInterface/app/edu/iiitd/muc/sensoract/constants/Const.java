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



import controllers.Application;
import edu.iiitd.muc.sensoract.apis.Global;
import play.Play;

/**
 * Defines all constants for SensorAct
 * 
 * 
 * @author Nipun, Manaswi
 * 
 */

public class Const {

	/*
	 * API names
	 */
	public static final String API_LOGIN = "login";
	public static final String API_RELOGIN = "relogin";
	public static final String API_OPCLASSIFIER = "opclassifier";
	public static final String API_REGISTER_VPDS = "registerVPDSToBroker";
	public static final String API_LIST_VPDS = "listvpds";
	public static final String API_USER_LIST = "getuserlist";
	public static final String API_MANAGE_VPDS = "manageVPDS";
	public static final String API_ADDUSER = "adduser";
	public static final String API_LISTALLUSERS = "listallusers";
	public static final String API_PUBLISHDATA = "publishdata";
	public static final String API_ADDDEVICE = "adddevice";
	public static final String API_DELETEDEVICE = "deletedevice";
	public static final String API_SHARE_DEVICE = "sharedevice";
	public static final String API_VIEW_SHARED_DEVICES = "viewsharedevices";
	public static final String API_LIST_SHARED_DEVICES_OWNER = "listsharedevices";
	public static final String API_GETDEVICE = "getdevice";
	public static final String API_LISTALLDEVICES = "listalldevices";
	public static final String API_LISTALLVPDSDEVICES = "listallvpdsdevices";
	public static final String API_QUERYDATA = "querydata";
	public static final String API_DOWNLOADATA = "downloadata";
	public static final String API_ADDDEVICETEMPLATE = "adddevicetemplate";
	public static final String API_DELETEDEVICETEMPLATE = "deletedevicetemplate";
	public static final String API_FINDDEVICE = "finddevice";
	public static final String API_FINDDEVICETEMPLATE = "finddevicetemplate";
	public static final String API_GENERATESECRETKEY = "generatesecretkey";
	public static final String API_GETSECRETKEY = "getsecretkey";
	public static final String API_GETVPDSINFO = "getvpdsinfo";
	public static final String API_LISTALLDEVICETEMPLATES = "listalldevicetemplates";
	public static final String API_ACTUATEDEVICE = "actuatedevice";
	public static final String API_LISTACTUATIONREQUEST = "listactuationrequests";
	public static final String API_CANCELACTUATIONREQUEST = "cancelactuationrequests";
	public static final String API_LISTASKLET = "listasklet";
	public static final String API_ADDGUARDRULE = "addguardrule";
	public static final String API_DELGUARDRULE = "delguardrule";
	public static final String API_GETGUARDRULE = "getguardrule";
	public static final String API_LISTGUARDRULE = "listallguardrules";
	public static final String API_ADDASSOCGUARDRULE = "addassocguardrule";
	public static final String API_DELASSOCGUARDRULE = "delassocguardrule";
	public static final String API_GETASSOCGUARDRULE = "getassocguardrule";
	public static final String API_LISTASSOCGUARDRULE = "listassocguardrules";

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
	public static final String MSG_REQUIRED = " is required";
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
	 * Configuration variables
	 */
	public static final String BROKER_NAME = "broker.url";

	/*
	 * URL's
	 */
	public static final String URL_UI_SERVER = "http://localhost:9003/";
	public static final String URL_BROKER_SERVER = Play.configuration.getProperty(BROKER_NAME);
	//public static final String URL_REPOSITORY_SERVER = "http://localhost:9000/";
	public String URL_REPOSITORY_SERVER="dfhdsf";
	//VPDS APIs
	
	
	
	// Broker APIs
	
	public static final String URL_BROKER_LOGIN = URL_BROKER_SERVER + "user/login";
	
	public static final String URL_BROKER_REGISTER_VPDS = URL_BROKER_SERVER
			+ "vpds/register";
	
	public static final String URL_BROKER_LIST_VPDS = URL_BROKER_SERVER
			+ "vpds/list";
	
	public static final String URL_BROKER_LIST_USER = URL_BROKER_SERVER
			+ "user/list";

	public static final String URL_BROKER_REGISTER_USER = URL_BROKER_SERVER
			+ "user/register";

	public static final String URL_BROKER_SHARE_DEVICE = URL_BROKER_SERVER
			+ "device/share";
	
	public static final String URL_BROKER_VIEW_SHARE_DEVICES_OWNER = URL_BROKER_SERVER
			+ "device/owner/shared";
	
	public static final String URL_BROKER_VIEW_SHARE_DEVICES_USER = URL_BROKER_SERVER
			+ "device/user/shared";
	
	public static final String URL_BROKER_LIST_SHARED_DEVICES_BY_OWNER = URL_BROKER_SERVER
			+ "device/owner/shared";
	
	public static final String URL_BROKER_SEARCH_DEVICE = URL_BROKER_SERVER
			+ "device/search";
	
	public static final String URL_BROKER_GET_ACCESS_KEY = URL_BROKER_SERVER
			+ "accesskey/get";

	public static final String URL_BROKER_LIST_ALL_DEVICES = URL_BROKER_SERVER
			+ "listalldevices";
	
	public static final String URL_HOME = URL_UI_SERVER + "home";
	public static final String URL_LOGIN = URL_UI_SERVER + "login";

	/*
	 * Parameters
	 */

	public static final String USERNAME = "username";
	public static final String SECRETKEY = "secretkey";
	public static final String USERTYPE = "usertype";
	public static final String REQUEST_BODY = "body";
	public static final String FAKE_SECRET_KEY = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
	
	public static final String USER = "USER";
	public static final String OWNER = "OWNER";
	
	public static final String VPDSURL = "vpdsurl";
	public static final String VPDSKEY = "vpdskey";
	public static final String VPDSNAME = "vpdsname";
	
	public static final String DEVICENAME = "devicename";
	public static final String SENSORNAME = "sensorname";
	public static final String SENSORID = "sensorid";
	public static final String ACTUATORNAME = "actuatorname";
	public static final String ACTUATORID = "actuatorid";

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
	public static final String LOGGER_INFO_LOGIN_SUCCESSFUL = "Login Successful: ";
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
	public static final String BASE_IMAGE_URL = "public/generatedImages/";
	public static final String BASE_OUTPUTCSV_URL = "public/csv/";

	public class Error {

		public static final String E404 = "Not Found!";
	}


	public class Warnings {

		public static final String WARNING1 = "warning1";
	}

}
