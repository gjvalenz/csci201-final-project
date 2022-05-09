package util;
enum Status
{
	R, S
};

public class Message {
	public int message_id;
	public String message;
	public String time;
	public Status status;
	public int from;
	public int to;
	public String fromName;
	public String toName;
	
	public Message(int message_id, String message, String time, String status, int from, int to)
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
	
	public void setNames(String fromName, String toName)
	{
		this.fromName = fromName;
		this.toName = toName;
	}
	
	public String asJSON()
	{
		return String.format("{\"message_id\": %d, \"message\": \"%s\", \"time\": \"%s\", \"status\": \"%s\", \"from\": %d, \"to\": %d, \"nFrom\": \"%s\", \"nTo\": \"%s\" }", message_id, message, time, status, from, to, fromName, toName);
	}
}
