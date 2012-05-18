package edu.iiitd.muc.sensoract.format;

public class RegisterUserRequest {

	public String username;
	public String password;
	public String email;
	public String secretkey;
	public RegisterUserRequest(String username, String password, String email) {
		
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	
}
