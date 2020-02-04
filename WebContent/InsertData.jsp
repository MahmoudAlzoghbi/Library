<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert Your Reset</title>
</head>
<body>
	<%-- <%
		String name = request.getSession().getAttribute("name").toString();
	%> --%>
	
	<br><br><br>
	<form action="StoreSellerName" method="post" dir="rtl">
		
		<table>
			<tr>
				<td>
					<input type="text" name = "sellername" placeholder= "اسم البائع" id = "name"/>
				</td>
				<td></td>
				<td>
					<input type="submit" value = "اضافة اسم البائع">
				</td>
			</tr>
		</table>
	</form>
	
	</br> </br>
	
	<form action="StoreProducts" method="post" dir="rtl">	
		<table>
			<tr>
				<td>
					<input type="text" name = "publishername" placeholder = "اسم دار النشر" id = "publishing"/>
				</td>
				
				<td>
					<input type="number" name = "number" placeholder = "العدد" id = "number"/>
				</td>
				
				<td>
					<input type="number" name = "price" placeholder = "الثمن او الفئة" id = "price"/>
				</td>
				<td></td> <td></td>
				<td>
					<input type = "submit" value = "اضافه">
				</td>
			</tr>
		</table>
	</form>	
	
	</br> </br>
	
	<%-- <form >
		<table>
			<tr>
				<td>
					<input type="text" value = "${name}" name = "dbsellername" />
				</td>
				<td></td>
			</tr>
		</table>
	</form> --%>
	<table dir="rtl">
		<tr>
			<th>
				<form action="homePage.jsp">
					<input type="submit" value = "الرجوع الى القائمه الرئيسيه" />
				</form>
			</th>
		</tr>
	</table>
	
</body>
</html>