package com.arunsh;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BloodDonationOne extends HttpServlet{
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException{
		
		String name = req.getParameter("name").toLowerCase();
		String gender = req.getParameter("gender").toLowerCase();
		String mailId = req.getParameter("mail").toLowerCase();
		String age = req.getParameter("age").toLowerCase();
		String bloodGroup = req.getParameter("group").toLowerCase();
		String dateOfBirth = req.getParameter("dob").toLowerCase();
		String mobileNumber = req.getParameter("mobile").toLowerCase();
		String state = req.getParameter("state").toLowerCase();
		String district = req.getParameter("district").toLowerCase();
		String address = req.getParameter("address").toLowerCase();
 		
		
		String sql = "INSERT INTO information.blooddonationdatabase(\r\n"
				+ "	name, gender, mail_id, age, blood_group, dob, mobile_number, state, district, address)\r\n"
				+ "	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		try {
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","3027");
				
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setString(1, name);
			st.setString(2, gender);
			st.setString(3, mailId);
			st.setString(4, age);
			st.setString(5, bloodGroup);
			st.setString(6, dateOfBirth);
			st.setString(7, mobileNumber);
			st.setString(8, state);
			st.setString(9, district);
			st.setString(10, address);
			
			
			st.executeUpdate();
			System.out.println("successful");
			
			st.close();
			con.close();
			
			res.sendRedirect("success.html");
			
			
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}