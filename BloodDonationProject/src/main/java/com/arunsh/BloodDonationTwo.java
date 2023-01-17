package com.arunsh;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BloodDonationTwo extends HttpServlet{
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException {
		
		String loc = req.getParameter("district").toLowerCase();
		String grp = req.getParameter("group").toLowerCase();
		try {
			PrintWriter pr = res.getWriter();
			
			String sql = "SELECT * FROM information.blooddonationdatabase WHERE district="+"'"+loc+"' AND blood_group='"+grp+"';";
			Class.forName("org.postgresql.Driver");
			Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","3027");
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			pr.println("<html><body bgcolor='cyan';>");
				pr.println("<html>");
						pr.println("<head>"
								+ "<style>"
								+ "table {"
								+ "  font-family: arial, sans-serif;"
								+ "  width: 100%;"
								+ "}"
								+ "td, th {"
								+ "  text-align: center;"
								+ "  padding: 8px;"
								+ "}"
								+ "tr:nth-child(even) {"
								+ "  background-color: white;"
								+ "}"
								+ "tr:nth-child(odd) {"
								+ "  background-color: cyan;"
								+ "}"
								+"table, th, td {"
								+ "  border: 1px solid black;"
								+ "}"
								+ "</style>"
								+ "</head>");
							pr.println("<body>");
							pr.println("<h1>Donors in the location :</h1><br>");
								pr.println("<table>");
									pr.println("<tr>");
										pr.println("<th>Result no</th>");
										pr.println("<th>NAME</th>");
										pr.println("<th>MAIL ID</th>");
										pr.println("<th>BLOOD GROUP</th>");
										pr.println("<th>MOBILE NUMBER</th>");
										pr.println("<th>DISTRICT</th>");
									pr.println("</tr>");
									int i=1;
								while(rs.next()) {
								 	pr.println("<tr>");
								 		pr.println("<td>"+i+"</td>");
										pr.println("<td>"+rs.getString(1)+"</td>");
										pr.println("<td>"+rs.getString(3)+"</td>");
										pr.println("<td>"+rs.getString(5)+"</td>");
										pr.println("<td>"+rs.getString(7)+"</td>");
										pr.println("<td>"+rs.getString(9)+"</td>");
									pr.println("</tr>");
									i++;
								}
								pr.println("</table>");
							pr.println("</body>");
						pr.println("</html>");
				
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
