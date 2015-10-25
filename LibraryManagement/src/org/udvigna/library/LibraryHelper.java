package org.udvigna.library;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

public interface LibraryHelper {
	
	boolean submitSignupForm(Map<String, String> userMap, Connection conn);

}
