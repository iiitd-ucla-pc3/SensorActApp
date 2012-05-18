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

