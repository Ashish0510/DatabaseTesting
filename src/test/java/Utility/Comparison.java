package Utility;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;

public class Comparison {
	public boolean comapareresults(ResultSet rs1,ResultSet rs2) throws SQLException {
		while(rs1.next()) {
			rs2.next();
			int count=rs1.getMetaData().getColumnCount();
			//System.out.println("the number of the column :"+count);
			
			for(int i=1;i<=count;i++) {
				if(!StringUtils.equals(rs1.getString(i),rs2.getString(i))) {
				return false;
				}
			}
		}
		return true;
		
	}
}
