package com.ALZoghbi;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TrackStore
 */
@WebServlet("/TrackStore")
public class TrackStore extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SQLiteConnection con = new SQLiteConnection();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TrackStore() {
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
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();

		String query = "SELECT * FROM productc";

		Connection connection = con.testConnection();
		List<String> publisherName = new ArrayList<>();
		List<Double> Booknumber = new ArrayList<>();
		List<String> UniqueName = new ArrayList<>();
		List<Double> UniqueNumber = new ArrayList<>();

		try {

			Statement st = connection.createStatement();
			ResultSet RS = st.executeQuery(query);

			while (RS.next()) {
				if (RS.getString("deleted").equals("true") || RS.getDouble("quantity") <= 0) {
					continue;
				}
				publisherName.add(RS.getString("name"));
				Booknumber.add(RS.getDouble("quantity"));
			}
			if (publisherName.isEmpty() || Booknumber.isEmpty()) {
				printBtnBack(out);
			} else {
				// get The Unique Name Of Books
				UniqueName.add(publisherName.get(0));

				for (int i = 1; i < publisherName.size(); i++) {
					if (!UniqueName.contains(publisherName.get(i))) {
						UniqueName.add(publisherName.get(i));
					}
				}

				// get The Unique Number for Each Book Name
				double numbers = 0;
				for (int i = 0; i < UniqueName.size(); i++) {
					for (int j = 0; j < publisherName.size(); j++) {

						if (UniqueName.get(i).equals(publisherName.get(j))) {
							numbers += Booknumber.get(j);
						}
					}
					UniqueNumber.add(numbers);
					numbers = 0;
				}

				String output = "<html>";
				output += "<table border = 2>";
				output += "<tr>";
				output += "<th width = 400dp>" + "«·’‰›" + "</th>";
				output += "<th width = 300dp>" + "«·⁄œœ" + "</th>";
				output += "</tr>";
				output += "</table>";
				output += "</html>";
				out.print(output);

				for (int i = 0; i < UniqueName.size(); i++) {
					output = "<html>";
					output += "<table border = 2>";
					output += "<tr>";
					output += "<td width = 400dp " + " dir = " + "ltr" + ">" + publisherName.get(i) + "</td>";
					output += "<td width = 300dp dir = ltr>" + Booknumber.get(i) + "</td>";
					output += "</tr>";
					output += "</table>";
					output += "</html>";
					out.print(output);
				}

				printBtnBack(out);
				/*
				 * out.print("</br>" + "</br>"); output = "<html>"; output += "<form action = "
				 * + "homePage.jsp" + " dir= " + " rtl " + " >"; output += "<tr>"; output +=
				 * "<th>"; output += "<input type = submit value = —ÃÊ⁄ «·Ï «·’›Õ… «·—∆Ì”Ì…/>";
				 * output += "</th>"; output += "</tr>"; output += "</form>"; output +=
				 * "</html>"; out.print(output);
				 */

			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}

	public void printBtnBack(PrintWriter out) {

		out.print("</br>" + "</br>");
		String output = "<html>";
		output += "<form action = " + "homePage.jsp" + " dir= " + " rtl " + " >";
		output += "<tr>";
		output += "<th>";
		output += "<input type = submit value = —ÃÊ⁄ «·Ï «·’›Õ… «·—∆Ì”Ì…/>";
		output += "</th>";
		output += "</tr>";
		output += "</form>";
		output += "</html>";
		out.print(output);
	}

}
