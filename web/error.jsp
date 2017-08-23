<%--
  Created by IntelliJ IDEA.
  User: Erin
  Date: 7/4/2017
  Time: 9:01 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ERROR: JHU Annual Software Development Seminar</title>
    <link rel="stylesheet" href="conferences.css">
</head>
<body>

<div class="heading">
    <img src="university.logo_.small_.horizontal.white_.png" alt="JOHNS HOPKINS UNIVERSITY">
</div>
<h2 style="padding-left: 140px">JOHNS HOPKINS ANNUAL SOFTWARE DEVELOPMENT SEMINAR</h2>
<hr width="99%" color="#C4C2C2">
<br>

<% String[] errors = (String[]) request.getAttribute("messages");
    for( String e: errors){
%>
<p class="error"><%=e%></p>
<%}%>


<%
    String button = request.getParameter("confirm");
    if(button == null){
%>
<form action="index.html" method="post">
    <input type="submit" value="Back">
</form>

<%}else{ %>
<form action="checkout.jsp" method="post">
    <input type="submit" value="Back">
</form>
<%}%>

</body>
</html>
