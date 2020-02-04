package com.ALZoghbi;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*import com.mysql.jdbc.Statement;
import com.mysql.jdbc.log.Log;*/

/**
 * Servlet implementation class DBConnection
 */
@WebServlet("/StoreProducts")
public class StoreProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;

	List<String> sellerName = new ArrayList<>();
	List<String> publisherName = new ArrayList<>();
	List<Integer> Booknumber = new ArrayList<>();
	List<Double> BookPrice = new ArrayList<>();
	public List<Integer> ids = new ArrayList<>();

	GetLibraryAttribute gAttribute = new GetLibraryAttribute();

	SQLiteConnection con = new SQLiteConnection();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StoreProducts() {
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
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("utF-8");

		String query = "INSERT INTO product (name , quantity , price , totalPrice , userid) VALUES (? , ? , ? , ? , ? )";
		String query1 = "SELECT * FROM user ";
		String Query2 = "SELECT * FROM productc ";
		String query3 = "INSERT INTO productc (name , quantity , price) VALUES ( ? , ? , ? )";
		String query4 = "UPDATE productc SET quantity = ? , price = ? where name = ?";

		// System.out.println(gAttribute.getSellerName(request) + " A7A");
		String name = gAttribute.getPublisherName(request).trim();
		int userId = 0;
		double number = gAttribute.getBookNumber(request);
		double price = gAttribute.getBookPrice(request);
		double total = gAttribute.getTotalPrice(request);
		// System.out.println(userId + "\t" + name + "\t" + number + "\t" + price + "\t"
		// + total);
		try {

			Connection connection = con.testConnection();
			java.sql.Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(query1);

			Statement ST = connection.createStatement();
			ResultSet RS = ST.executeQuery(Query2);

			boolean result = false;
			while (RS.next()) {
				if (RS.getString("name").equals(name)) {
					// System.out.println("Ana Hena");
					PreparedStatement pst = connection.prepareStatement(query4);
					pst.setDouble(1, number + RS.getDouble("quantity"));
					pst.setDouble(2, price);
					pst.setString(3, name);

					pst.executeUpdate();

					result = true;
				}
			}

			while (rs.next()) {
				ids.add(rs.getInt("id"));
			}
			userId = ids.get(ids.size() - 1);
			
			PreparedStatement pr = connection.prepareStatement(query);
			pr.setString(1, name);
			pr.setDouble(2, number);
			pr.setDouble(3, price);
			pr.setDouble(4, total);
			pr.setInt(5, userId);

			int id = pr.executeUpdate();

			if (!result) {
				PreparedStatement pr1 = connection.prepareStatement(query3);
				pr1.setString(1, name);
				pr1.setDouble(2, number);
				pr1.setDouble(3, price);

				pr1.executeUpdate();

			}

			CurrentBill cb = new CurrentBill();
			cb.SoutCurrentBill(request, response);
			
/*			String query5 = "SELECT * FROM product WHERE userid = " + userId;

			Statement st2 = connection.createStatement();
			ResultSet rs2 = st2.executeQuery(query5);

			String Data = "<table border = 2 dir = ltr>";
			Data += "<tr>";
			Data += "<th width = 0dp >" + "«·’‰›" + "</th>";
			Data += "<th width = 100dp>" + "«·⁄œœ" + "</th>";
			Data += "<th width = 100dp>" + "«·À„‰ «Ê «·”⁄—" + "</th>";
			Data += "</tr>";
			while (rs2.next()) {
				Data += "<tr>";
				Data += "<form action = DeleteRecord method = post>";
				Data += "<td width = 200dp>" + "<input type = text name = id readonly = readonly value = "
						+ rs2.getString("name") + "></td>";
				Data += "<th width = 100dp>" + rs2.getDouble("quantity") + "</th>";
				Data += "<th width = 100dp>" + rs2.getDouble("price") + "</th>";
				Data += "<th width = 100dp>" + "<input type = submit value = „”Õ /> </th>" + "</form>";
				Data += "</tr>";
			}
			Data += "</table>";

			HttpSession session = request.getSession(true);

			session.setAttribute("Data", Data);

			response.sendRedirect("InsertData2.jsp");*/

			// System.out.println("Welcome");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
