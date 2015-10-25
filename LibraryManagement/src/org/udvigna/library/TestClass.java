package org.udvigna.library;

public class TestClass {

	public static void main(String[] args) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("Driver found");
			} catch (ClassNotFoundException e) {
				System.out.println("driver not found");
				e.printStackTrace();
			}

	}

}
