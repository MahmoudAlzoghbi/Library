package com.ALZoghbi;

import java.beans.Statement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.OutputBuffer;

/**
 * Servlet implementation class SeeAllBills
 */
@WebServlet("/SeeAllBills")
public class SeeAllBills extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SQLiteConnection con = new SQLiteConnection();
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SeeAllBills() {
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

		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		List<String> sellerName = new ArrayList<>();
		List<String> created_at = new ArrayList<>();
		List<String> publisherName = new ArrayList<>();
		List<Double> Booknumber = new ArrayList<>();
		List<Double> BookPrice = new ArrayList<>();
		List<Double> TotalPrice = new ArrayList<>();
		List<Integer> ids = new ArrayList<>();
		List<Integer> userId = new ArrayList<>();
		String output;
		PrintWriter out = response.getWriter();

		String query1 = "SELECT * FROM user";
		String query2 = "SELECT * FROM product";

		Connection connection = con.testConnection();

		try {

			java.sql.Statement st = connection.createStatement();
			ResultSet RS = st.executeQuery(query1);

			java.sql.Statement stat = connection.createStatement();
			ResultSet rs = stat.executeQuery(query2);
			while (RS.next()) {
				ids.add(RS.getInt("id"));
				sellerName.add(RS.getString("name"));
				created_at.add(RS.getString("created_at"));
			}

			while (rs.next()) {
				if (rs.getString("deleted").equals("true")) {
					continue;
				}
				
				publisherName.add(rs.getString("name"));
				Booknumber.add(rs.getDouble("quantity"));
				BookPrice.add(rs.getDouble("price"));
				TotalPrice.add(rs.getDouble("totalPrice"));
				userId.add(rs.getInt("userid"));
			}

			for (int i = 0; i < ids.size(); i++) {
				double totalPrice = 0.0;
				output = "<html>";
				output += "<table border = 2>";
				output += "<tr>" + "<th width = 200dp>" + sellerName.get(i) + "</th>" + "<th width = 200dp>"
						+ created_at.get(i) + "</th> <th> </th>" + "</tr>";
				output += "<tr>";
				output += "<th width = 300dp>" + "«·’‰›" + "</th>";
				output += "<th width = 300dp>" + "«·⁄œœ" + "</th>";
				output += "<th width = 300dp>" + "«·”⁄—" + "</th>";
				output += "</tr>";
				output += "</table>";
				output += "</html>";
				out.print(output);
				for (int j = 0; j < userId.size(); j++) {
					if (ids.get(i) == userId.get(j)) {
						output = "<html>";
						output += "<table border = 2>";
						output += "<tr>";
						output += "<td width = 300dp>" + publisherName.get(j) + "</td>";
						output += "<td width = 300dp>" + Booknumber.get(j) + "</td>";
						output += "<td width = 300dp>" + BookPrice.get(j) + "</td>";
						output += "</tr>";
						output += "</table>";
						output += "</html>";
						out.print(output);

						totalPrice += (Booknumber.get(j) * BookPrice.get(j));
					}
				}
				output = "<html>";
				output += "<table border = 2>";
				output += "<tr>";
				output += "<th width = 300dp>" + "«·«Ã„«·Ï" + "   " + totalPrice;
				output += "</tr>";
				output += "</table>";
				output += "</html>";
				out.print(output);
				out.print("<br>" + "<br>");
			}
			out.print("<br>" + "<br>");
			output = "<html>";
			output += "<form action = " + "homePage.jsp" + " dir= " + " rtl " + " >";
			output += "<tr>";
			output += "<th>";
			output += "<input type = submit value = "+"—ÃÊ⁄ «·Ï «·’›Õ… «·—∆Ì”Ì… "+"/>";
			output += "</th>";
			output += "</tr>";
			output += "</form>";
			output += "</html>";
			
			out.print(output);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
