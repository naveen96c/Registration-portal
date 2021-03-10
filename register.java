
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

public class register extends HttpServlet {
	@Override
	protected void doPost( HttpServletRequest req, HttpServletResponse res ){
		String name = req.getParameter("name");
		String password = req.getParameter("password");
			try{
				
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_form","root","9171710887");
				Statement stm = con.createStatement();
				
				ResultSet RS = stm.executeQuery("SELECT COUNT(*) AS total FROM students where Name ='" + name + "' and Password ='" + password + "'");
				
				int size =0;  
				if (RS != null)   
				{  
					RS.next();
					size = RS.getInt("total");
				}
				System.out.println("Row count ::" + size);
				if (size > 0) {
					System.out.println("Username already exists");
					res.getWriter().print( "1" );
					res.getWriter().close();
					res.getWriter().flush();
				}
				else{
					
					String qry = "insert into students (Name, Password) values ('"+name +"', '"+password+"');";
					int rs = stm.executeUpdate(qry);
				
					System.out.println("############### Successfully inserted .....");
				
					res.getWriter().print( "2" );
					res.getWriter().close();
					res.getWriter().flush();
				}
				con.close();
				
			}	catch (SQLException ex) {
				try{
					res.getWriter().print( ex.getMessage() );
					res.getWriter().close();
					res.getWriter().flush();
				}catch (IOException e){
				}
				System.out.println("An error occurred. Maybe user123/password is invalid " );
				ex.printStackTrace();
			} catch( Exception e ){
				System.out.println("error in main func");
				e.printStackTrace();
			}
	}
}