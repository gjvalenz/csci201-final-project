package finalproject;

enum Status
{
	R, S
};

public class Message {
	int message_id;
	String message;
	String time;
	Status status;
	int from;
	int to;
	
	Message(int message_id, String message, String time, String status, int from, int to)
	{
		this.message_id = message_id;
		this.message = message;
		this.time = time;
		if(status.equals("S"))
		{
			this.status = Status.S;
		}
		else
			this.status = Status.R;
		this.from = from;
		this.to = to;
	}
	
	public String asJSON()
	{
		return String.format("{\"message_id\": %d, \"message\": \"%s\", \"time\": \"%s\", \"status\": \"%s\", \"from\": %d, \"to\": %d}", message_id, message, time, status, from, to);
	}
}
