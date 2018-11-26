package Com.MBS.MBSBody;

public class LoginBody 
{
//	{
//        "username" : "snandigam@traveltripper.com",
//        "password" : "travel123",
//        "force_new_session" : true
//    }

	private String username;
	private String password;
	private boolean force_new_session;
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setForce_new_session(boolean force_new_session) {
		this.force_new_session = force_new_session;
	}
	
	
	

}
