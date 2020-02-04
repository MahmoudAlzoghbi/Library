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
 * Servlet implementation class ConfirmBill
 */
@WebServlet("/ConfirmBill")
public class ConfirmBill extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmBill() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("utF-8");
		
		String query = "SELECT * FROM selling";
		String query2 = "UPDATE selling SET ordered = ? WHERE name = ?";
		
		try {
			SQLiteConnection con = new SQLiteConnection();
			Connection connection = con.testConnection();
			
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while (rs.next()) {
				if (rs.getString("ordered").equals("false")) {
					PreparedStatement pst = connection.prepareStatement(query2);
					pst.setString(1, "true");
					pst.setString(2, rs.getString("name"));
					
					pst.executeUpdate();
				}
			}
			
			ItemsToSell item = new ItemsToSell();
			String output = item.ShowData(request, response);
			
			HttpSession session = request.getSession(true);
			session.setAttribute("Data", output);
			response.sendRedirect("makeOrder.jsp");
			
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}

}
