package org.udvigna.library;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SignupServlet
 */
@WebServlet("/signup.do")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String url = "jdbc:mysql://localhost:3306/library";
	
	private Connection conn;
		
	private Connection getDBConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, getDBCredentials());
		}
		catch (ClassNotFoundException e) {
			System.out.println("couldn't get a connection");
			e.printStackTrace();
		}
		
		catch (SQLException e) {
			System.out.println("couldn't get a connection");
			e.printStackTrace();
		} 
		return conn;
	}
	
	
       
    @Override
	public void init() throws ServletException {
    	System.out.println("came in init");
		getDBConnection();
		getServletContext().setAttribute("dbconnection", conn);
		
	}



	private Properties getDBCredentials() {
		Properties prop = new Properties();
		prop.setProperty("user", "root");
		prop.setProperty("password", "root");
		return prop;
	}



	/**
     * @see HttpServlet#HttpServlet()
     */
    public SignupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		Map<String, String> usermap = new HashMap<String, String>();
		usermap.put("username", request.getParameter("loginid"));
		usermap.put("password", request.getParameter("password"));
		usermap.put("firstname", request.getParameter("firstname"));
		usermap.put("lastname", request.getParameter("lastname"));
		
		LibraryHelper helper = new LibraryHelperImpl();
		


		if(helper.submitSignupForm(usermap, conn)){
			out.write("Thank you for signing");
		}
		
		System.out.println("completed post method");


		
	}

}
