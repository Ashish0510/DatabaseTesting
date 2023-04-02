package DatbaseTest;

import java.sql.CallableStatement;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.StartingAndEnding;
import Utility.Comparison;

public class FirstDataBasetest extends StartingAndEnding {

	
	Statement stmt;
	ResultSet rs;
	ResultSet rs1;
	ResultSet rs2;
	Comparison cs=new Comparison();
	
	
	CallableStatement cstmt=null;
	
	
	
	
	@Test(priority=1)
public void storedProcedureExists() throws SQLException {
		stmt=con.createStatement();
		rs=stmt.executeQuery("show procedure status where name='selectAllcustomer'");
		rs.next();
		Assert.assertEquals(rs.getString("Name"),"selectAllcustomer");
		
		
	}
	@Test(priority=2)
	public void comapareAllColumns() throws SQLException {
		cstmt =con.prepareCall("{call SelectWholecustomer()}");
		rs1=cstmt.executeQuery();
		Statement stmt=con.createStatement();
		rs2=stmt.executeQuery("select * from customers ");
		Assert.assertEquals(cs.comapareresults(rs1,rs2),true);
	}
	
	@Test(priority=3)
	public void CompareWithparameter() throws SQLException {
		String parameter="USA";
		cstmt=con.prepareCall("{call selectAllcustomer(?)}");
		cstmt.setString(1, parameter);
		rs1=cstmt.executeQuery();
		Statement stmt =con.createStatement();
		rs2=stmt.executeQuery("select * from customers where city='USA'");
		Assert.assertEquals(cs.comapareresults(rs1,rs2), true);
	}
	@Test(priority=4)
	public void withtwoparameters() throws SQLException {
		cstmt=con.prepareCall("{call withTwoParameters(?,?)}");
        cstmt.setString(1,"Melbourne");
        cstmt.setString(2,"Australia");
		rs1=cstmt.executeQuery();
		
		Statement stmt=con.createStatement();
		rs2=stmt.executeQuery("select * from customers where city='Melbourne' and country='Australia'");
		Assert.assertEquals(cs.comapareresults(rs1,rs2),true);
	}
	
	
}
