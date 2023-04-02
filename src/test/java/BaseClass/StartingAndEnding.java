package BaseClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class StartingAndEnding {
	 public static Connection con;
	
	@BeforeClass
	public void setup() throws SQLException {
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels","root","Humanbeing05#");
	}
	@AfterClass
	public void teardown() throws SQLException {
		con.close();
	}

}
