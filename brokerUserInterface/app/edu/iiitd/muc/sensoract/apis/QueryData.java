package edu.iiitd.muc.sensoract.apis;

import play.libs.WS;
import play.libs.WS.HttpResponse;
import edu.iiitd.muc.sensoract.constants.Const;
import edu.iiitd.muc.sensoract.format.APIResponse;

public class QueryData extends SensorActAPI {
	
	public final void doProcess(String queryBody)
	{	
		String secretkey=null;
		try
		{
			secretkey=usernameToSecretKeyMap.get(session.get(Const.USERNAME));
		}
		catch(Exception e)
		{
			renderJSON(gson.toJson(new APIResponse(Const.API_ADDDEVICE,1,"Session Expired.Login Again")));

		}
		String queryBodyWithSecretKey=queryBody.replace(Const.FAKE_SECRET_KEY,secretkey);
	
		HttpResponse responseFromBroker=sendRequestToBroker(queryBodyWithSecretKey);
		renderJSON(responseFromBroker.getString());
	}

	private HttpResponse sendRequestToBroker(String queryBody) {
		HttpResponse response=null;
		try{
		response = WS.url(Const.URL_BROKER_QUERY_DATA).body(queryBody).mimeType("application/json").post(); 
		}
		catch (Exception e)
		{
			renderJSON(gson.toJson(new APIResponse(Const.API_QUERYDATA,1,e.toString())));
		}
		return response;
	}

}
