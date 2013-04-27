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
package edu.iiitd.muc.sensoract.apis;

import com.google.gson.Gson;

import controllers.Application;
import edu.iiitd.muc.sensoract.utilities.SensorActLogger;

public class SensorActAPI extends Application {

	public static ParamValidator validator = new ParamValidator();
	public static Gson gson = new Gson();
	public static RegisterUser registeruser = new RegisterUser();
	public static UserList listAllUsers = new UserList();
	public static GetDevice getDevice = new GetDevice();
	public static ListAllDevices listAllDevices = new ListAllDevices();
	public static ListAllVPDSDevices listAllVPDSDevices = new ListAllVPDSDevices();
	public static ListAllDeviceTemplates listAllDeviceTemplates = new ListAllDeviceTemplates();

	public static AddDevice addDevice = new AddDevice();
	public static AddDeviceTemplate addDeviceTemplate = new AddDeviceTemplate();

	public static DeleteDevice deleteDevice = new DeleteDevice();
	public static DeleteDeviceTemplate deleteDeviceTemplate = new DeleteDeviceTemplate();

	public static ActuateDevice actuateDevice = new ActuateDevice();	
	public static ListActuationRequest listActRequests = new ListActuationRequest();
	public static CancelActuationRequest cancelActRequests = new CancelActuationRequest();
	
	public static ListTasklet listTasklet = new ListTasklet();
	
	public static AddGuardRule addGuardRule = new AddGuardRule();
	public static DeleteGuardRule delGuardRule = new DeleteGuardRule();
	public static GetGuardRule getGuardRule = new GetGuardRule();
	public static ListGuardRule listGuardRule = new ListGuardRule();
	
	public static AddAssocGuardRule addAssocGuardRule = new AddAssocGuardRule();
	public static DeleteAssocGuardRule delAssocGuardRule = new DeleteAssocGuardRule();
	public static GetAssocGuardRule getAssocGuardRule = new GetAssocGuardRule();
	public static ListAssocGuardRule listAssocGuardRule = new ListAssocGuardRule();
	
	public static QueryData queryData = new QueryData();
	public static DownloadData downloadData = new DownloadData();
	public static QueryData2 queryData2 = new QueryData2();

	public static Login login = new Login();
	public static GetSecretKey getSecretKey = new GetSecretKey();
	public static GetVPDSInfo getvpdsinfo = new GetVPDSInfo();
	public static GetRepositoryInfo getRepositoryInfo = new GetRepositoryInfo();
	public static GenerateSecretKey generateSecretKey = new GenerateSecretKey();
	public static FindDevice findDevice = new FindDevice();
	public static FindDeviceTemplate findDeviceTemplate = new FindDeviceTemplate();
	
	public static RegisterVPDSToBroker registerVPDS = new RegisterVPDSToBroker();
	public static ManageVPDS manageVPDS = new ManageVPDS();
	public static GetVPDSList getVPDSList = new GetVPDSList();
	public static GetUserList getUserList = new GetUserList();
	public static ShareDevice shareDevice = new ShareDevice();
	public static ViewSharedDevices viewShareDevices = new ViewSharedDevices();
	public static ListSharedDevicesByOwner listSharedDevices = new ListSharedDevicesByOwner();
	public static OperationClassifier opClassifier = new OperationClassifier();

	public static SensorActLogger logger = new SensorActLogger();
	public static SoundInput soundInput = new SoundInput();
}
