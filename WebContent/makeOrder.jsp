<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="windows-1256"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<br><br>
	<form action = "MakeOrder" method = "post" dir="rtl">
		<table>
			<tr>
				<td>
					<input type="text" name = "publishername" placeholder = "ÇÓã ÏÇÑ ÇáäÔÑ" id = "publishing"/>
				</td>
				
				<td>
					<input type="number" name = "number" placeholder = "ÇáÚÏÏ" id = "number"/>
				</td>
				
				<td></td> <td></td>
				<td>
					<input type = "submit" value = "ÇÖÇİÉ">
				</td>
			</tr>
		</table>
	</form>
	<br><br><br>
	<%
		String Data = request.getSession().getAttribute("Data").toString();
		out.print(Data); 
	%>
	
	
</body>
</html>