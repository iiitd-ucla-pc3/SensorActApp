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
 * Application class, entry point for all APIs for the repo
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
	@Before(unless = { "login", "index" })
	static void checkAuthentication() {
		System.out.println("Session" + session.get(Const.USERNAME));
		if (session.get(Const.USERNAME) == null)
			index();

		if (usernameToSecretKeyMap.get(session.get(Const.USERNAME)) == null) {
			index();

		}
	}

	public static void index() {
		if (usernameToSecretKeyMap.get(session.get(Const.USERNAME)) != null)
			home();
		render();
	}

	public static void speak()
	{
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

	public static void editdevice(String devicename, String isdevice,
			String mode) {
		System.out.println("Device name" + devicename);
		renderArgs.put("devicename", devicename);
		renderArgs.put("isdevice", isdevice);
		renderArgs.put("mode", mode);

		render();
	}

	public static void finddevice() {
		String body = request.params.get(Const.REQUEST_BODY);
		api.findDevice.doProcess(body);

	}

	public static void finddevicetemplate() {
		String body = request.params.get(Const.REQUEST_BODY);
		api.findDeviceTemplate.doProcess(body);

	}

	public static void device2() {
		render();
	}

	public static void adddevice() {
		String deviceBody = request.params.get(Const.REQUEST_BODY);
		api.addDevice.doProcess(deviceBody);

	}

	public static void adddevicetemplate() {
		String deviceBody = request.params.get(Const.REQUEST_BODY);
		api.addDeviceTemplate.doProcess(deviceBody);

	}

	public static void deletedevice() {
		String deleteDeviceRequest = request.params.get(Const.REQUEST_BODY);
		api.deleteDevice.doProcess(deleteDeviceRequest);
	}

	public static void deletedevicetemplate() {
		String deleteDeviceRequest = request.params.get(Const.REQUEST_BODY);
		api.deleteDeviceTemplate.doProcess(deleteDeviceRequest);
	}

	public static void querydata() {
		String queryBody = request.params.get(Const.REQUEST_BODY);
		api.queryData.doProcess(queryBody);
	}

	public static void querydata2() {
		String queryBody = request.params.get(Const.REQUEST_BODY);
		api.queryData2.doProcess(queryBody);
	}

	public static void listalldevices() {
		api.listAllDevices.doProcess();
	}

	public static void listalldevicetemplates() {
		api.listAllDeviceTemplates.doProcess();
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

	public static void getrepositoryinfo()

	{
		api.getRepositoryInfo.doProcess();

	}

	public static void generatesecretkey()

	{
		String body = request.params.get(Const.REQUEST_BODY);
		api.generateSecretKey.doProcess(body);

	}

	public static void soundinput(String userID) {

		api.soundInput.doProcess(userID);

	}

}
