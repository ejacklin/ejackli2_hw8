<%--
  Created by IntelliJ IDEA.
  User: Erin
  Date: 6/15/2017
  Time: 10:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>JHU Annual Software Development Seminar</title>
        <link rel="stylesheet" href="conferences.css">
    </head>
    <body>

        <div class="heading">
            <img src="university.logo_.small_.horizontal.white_.png" alt="JOHNS HOPKINS UNIVERSITY">
        </div>
        <h2 style="padding-left: 140px">JOHNS HOPKINS ANNUAL SOFTWARE DEVELOPMENT SEMINAR</h2>
        <hr width="99%" color="#C4C2C2">
        <br>

        <%--<jsp:useBean id="attendee" scope="session" class = "tracks.Attendee"/>--%>
        <h2>${attendee.name}</h2>

        <p style="padding-left:20px">
            You are registered as a <strong>${attendee.status}</strong>.
            <br><br>
            Your e-mail confirmation will be sent to: <strong>${attendee.email}</strong>
        </p>

    <table class="cTable" style="width:50%">

        <tr>
            <th>Courses</th>
            <th>Cost</th>
        </tr>

        <c:forEach var="c" items="${attendee.classes}" varStatus="loop">
        <tr>
            <td><c:out value="${c}"/></td>
            <td>$ ${attendee.classCost}.00</td>
            <td>
                <form action="addToConferenceRegister" method ="post">
                    <input type="submit" name="button${loop.index}" value="Remove">
                </form>
            </td>
        </tr>
        </c:forEach>

        <tr>
            <td><br></td>
            <td><br></td>
        </tr>
        <c:if test = "${attendee.hotel}">
            <tr>
                <td>Hotel Accommodation (parking included)</td>
                <td>$185.00</td>
            </tr>
        </c:if >

        <c:if test = "${!attendee.hotel && attendee.parking}" >
        <tr>
            <td>Parking</td>
            <td>$10.00</td>
        </tr>
        </c:if>

        <tr>
            <td><strong>Total</strong></td>
            <td><strong>$${attendee.totalCost}.00</strong></td>

        </tr>
    </table>

    <br>
        <table style="width:50%">
            <tr>
                <td>
                    <form action="/index.jsp" method="post">
                        <input type="submit" value="Edit Information">
                    </form>
                </td>
                <td>
                    <form action="/index.jsp" method="post">
                        <input type="submit" value="Add More Courses">
                    </form>
                </td>
                <td>
                    <form action="/checkout.jsp" method ="post">
                        <input type="submit" value="Check Out">
                    </form>
                </td>
                <%--<td>--%>
                    <%--<form action="registerConfirmation" method ="post">--%>
                        <%--<input type="submit" value="Confirm Registration">--%>
                    <%--</form>--%>
                <%--</td>--%>
            </tr>
        </table>
    </body>
</html>
