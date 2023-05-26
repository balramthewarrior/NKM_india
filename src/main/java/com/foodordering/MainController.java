package com.foodordering;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class MainController {

//	public static void main(String[] args) throws SQLException {
//		MainController mc = new MainController();
//		mc.deleteFromTable("element1");
//	}

	@PostMapping("/insert")
	public String insertIntoTable(@RequestBody String data) throws SQLException {

		// Connect to the PostgreSQL database
		Connection connection = null;
		try {
			System.out.println(data);
			String text = new JSONObject(data).getString("text");
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/nkm", "postgres", "Rama");

			// Create a prepared statement
			PreparedStatement statement = connection.prepareStatement("INSERT INTO nkm VALUES (?)");

			// Set the parameter value
			statement.setString(1, text);

			// Execute the statement
			statement.executeUpdate();

			// Close the connection
			connection.close();
			System.out.println("inserted");
			return "inserted";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			connection.close();
			e.printStackTrace();
			System.out.println("not inserted");
			return "not inserted";
		}
	}

	@DeleteMapping("/delete/{text}")
	public String deleteFromTable(@PathVariable("text") String text) throws SQLException {

		// Connect to the PostgreSQL database
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/nkm", "postgres", "Rama");

			// Create a prepared statement
			PreparedStatement statement = connection.prepareStatement("DELETE FROM nkm WHERE 'text'=(?)");
			System.out.println(statement + text);
			// Set the parameter value
			statement.setString(1, text);
			System.out.println(statement + text);
			// Execute the statement
			statement.executeUpdate();

			// Close the connection
			connection.close();
			System.out.println("deleted");
			return "deleted";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			connection.close();
			System.out.println("not deleted");
			return "not deleted";
		}
	}

}
