package org.udvigna.library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class LibraryHelperImpl implements LibraryHelper {
	
	private static final String INSERT_USER = "INSERT INTO user" +  "(username, firstname, lastname, password) VALUES" +  "(? , ? ,?, ?) ";

	@SuppressWarnings("finally")
	@Override
	public boolean submitSignupForm(Map<String, String> userMap, Connection conn){
		boolean inserted = false;
		

			try (PreparedStatement stmt = conn.prepareStatement(INSERT_USER)){
				stmt.setString(1, userMap.get("username"));
				stmt.setString(2, userMap.get("firstname"));
				stmt.setString(3, userMap.get("lastname"));
				stmt.setString(4, userMap.get("password"));
				inserted = stmt.execute();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new Exception("Exception occurred while inserting the user information ", e);
			}
			finally { 
				return inserted;
      	}
	}

}
