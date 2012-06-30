package edu.iiitd.muc.sensoract.utilities;

import edu.iiitd.muc.sensoract.apis.SensorActAPI;
import edu.iiitd.muc.sensoract.constants.Const;
import edu.iiitd.muc.sensoract.format.APIResponse;

public class SecretKey extends SensorActAPI {
	public String getSecretKeyFromHashMap(String username) {
		String key = null;

		try {
			/*
			 * Get the secret key from the HashMap
			 */

			key = usernameToSecretKeyMap.get(username);
		} catch (Exception e) {
			logger.info(Const.API_ADDDEVICETEMPLATE,
					Const.LOGGER_INFO_SESSION_EXPIRED);
			renderJSON(gson.toJson(new APIResponse(Const.API_ADDDEVICE, 1,
					Const.LOGGER_INFO_SESSION_EXPIRED)));

		}
		return key;

	}

}
