package com.ALZoghbi;

import javax.servlet.http.HttpServletRequest;

public class GetLibraryAttribute {
	
	int userId;
	String SellerName;
	String created_at;
	String PublisherName;
	double BookNumber;
	double BookPrice;
	double TotalPrice;
	
	public GetLibraryAttribute() {
	}
	
	public GetLibraryAttribute(int id , String name , String created_at) {
		this.userId = id;
		this.SellerName = name;
		this.created_at = created_at;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public int getUserId() {
		return userId;
	}
	
	
	public String getSellerName(HttpServletRequest req) {
		
		SellerName = req.getParameter("sellername");
		//System.out.println(SellerName + "gggggggggg");
		return SellerName;
	}
	
	public String getPublisherName(HttpServletRequest req) {
		PublisherName = req.getParameter("publishername");
		return PublisherName;
	}

	public double getBookNumber(HttpServletRequest req) {
		BookNumber = Integer.parseInt(req.getParameter("number"));
		return BookNumber;
	}

	public double getBookPrice(HttpServletRequest req) {
		BookPrice = Integer.parseInt(req.getParameter("price"));
		return BookPrice;
	}

	public double getTotalPrice(HttpServletRequest req) {
		
		TotalPrice = getBookNumber(req) *getBookPrice(req);
		return TotalPrice;
	}

}
