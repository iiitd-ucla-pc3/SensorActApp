package controllers;

/*
 * Standard Java imports
 */
import java.util.HashMap;

import play.mvc.Before;
import play.mvc.Controller;
import edu.iiitd.muc.sensoract.apis.SensorActAPI;
import edu.iiitd.muc.sensoract.constants.Const;
import edu.iiitd.muc.sensoract.exceptions.InvalidJsonException;

/**
 * Application class, entry point for all APIs for the broker
 * 
 * @author Nipun Batra
 * @version 1.0
 * @category Controller
 */

public class Application extends Controller {

	public static SensorActAPI api = new SensorActAPI();
	public static HashMap<String, String> usernameToSecretKeyMap = new HashMap<String, String>();

	/*
	 * If a user has not logged in,he is redirected to the index page
	 */
	@Before(unless = { "login", "index", "registeruser" })
	static void checkAuthentication() {
		System.out.println(session);
		if (session.get(Const.USERNAME) == null)
			index();
	}

	public static void index() {
		render();
	}

	public static void logout() {
		usernameToSecretKeyMap.remove(session.get(Const.USERNAME));
		session.clear();
		redirect(Const.URL_UI_SERVER);
	}

	public static void home() {
		flash.put(Const.USERNAME, session.get(Const.USERNAME));
		renderArgs.put(Const.USERNAME, session.get(Const.USERNAME));
		render();
	}

	public static void device() {
		render();
	}

	public static void device2() {
		render();
	}

	public static void adddevice() {
		String deviceBody = request.params.get(Const.REQUEST_BODY);
		api.addDevice.doProcess(deviceBody);

	}

	public static void deletedevice() {
		String deleteDeviceRequest = request.params.get(Const.REQUEST_BODY);
		api.deleteDevice.doProcess(deleteDeviceRequest);
	}

	public static void querydata() {
		String queryBody = request.params.get(Const.REQUEST_BODY);
		api.queryData.doProcess(queryBody);
	}

	public static void listalldevices() {
		api.listAllDevices.doProcess();
	}

	public static void registeruser() {

		String registerUserBody = request.params.get(Const.REQUEST_BODY);
		api.registeruser.doProcess(registerUserBody);
	}

	public static void display() {

		renderArgs.put(Const.USERNAME, session.get(Const.USERNAME));
		render();
	}

	public static void display2() {
		renderArgs.put(Const.USERNAME, session.get(Const.USERNAME));
		render();
	}

	public static void repository() {
		renderArgs.put(Const.USERNAME, session.get(Const.USERNAME));
		render();
	}

	public static void login() throws InvalidJsonException

	{
		String loginBody = request.params.get(Const.REQUEST_BODY);
		api.login.doProcess(loginBody);

	}

	public static void listallrepositories() {
		api.listAllRepositories.doProcess();
	}

	public static void registerrepository() {
		String body = request.params.get(Const.REQUEST_BODY);
		api.registerRepository.doProcess(body);
	}
}