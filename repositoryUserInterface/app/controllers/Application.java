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
package controllers;

/*
 * Standard Java imports
 */
import java.util.HashMap;

import org.junit.Test;

import play.Play;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.Http;
import edu.iiitd.muc.sensoract.apis.Global;
import edu.iiitd.muc.sensoract.apis.SensorActAPI;
import edu.iiitd.muc.sensoract.constants.Const;
import edu.iiitd.muc.sensoract.exceptions.InvalidJsonException;
import edu.iiitd.muc.sensoract.utilities.SecretKey;

/**
 * Application class, entry point for all APIs for the repo
 * 
 * @author Nipun Batra, Manaswi Saha
 * @version 1.0
 * @category Controller
 */

public class Application extends Controller {

	public static SensorActAPI api = new SensorActAPI();
	public static HashMap<String, String> usernameToSecretKeyMap = new HashMap<String, String>();

	/*
	 * If a user has not logged in,he is redirected to the index page
	 */
	@Before(unless = { "login", "index","registeruser" })
	static void checkAuthentication() {
		System.out.println("Session: " + session.get(Const.USERNAME));
		if (session.get(Const.USERNAME) == null){
			index();
			System.out.println("New session will be created");
		}		

		if (usernameToSecretKeyMap.get(session.get(Const.USERNAME)) == null) {
			System.out.println("New session exists");
			index();

		}
	}
	
	@Before(only={"device","guardrule","actuate","presenceactuate", 
			"display","addguardrule","assocguardrule", "editdevice","editguardrule"})
	static void checkVPDSDetails() {
		System.out.println("Session VPDS URL: " + session.get(Const.VPDSURL));
		if (session.get(Const.VPDSURL) == null)
			home();
		Global global=new Global(session.get(Const.VPDSURL), session.get(Const.VPDSKEY));
	}
	

	public static void index() {
		if (usernameToSecretKeyMap.get(session.get(Const.USERNAME)) != null) {
			System.out.println("Session exists");
			home();
			
		}
			
		render();
	}

	public static void speak()
	{
		render();
	}
	
	public static void logout() {
		usernameToSecretKeyMap.remove(session.get(Const.USERNAME));
		session.clear();
		System.out.println("Proof: " + Http.Request.current().getBase());
		//redirect(Http.Request.current().getBase());
		index();
		Play.configuration.getProperty("application.baseUrl");
	}
	
	public static void relogin(){
		session.remove(Const.USERTYPE);
		session.put(Const.USERTYPE, Const.OWNER);
		home();
	}

	public static void home() {
		
		String usertype = session.get(Const.USERTYPE);
		System.out.println("UserType:" + usertype);
		renderArgs.put(Const.USERTYPE, usertype);
		if(usertype.equals(Const.USER))
			homeuser();
		else if(usertype.equals(Const.OWNER))
			homevo();
	}
	
	public static void homeuser() {
		flash.put(Const.USERNAME, session.get(Const.USERNAME));
		String usertype = session.get(Const.USERTYPE);
		renderArgs.put(Const.USERTYPE, usertype);
		renderArgs.put(Const.USERNAME, session.get(Const.USERNAME));
		render();
	}
	
	public static void homevo() {
		flash.put(Const.USERNAME, session.get(Const.USERNAME));
		String usertype = session.get(Const.USERTYPE);
		if(usertype.equals("USER"))
			homeuser();
		renderArgs.put(Const.USERTYPE, usertype);
		renderArgs.put(Const.USERNAME, session.get(Const.USERNAME));
		render();
	}
	
	public static void managehome() {
		flash.put(Const.USERNAME, session.get(Const.USERNAME));
		String usertype = session.get(Const.USERTYPE);
		System.out.println("Snsodjsdjs");	
		renderArgs.put(Const.USERTYPE, usertype);
		renderArgs.put(Const.USERNAME, session.get(Const.USERNAME));
		renderArgs.put(Const.VPDSNAME, session.get(Const.VPDSNAME));
		renderArgs.put(Const.VPDSURL, session.get(Const.VPDSURL));
		renderArgs.put(Const.VPDSKEY, session.get(Const.VPDSKEY));
		render();
	}
	
	public static void getsecretkey() {
		api.getSecretKey.doProcess();
	}
	
	public static void registervpds() {
		renderArgs.put(Const.USERNAME, session.get(Const.USERNAME));
		String usertype = session.get(Const.USERTYPE);
		renderArgs.put(Const.USERTYPE, usertype);
		render();
	}
	
	public static void registerVPDSToBroker() {		
		String body = request.params.get(Const.REQUEST_BODY);
		api.registerVPDS.doProcess(body);
	}
	
	public static void sharedevices() {
		renderArgs.put(Const.USERNAME, session.get(Const.USERNAME));
		String usertype = session.get(Const.USERTYPE);
		renderArgs.put(Const.USERTYPE, usertype);
		render();
	}
	public static void share() {
		renderArgs.put(Const.USERNAME, session.get(Const.USERNAME));
		String usertype = session.get(Const.USERTYPE);
		renderArgs.put(Const.USERTYPE, usertype);
		render();
	}
	
	public static void getuserlist() {
		api.getUserList.doProcess();
	}
	
	public static void sharedevice() {
		api.shareDevice.doProcess(request.params.get(Const.REQUEST_BODY));
	}
	
	public static void viewsharedevices() {
		api.viewShareDevices.doProcess(request.params.get(Const.REQUEST_BODY));
	}
	
	public static void opclassifier() {
		api.opClassifier.doProcess(request.params.get(Const.REQUEST_BODY));
	}
	
	public static void userdisplay() {

		renderArgs.put(Const.USERNAME, session.get(Const.USERNAME));
		renderArgs.put(Const.VPDSNAME, session.get(Const.VPDSNAME));
		renderArgs.put(Const.DEVICENAME, session.get(Const.DEVICENAME));
		renderArgs.put(Const.SENSORNAME, session.get(Const.SENSORNAME));
		
		render();
	}
	
	public static void useractuate() {

		renderArgs.put(Const.USERNAME, session.get(Const.USERNAME));
		renderArgs.put(Const.VPDSNAME, session.get(Const.VPDSNAME));
		renderArgs.put(Const.DEVICENAME, session.get(Const.DEVICENAME));
		renderArgs.put(Const.ACTUATORNAME, session.get(Const.ACTUATORNAME));
		
		render();
	}
	
	public static void userpresenceactuate() {
		renderArgs.put(Const.USERNAME, session.get(Const.USERNAME));
		renderArgs.put(Const.VPDSNAME, session.get(Const.VPDSNAME));
		renderArgs.put(Const.DEVICENAME, session.get(Const.DEVICENAME));
		renderArgs.put(Const.ACTUATORNAME, session.get(Const.ACTUATORNAME));
		
		render();
	}

	public static void managevpds() {
		renderArgs.put(Const.USERNAME, session.get(Const.USERNAME));
		String usertype = session.get(Const.USERTYPE);
		renderArgs.put(Const.USERTYPE, usertype);
		render();
	}
	
	public static void getvpdsdetails() {
		
		String body = request.params.get(Const.REQUEST_BODY);
		api.manageVPDS.doProcess(body);
	}
	
	public static void listvpds() {
		
		String body = request.params.get(Const.REQUEST_BODY);
		api.getVPDSList.doProcess(body);
	}	
	
	
	public static void device() {
		renderArgs.put(Const.USERNAME, session.get(Const.USERNAME));
		String usertype = session.get(Const.USERTYPE);
		renderArgs.put(Const.USERTYPE, usertype);
		renderArgs.put(Const.VPDSNAME, session.get(Const.VPDSNAME));
		renderArgs.put(Const.VPDSURL, session.get(Const.VPDSURL));
		renderArgs.put(Const.VPDSKEY, session.get(Const.VPDSKEY));
		render();
	}
	
	
	public static void editdevice(String devicename, String isdevice,
			String mode) {
		System.out.println("Device name" + devicename);
		renderArgs.put("devicename", devicename);
		renderArgs.put("isdevice", isdevice);
		renderArgs.put("mode", mode);
		renderArgs.put(Const.USERNAME, session.get(Const.USERNAME));
		String usertype = session.get(Const.USERTYPE);
		renderArgs.put(Const.USERTYPE, usertype);
		renderArgs.put(Const.VPDSNAME, session.get(Const.VPDSNAME));
		renderArgs.put(Const.VPDSURL, session.get(Const.VPDSURL));
		renderArgs.put(Const.VPDSKEY, session.get(Const.VPDSKEY));
		
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
		renderArgs.put(Const.USERNAME, session.get(Const.USERNAME));
		renderArgs.put(Const.VPDSNAME, session.get(Const.VPDSNAME));
		renderArgs.put(Const.VPDSURL, session.get(Const.VPDSURL));
		renderArgs.put(Const.VPDSKEY, session.get(Const.VPDSKEY));
		
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
	
	public static void listactuationrequests() {		
		api.listActRequests.doProcess(request.params.get(Const.REQUEST_BODY));
	}
	
	public static void cancelactuationrequests() {		
		api.cancelActRequests.doProcess(request.params.get(Const.REQUEST_BODY));
	}

	public static void listalldevices() {
		api.listAllDevices.doProcess();
	}
	
	public static void listallvpdsdevices() {
		api.listAllVPDSDevices.doProcess(request.params.get(Const.REQUEST_BODY));
	}

	public static void listalldevicetemplates() {
		api.listAllDeviceTemplates.doProcess();
	}
	
	public static void listallusers() {
		api.listAllUsers.doProcess();
	}

	public static void registeruser() {

		String registerUserBody = request.params.get(Const.REQUEST_BODY);
		api.registeruser.doProcess(registerUserBody);
	}
	
	
	public static void display() {

		renderArgs.put(Const.USERNAME, session.get(Const.USERNAME));
		renderArgs.put(Const.VPDSNAME, session.get(Const.VPDSNAME));
		renderArgs.put(Const.VPDSURL, session.get(Const.VPDSURL));
		renderArgs.put(Const.VPDSKEY, session.get(Const.VPDSKEY));
		
		render();
	}
	
	
	public static void actuate() {

		renderArgs.put(Const.USERNAME, session.get(Const.USERNAME));
		renderArgs.put(Const.VPDSNAME, session.get(Const.VPDSNAME));
		renderArgs.put(Const.VPDSURL, session.get(Const.VPDSURL));
		renderArgs.put(Const.VPDSKEY, session.get(Const.VPDSKEY));
		
		render();
	}
	
	
	public static void actuatedevice() {
		String deviceActuateBody = request.params.get(Const.REQUEST_BODY);
		renderArgs.put(Const.VPDSNAME, session.get(Const.VPDSNAME));
		renderArgs.put(Const.VPDSURL, session.get(Const.VPDSURL));
		renderArgs.put(Const.VPDSKEY, session.get(Const.VPDSKEY));
		
		api.actuateDevice.doProcess(deviceActuateBody);

	}
	
	
	public static void presenceactuate() {
		renderArgs.put(Const.USERNAME, session.get(Const.USERNAME));
		renderArgs.put(Const.VPDSNAME, session.get(Const.VPDSNAME));
		renderArgs.put(Const.VPDSURL, session.get(Const.VPDSURL));
		renderArgs.put(Const.VPDSKEY, session.get(Const.VPDSKEY));
		
		render();
	}
	
	
	public static void guardrule() {
		renderArgs.put(Const.USERNAME, session.get(Const.USERNAME));
		renderArgs.put(Const.VPDSNAME, session.get(Const.VPDSNAME));
		renderArgs.put(Const.VPDSURL, session.get(Const.VPDSURL));
		renderArgs.put(Const.VPDSKEY, session.get(Const.VPDSKEY));
		
		render();

	}
	
	public static void addGuardRule() {
		api.addGuardRule.doProcess(request.params.get(Const.REQUEST_BODY));

	}
	
	public static void delGuardRule() {
		api.delGuardRule.doProcess(request.params.get(Const.REQUEST_BODY));

	}
	
	public static void getGuardRule() {
		api.getGuardRule.doProcess(request.params.get(Const.REQUEST_BODY));

	}
	
	public static void listallguardrules() {
		api.listGuardRule.doProcess();
	}
	
	
	public static void editguardrule(String guardrulename, String mode) {
		System.out.println("Guard Rule: " + guardrulename);
		renderArgs.put("guardrulename", guardrulename);
		renderArgs.put("mode", mode);
		renderArgs.put(Const.USERNAME, session.get(Const.USERNAME));
		renderArgs.put(Const.VPDSNAME, session.get(Const.VPDSNAME));
		renderArgs.put(Const.VPDSURL, session.get(Const.VPDSURL));
		renderArgs.put(Const.VPDSKEY, session.get(Const.VPDSKEY));
		render();

	}	
	
	public static void addAssocGuardRule() {
		api.addAssocGuardRule.doProcess(request.params.get(Const.REQUEST_BODY));

	}
	
	public static void delAssocGuardRule() {
		api.delAssocGuardRule.doProcess(request.params.get(Const.REQUEST_BODY));

	}
	
	public static void getAssocGuardRule() {
		api.getAssocGuardRule.doProcess(request.params.get(Const.REQUEST_BODY));

	}
	
	public static void listassocguardrules() {
		api.listAssocGuardRule.doProcess();
	}
	
	
	public static void assocguardrule(String guardrulename, String mode) {
		System.out.println("Guard Rule: " + guardrulename);
		renderArgs.put("guardrulename", guardrulename);
		renderArgs.put("mode", mode);
		renderArgs.put(Const.USERNAME, session.get(Const.USERNAME));
		renderArgs.put(Const.VPDSNAME, session.get(Const.VPDSNAME));
		renderArgs.put(Const.VPDSURL, session.get(Const.VPDSURL));
		renderArgs.put(Const.VPDSKEY, session.get(Const.VPDSKEY));
		
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
