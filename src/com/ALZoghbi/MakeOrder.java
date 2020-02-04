package com.ALZoghbi;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MakeOrder
 */
@WebServlet("/MakeOrder")
public class MakeOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MakeOrder() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
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

		String name = request.getParameter("publishername");
		double number = Integer.parseInt(request.getParameter("number"));

		String query = "SELECT * FROM productc";
		String query2 = "UPDATE productc SET quantity = ? where name = ?";
		String query3 = "INSERT INTO selling (name , number , price ) VALUES ( ? , ? , ? )";
		String query4 = "SELECT * FROM selling";
		String query5 = "SELECT price FROM productc WHERE name = " + name;

		try {
			SQLiteConnection con = new SQLiteConnection();
			Connection connection = con.testConnection();
			double result = 0;
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(query);
			String output = "";
			while (rs.next()) {
				if (rs.getString("name").equals(name) && rs.getDouble("quantity") >= number) {
					// System.out.println(rs.getDouble("price"));
					// result = rs.getDouble("price");
					PreparedStatement pst = connection.prepareStatement(query2);
					pst.setDouble(1, rs.getDouble("quantity") - number);
					pst.setString(2, name);

					pst.executeUpdate();

					PreparedStatement orderpst = connection.prepareStatement(query3);
					orderpst.setString(1, name);
					orderpst.setDouble(2, number);
					orderpst.setDouble(3, rs.getDouble("price"));
					orderpst.executeUpdate();
					System.out.println(result);
					ItemsToSell item = new ItemsToSell();
					output = item.ShowData(request, response);
					// System.out.println(output);

					output += "</br> </br>";
				}
			}
			/*
			 * Statement selectPrice = connection.createStatement(); ResultSet rsPrice =
			 * selectPrice.executeQuery(query5);
			 */
			Statement Ostate = connection.createStatement();
			ResultSet RS = Ostate.executeQuery(query4);

			output += "<table border = 1 align = center>";
			/*output += "<form method = post >";*/
			while (RS.next()) {
				if (RS.getString("ordered").equals("false")) {
					output += "<tr>";
					output += "<td width = 200px>" + RS.getString("name") + "</td>";
					output += "<td width = 100px>" + RS.getDouble("number") + "</td>";
					output += "<td width = 100px>" + RS.getDouble("price") + "</td>";
					output += "</tr>";
					result += RS.getDouble("price");
				}
			}
			output += "<tr><td width = 200px>" + result + "</td></tr>";
			output += "<tr align = center>";
			output += " <th><form action = ConfirmBill method = post>"
					+ "<input type = submit name = ÊäÝíÐ ÇáÚãáíÉ /></form></th></tr>";
			output += "</table>";

			HttpSession session = request.getSession(true);
			session.setAttribute("Data", output);
			response.sendRedirect("makeOrder.jsp");

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}

}
