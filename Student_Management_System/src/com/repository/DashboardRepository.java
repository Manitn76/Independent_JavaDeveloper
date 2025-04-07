package com.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.SQLException;

import com.model.DashboardModel;

public class DashboardRepository {
	com.connection.Connection con=new com.connection.Connection(); 
	String q="";
	int id=0;
	boolean flag=false;
	PreparedStatement pst=null;
	ResultSet rs=null;
	
	public boolean viewDetails(DashboardModel dashboardmodel) {
		flag=false;
		q="SELECT * FROM STUDENT_DETAILS WHERE S_ID=?;";
		id=dashboardmodel.getsId();
		try {
			pst=con.getConnection().prepareStatement(q);
			pst.setInt(1, id);
			rs=pst.executeQuery();
			while(rs.next()) {
				if (id == rs.getInt("S_ID")) {
					dashboardmodel.setId(rs.getInt("ID"));
					dashboardmodel.setsId(rs.getInt("S_ID"));
					dashboardmodel.setName(rs.getString("NAME"));
					dashboardmodel.setMajor(rs.getString("MAJOR"));
					flag=true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public boolean addDetails(DashboardModel dashboardmodel) {
		id=dashboardmodel.getsId();
		String name=dashboardmodel.getName();
		String dept=dashboardmodel.getMajor();
		q="INSERT INTO STUDENT_DETAILS(S_ID,NAME,MAJOR)VALUES(?,?,?);";
		try {
			pst=con.getConnection().prepareStatement(q);
			pst.setInt(1, id);
			pst.setString(2, name);
			pst.setString(3, dept);
			int res=pst.executeUpdate();
			flag=(res>0)?true:false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public boolean deleteDetails(DashboardModel dashboardmodel) {
		id=dashboardmodel.getsId();
		q="DELETE FROM STUDENT_DETAILS WHERE S_ID=?";
		try {
			pst=con.getConnection().prepareStatement(q);
			pst.setInt(1, id);
			int res=pst.executeUpdate();
			flag=(res>0)?true:false;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public boolean editDetails(DashboardModel dashboardmodel) {
		id=dashboardmodel.getsId();
		q="UPDATE STUDENT_DETAILS SET NAME=?, MAJOR=? WHERE S_ID=?;";
		try {
			pst=con.getConnection().prepareStatement(q);
			pst.setString(1, dashboardmodel.getName());
			pst.setString(2, dashboardmodel.getMajor());
			pst.setInt(3, id);
			int res=pst.executeUpdate();
			flag=(res>0)?true:false;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}
