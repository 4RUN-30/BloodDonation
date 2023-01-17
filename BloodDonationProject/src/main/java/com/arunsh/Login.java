package com.arunsh;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login extends HttpServlet{
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException {
		
		String userName = req.getParameter("username");
		String password = req.getParameter("password");
		try {
			PrintWriter pr = res.getWriter();
			
			
			String sql = "SELECT * FROM information.login WHERE user_name="+"'"+userName+"' AND password='"+password+"';";			
			Class.forName("org.postgresql.Driver");
			Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","3027");
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			RequestDispatcher rd;
			int i=0;
			while(rs.next()) {
				i++;
				break;
			}
			if(i==0) {
				rd = req.getRequestDispatcher("fail.html");
				rd.forward(req, res);
			}
			else {
				rd = req.getRequestDispatcher("choose.html"); 
				rd.forward(req,res);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
