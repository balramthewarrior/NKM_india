package com.foodordering;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("/nkm")
public class ToDo_nkmIndia {

	@Path("/insert")
	@POST
	@Produces({ "application/json" })
	@Consumes({ "application/json" })
	public String insert(@QueryParam("text") String text) {
		try {
			// Connect to the PostgreSQL database
			Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mydb", "postgres",
					"Rama");

			// Create a statement
			Statement statement = connection.createStatement();

			// Insert the text into the database
			statement.executeUpdate("INSERT INTO text (text) VALUES (?)");

			// Close the connection
			connection.close();

			// Return success message
			return "Text inserted successfully";
		} catch (SQLException e) {
			// Return error message
			return "Error inserting text: " + e.getMessage();
		}
	}

	@Path("/delete")
	@POST
	@Produces({ "application/json" })
	@Consumes({ "application/json" })
	public String delete(@QueryParam("id") int id) {
		try {
			// Connect to the PostgreSQL database
			Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mydb", "postgres",
					"password");

			// Create a statement
			Statement statement = connection.createStatement();

			// Delete the text from the database
			statement.executeUpdate("DELETE FROM text WHERE id = ?", id);

			// Close the connection
			connection.close();

			// Return success message
			return "Text deleted successfully";
		} catch (SQLException e) {
			// Return error message
			return "Error deleting text: " + e.getMessage();
		}
	}

}
