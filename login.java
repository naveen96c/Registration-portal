import java.sql.*;
import javax.servlet.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class login extends HttpServlet {
	@Override
	protected void doPost( HttpServletRequest req, HttpServletResponse res ){
		try{
			
			String name = req.getParameter("name");
			String password = req.getParameter("password");
			System.out.println(name+" "+password);


			String qry = "SELECT * FROM students WHERE name='"+name+"' AND password='"+password+"';";
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_form","root","9171710887");
				Statement stm = con.createStatement();
				ResultSet rs = stm.executeQuery(qry);
				boolean userPresent = false;
				 while(rs.next()){
						String resultname = rs.getString("Name");
						String resultpassword = rs.getString("Password");
						System.out.println(resultname+" "+resultpassword);
						userPresent = true;
				 }
				 rs.close();
				 if (userPresent) {
					 res.getWriter().print( "Successfully logged in" );
				 } else {
					 res.getWriter().print( "Please Register" );
				 }
			
				con.close();			
				res.getWriter().close();
				res.getWriter().flush();

			
	        } catch (SQLException ex) {
	            System.out.println("An error occurred. Maybe user/password is invalid");
	            ex.printStackTrace();
	        } 
		}
		catch( Exception e ){
			System.out.println("error in main func");
			e.printStackTrace();
		}

	}
}