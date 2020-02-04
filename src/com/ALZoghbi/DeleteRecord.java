package com.ALZoghbi;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class DeleteRecord
 */
@WebServlet("/DeleteRecord")
public class DeleteRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteRecord() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * // TODO Auto-generated method stub
		 * response.getWriter().append("Served at: ").append(request.getContextPath());
		 */
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("utF-8");

		PrintWriter out = response.getWriter();

		String name = request.getParameter("id").trim();
		//System.out.println(name);
		String query1 = "UPDATE product SET deleted = ? WHERE name = ?";
		String query2 = "UPDATE productc SET deleted = ? WHERE name = ?";

		try {
			SQLiteConnection con = new SQLiteConnection();

			Connection connection = con.testConnection();

			PreparedStatement pst = connection.prepareStatement(query1);

			pst.setString(1, "true");
			pst.setString(2, name);

			pst.executeUpdate();

			PreparedStatement pst1 = connection.prepareStatement(query2);

			pst1.setString(1, "true");
			pst1.setString(2, name);

			pst1.executeUpdate();
			
			CurrentBill cb = new CurrentBill();
			
			cb.SoutCurrentBill(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

	}

}
