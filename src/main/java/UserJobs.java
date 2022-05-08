import javax.servlet.http.HttpSession;

public class UserJobs {
	private static Boolean isActive = false;
	private static Thread t1;
	
	public static void setActive()
	{
		isActive = true;
	}
	
	public static void startJob(HttpSession session)
	{
		t1 = new GetFriendsIntoSession(session);
		t1.start();
	}
	
	public static void endJob() throws InterruptedException
	{
		if(isActive)
		{
			t1.join();
			isActive = false;
		}
	}
}
