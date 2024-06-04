package emploiDuTemps_java;
import java.sql.Connection;
import java.sql.DriverManager;

public class Connexion {
	Connection cn;
	public Connexion(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			cn=DriverManager.getConnection("jdbc:mysql://localhost/emploiDuTemps_db","root","");
	    System.out.println("Connection etablie!");
		}
		catch(Exception e){
			System.out.println("non connectee!");
			
		}
		
	}
    public Connection laConnection(){
    	return cn;
    }
}
