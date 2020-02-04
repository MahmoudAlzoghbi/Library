package com.ALZoghbi;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CurrentBill {

	public SQLiteConnection con = new SQLiteConnection();

	public void SoutCurrentBill(HttpServletRequest request, HttpServletResponse response) {

		String query1 = "SELECT * FROM user ";
		try {

			Connection connection = con.testConnection();
			java.sql.Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(query1);

			List<Integer> ids = new ArrayList<>();
			while (rs.next()) {
				ids.add(rs.getInt("id"));
			}
			int userId = ids.get(ids.size() - 1);

			String query5 = "SELECT * FROM product WHERE userid = " + userId;

			Statement st2 = connection.createStatement();
			ResultSet rs2 = st2.executeQuery(query5);

			String Data = "<table border = 2 dir = ltr>";
			Data += "<tr>";
			Data += "<th width = 300dp >" + "«·’‰›" + "</th>";
			Data += "<th width = 100dp>" + "«·⁄œœ" + "</th>";
			Data += "<th width = 100dp>" + "«·À„‰ «Ê «·”⁄—" + "</th>";
			Data += "</tr>";
			while (rs2.next()) {
				//System.out.println(rs2.getString("name"));
				if (rs2.getString("deleted").equals("true")) {
					continue;
				}
				Data += "<tr>";
				Data += "<form action = DeleteRecord method = post>";
				Data += "<td width = 300dp>"
						+ "<textarea name = id dir = rtl readonly = readonly rows = 1 cols = 35> "
						+ rs2.getString("name") + "</textarea></td>";
				Data += "<th width = 100dp>" + rs2.getDouble("quantity") + "</th>";
				Data += "<th width = 100dp>" + rs2.getDouble("price") + "</th>";
				Data += "<th width = 100dp>" + "<input type = submit value = „”Õ /> </th>" + "</form>";
				Data += "</tr>";
			}
			Data += "</table>";

			HttpSession session = request.getSession(true);

			session.setAttribute("Data", Data);

			response.sendRedirect("InsertData2.jsp");

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

	}

}
