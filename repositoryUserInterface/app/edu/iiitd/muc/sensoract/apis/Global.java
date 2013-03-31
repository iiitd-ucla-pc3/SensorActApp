package edu.iiitd.muc.sensoract.apis;

import javax.mail.Session;

import controllers.Application;

public class Global {
	public static String URL_REPOSITORY_SERVER;
	public static String VPDS_OWNER_KEY;
	
	public static String URL_REPOSITORY_REGISTER_USER;
	
	public static String URL_REPOSITORY_LOGIN_USER;
	
	public static String URL_REPOSITORY_LIST_USERS;
	
	public static String URL_REPOSITORY_ADD_DEVICE;
	
	public static String URL_REPOSITORY_ADD_DEVICE_TEMPLATE;
	
	public static String URL_REPOSITORY_FIND_DEVICE;
	
	public static String URL_REPOSITORY_FIND_DEVICE_TEMPLATE;
	
	public static String URL_REPOSITORY_DELETE_DEVICE;
	
	public static String URL_REPOSITORY_DELETE_DEVICE_TEMPLATE;
	
	public static String URL_REPOSITORY_GET_DEVICE;

	public static String URL_REPOSITORY_LIST_ALL_DEVICES;
	
	public static String URL_REPOSITORY_LIST_ALL_DEVICE_TEMPLATES;

	public static String URL_REPOSITORY_ACTUATE_DEVICE;
	
	public static String URL_REPOSITORY_LIST_ACTUATION_REQUEST;
	
	public static String URL_REPOSITORY_CANCEL_ACTUATION_REQUEST ;
	
	public static String URL_REPOSITORY_LIST_TASKLET ;
	
	public static String URL_REPOSITORY_ADD_GUARD_RULE;
	
	public static String URL_REPOSITORY_DELETE_GUARD_RULE;
	
	public static String URL_REPOSITORY_GET_GUARD_RULE;
	
	public static String URL_REPOSITORY_LIST_GUARD_RULE;
	
	public static String URL_REPOSITORY_ASSOC_GUARD_RULE_ADD;
	
	public static String URL_REPOSITORY_ASSOC_GUARD_RULE_DELETE;
	
	public static String URL_REPOSITORY_ASSOC_GUARD_RULE_GET;
	
	public static String URL_REPOSITORY_ASSOC_GUARD_RULE_LIST;

	public static String URL_REPOSITORY_QUERY_DATA;
	
	public static String URL_REPOSITORY_GET_INFO;
	
	public static String URL_REPOSITORY_GET_KEY_LIST;
	
	public static String URL_REPOSITORY_GENERATE_SECRET_KEY;
	
	public Global()
	{
		
	}

	public Global(String vpds, String key) {
		this.URL_REPOSITORY_SERVER=vpds;
		this.VPDS_OWNER_KEY = key;
		
		this.URL_REPOSITORY_REGISTER_USER = URL_REPOSITORY_SERVER
				+ "user/register";
		
		this.URL_REPOSITORY_LOGIN_USER = URL_REPOSITORY_SERVER
				+ "user/login";
		
		this.URL_REPOSITORY_LIST_USERS = URL_REPOSITORY_SERVER
				+ "user/list";
		
		this.URL_REPOSITORY_ADD_DEVICE = URL_REPOSITORY_SERVER
				+ "device/add";
		
		this.URL_REPOSITORY_ADD_DEVICE_TEMPLATE = URL_REPOSITORY_SERVER
				+ "device/template/add";

		this.URL_REPOSITORY_FIND_DEVICE = URL_REPOSITORY_SERVER
				+ "device/get";
		
		this.URL_REPOSITORY_FIND_DEVICE_TEMPLATE = URL_REPOSITORY_SERVER
				+ "device/template/get";

		this.URL_REPOSITORY_DELETE_DEVICE = URL_REPOSITORY_SERVER
				+ "device/delete";
		
		this.URL_REPOSITORY_DELETE_DEVICE_TEMPLATE = URL_REPOSITORY_SERVER
				+ "device/template/delete";

		this.URL_REPOSITORY_GET_DEVICE = URL_REPOSITORY_SERVER
				+ "device/get";
		
		this.URL_REPOSITORY_LIST_ALL_DEVICES = URL_REPOSITORY_SERVER
				+ "device/list";
		
		this.URL_REPOSITORY_LIST_ALL_DEVICE_TEMPLATES = URL_REPOSITORY_SERVER
				+ "device/template/list";

		this.URL_REPOSITORY_ACTUATE_DEVICE = URL_REPOSITORY_SERVER
				+ "device/actuate";
		
		this.URL_REPOSITORY_LIST_ACTUATION_REQUEST = URL_REPOSITORY_SERVER
				+ "device/actuationrequest/list";
		
		this.URL_REPOSITORY_CANCEL_ACTUATION_REQUEST = URL_REPOSITORY_SERVER
				+ "device/actuationrequest/cancel";
		
		this.URL_REPOSITORY_LIST_TASKLET = URL_REPOSITORY_SERVER
				+ "tasklet/list";
		
		this.URL_REPOSITORY_ADD_GUARD_RULE = URL_REPOSITORY_SERVER
				+ "guardrule/add";
		
		this.URL_REPOSITORY_DELETE_GUARD_RULE = URL_REPOSITORY_SERVER
				+ "guardrule/delete";
		
		this.URL_REPOSITORY_GET_GUARD_RULE = URL_REPOSITORY_SERVER
				+ "guardrule/get";
		
		this.URL_REPOSITORY_LIST_GUARD_RULE = URL_REPOSITORY_SERVER
				+ "guardrule/list";
		
		this.URL_REPOSITORY_ASSOC_GUARD_RULE_ADD = URL_REPOSITORY_SERVER
				+ "guardrule/association/add";
		
		this.URL_REPOSITORY_ASSOC_GUARD_RULE_DELETE = URL_REPOSITORY_SERVER
				+ "guardrule/association/delete";
		
		this.URL_REPOSITORY_ASSOC_GUARD_RULE_GET = URL_REPOSITORY_SERVER
				+ "guardrule/association/get";
		
		this.URL_REPOSITORY_ASSOC_GUARD_RULE_LIST = URL_REPOSITORY_SERVER
				+ "guardrule/association/list";

		this.URL_REPOSITORY_QUERY_DATA = URL_REPOSITORY_SERVER
				+ "data/query";
		
		this.URL_REPOSITORY_GET_INFO = URL_REPOSITORY_SERVER
				+ "repo/info";
		
		this.URL_REPOSITORY_GET_KEY_LIST = URL_REPOSITORY_SERVER
				+ "key/list";
		
		this.URL_REPOSITORY_GENERATE_SECRET_KEY = URL_REPOSITORY_SERVER
				+ "user/generate/repokey";
		
	}
	


}
