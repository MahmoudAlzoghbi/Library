package com.ALZoghbi;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.sqlite.SQLiteException;

/**
 * Servlet implementation class StoreSellerName
 */
@WebServlet("/StoreSellerName")
public class StoreSellerName extends HttpServlet {
	private static final long serialVersionUID = 1L;
    GetLibraryAttribute gAttribute = new GetLibraryAttribute();
  
    public List<Integer> ids = new ArrayList<>();
        /**
     * @see HttpServlet#HttpServlet()
     */
    public StoreSellerName() {
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
		
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String sellername = gAttribute.getSellerName(request);
		//System.out.println(sellername);

		PrintWriter out = response.getWriter();
		
		SQLiteConnection con = new SQLiteConnection();
		
		String query = "INSERT INTO user (name) VALUES (?)";
		String query1 = "SELECT * FROM user " ;
		
		try {
			Connection connection = con.testConnection();
			
			PreparedStatement pr = connection.prepareStatement(query);
			pr.setString(1, sellername);
			
			int id = pr.executeUpdate();
			
			//System.out.println(id + " Ana Hena");
			
			java.sql.Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(query1);
			
			while(rs.next()) {
				if (rs.getString("name").equals(sellername)) {
					ids.add(rs.getInt("id"));
					//System.out.print(" id : " + rs.getInt("id")+ "\t");
					/*System.out.print(" name : " + rs.getString("name")+"\t");
					System.out.println(" Created_at : " + rs.getString("created_at"));*/
				}
			//System.out.println(gAttribute.getUserId() + "Signature Mahmoud Al-Zoghbi");
			}
			//System.out.println(ids.get(ids.size()-1) + " \t" + "A5er 7ad ");
			
			response.sendRedirect("InsertData.jsp");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//response.sendRedirect("InsertData.jsp");
		
	}
}
