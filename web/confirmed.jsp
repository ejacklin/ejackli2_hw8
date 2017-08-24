<%--
  Created by IntelliJ IDEA.
  User: Erin
  Date: 7/17/2017
  Time: 9:17 PM
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

<h1 style="padding-left: 140px">Your registration has been confirmed</h1>
<br>
<p style="padding-left: 140px">An email has been sent to: <a href="mailto:${attendee.email}">${attendee.email}</a></p>

<p style="padding-left: 140px">If you do not receive the e-mail confirmation or you need to update your
registration information, please contact the conference committee at
<a href="mailto:registration@seminar.jhu.edu">registration@seminar.jhu.edu</a>
    or at (410) 410-4100.</p>


    <form action="addToConferenceRegister" method ="post">
        <table style="width:50%; padding-left: 140px">
            <tr>
                <form action="addToConferenceRegister" method ="post">
                <td> <input type="submit" value="Done" name="endSession"></td>
                </form>

                <form action="addToConferenceRegister" method ="post">
                <td> <input type="submit" value="Print Confirmation in PDF" name="printPDF"></td>
                </form>

                <form action="addToConferenceRegister" method ="post">
                <td> <input type="submit" value="Print Confirmation in Excel" name="printExcel"></td>
                </form>
            </tr>
        </table>

    </form>

</body>
</html>
