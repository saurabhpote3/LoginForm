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



@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		try {
			MysqlDataSource ds = new MysqlDataSource();
			ds.setUser("root");
			ds.setPassword("root");
			ds.setServerName("localhost");
			ds.setPortNumber(3306);
			ds.setDatabaseName("db");
			ds.setUseSSL(false);

			try (Connection conn = ds.getConnection();
					PreparedStatement st = conn
							.prepareStatement("SELECT email FROM login_user WHERE email = ? AND password = ?")) {

				st.setString(1, email);
				st.setString(2, password);

				try (ResultSet row = st.executeQuery()) {
					if (row.next()) {
						PrintWriter out = response.getWriter();

						out.println("Login Success");
					} else {
						PrintWriter out = response.getWriter();
						out.println("Login Failed");
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
