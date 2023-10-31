package com.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.jdbc.MysqlDataSource;

// Import statements...

@WebServlet("/CreateUserServlet")
public class CreateUserServlet extends HttpServlet  {
	private static final long serialVersionUID = 1L;
	MysqlDataSource dataSource = new MysqlDataSource();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		dataSource.setUser("root");
		dataSource.setPassword("root");
		dataSource.setDatabaseName("db");
		dataSource.setPortNumber(3306);
		dataSource.setServerName("localhost");
		try {
			dataSource.setUseSSL(false);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		try (Connection conn = dataSource.getConnection()) {
			if (userExists(conn, email)) {
				PrintWriter out = response.getWriter();
				out.println("Already user existed");
			} else {
				insertUser(conn, name, email, password);

				PrintWriter out = response.getWriter();
				out.println("User created successfully");
			}
		} catch (SQLException e) {
			e.printStackTrace(); // Log the exception properly using a logging framework
		}
	}

	private boolean userExists(Connection conn, String email) throws SQLException {
		String query = "SELECT name FROM login_user WHERE email=?";
		try (PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setString(1, email);
			try (ResultSet rs = stmt.executeQuery()) {
				return rs.next();
			}
		}
	}

	private void insertUser(Connection conn, String name, String email, String password) throws SQLException {
		String query = "INSERT INTO login_user (name, email, password) VALUES (?, ?, ?)";
		try (PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setString(1, name);
			stmt.setString(2, email);
			stmt.setString(3, password);
			stmt.executeUpdate();
		}
	}
}
