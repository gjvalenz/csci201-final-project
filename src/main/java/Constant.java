import java.util.regex.Pattern;

public class Constant {
    static public String DBUserName = "cscigroupmember";
    static public String DBPassword = "csci201spring2022";
    static public String DBURL = "jdbc:mysql://csci201-final-project-db.cdtddri9q3e5.us-east-2.rds.amazonaws.com:3306/final";
    static public java.text.SimpleDateFormat sdf = 
    	     new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    static public Pattern namePattern = Pattern.compile("^[ A-Za-z]+$");
    static public Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\."
            + "[a-zA-Z0-9_+&*-]+)*@"
            + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
            + "A-Z]{2,7}$");
  
}
