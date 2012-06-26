package controllers;

import play.libs.WS;
import play.libs.WS.HttpResponse;
import play.mvc.Controller;

public class SendHTTP extends Controller {

	public HttpResponse sendGetRequest(String url, String invokingApiname) {
		HttpResponse response = null;
		try {
			// logger.info(invokingApiname);

			System.out.println("URL receieved is " + url);
			response = WS.url(url).get();
		} catch (Exception e) {
			renderText("Error " + e);
			// renderJSON(gson.toJson(new APIResponse(invokingApiname, 1,
			// Const.ERROR_MESSAGE_CONNECTION_FAILURE)));

		}
		return response;

	}
}
