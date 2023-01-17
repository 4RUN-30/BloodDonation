package com.arunsh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Create extends HttpServlet{
	public void doPost(HttpServletRequest req,HttpServletResponse res) {
		
		String userName = req.getParameter("username");
		String password = req.getParameter("password");
		String sql = "INSERT INTO information.login(\r\n"
				+ "	user_name, password)\r\n"
				+ "	VALUES (?, ?);";
		try {
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","3027");
			
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, userName);
			st.setString(2, password);
			
			st.executeUpdate();
			st.close();
			con.close();
			
			RequestDispatcher rd = req.getRequestDispatcher("choose.html");
			rd.forward(req, res);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}