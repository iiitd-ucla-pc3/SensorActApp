/**
 * 
 */
package edu.iiitd.muc.sensoract.constants;

/**
 * Defines all constants for SensorAct
 * 
 * https://github.com/bsautner/com.nimbits/blob/master/nimbits-model/src/com/
 * nimbits/client/model/Const.java
 * 
 * @author samy
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
	public static final String URL_UI_SERVER = "http://muc.iiitd.com:9003/";
	public static final String URL_BROKER_SERVER = "http://muc.iiitd.com:9001/";
	public static final String URL_BROKER_REGISTER_USER = URL_BROKER_SERVER
			+ "user/register";
	public static final String URL_BROKER_LOGIN_USER = URL_BROKER_SERVER
			+ "user/login";
	public static final String URL_BROKER_ADD_DEVICE = URL_BROKER_SERVER
			+ "device/add";
	public static final String URL_BROKER_DELETE_DEVICE = URL_BROKER_SERVER
			+ "device/delete";
	public static final String URL_BROKER_LIST_ALL_DEVICES = URL_BROKER_SERVER
			+ "device/list";
	public static final String URL_BROKER_QUERY_DATA = URL_BROKER_SERVER
			+ "data/query";
	public static final String URL_BROKER_LIST_ALL_REPOSITORIES = URL_BROKER_SERVER
			+ "user/repo/list";
	public static final String URL_BROKER_REGISTER_REPOSITORY = URL_BROKER_SERVER
			+ "user/repo/register";

	public static final String URL_HOME = URL_UI_SERVER + "home";
	public static final String URL_LOGIN = URL_UI_SERVER + "login";

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
