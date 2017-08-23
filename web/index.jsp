<%--
  Created by IntelliJ IDEA.
  User: Erin
  Date: 7/17/2017
  Time: 8:33 PM
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
<div class="forms">
    <form action="addToConferenceRegister" method ="post">
        <fieldset>
            <legend>Contact Information</legend>
            <table>
                <tr>
                    <td><label for="name">Name:</label></td>
                    <td><input type="text" name="name" id="name" value="${attendee.name}"/></td>
                </tr>
                <tr>
                    <td><label for="email">E-mail:</label></td>
                    <td><input type="text" name="email" id="email" value="${attendee.email}"/></td>
                </tr>
            </table>
        </fieldset>



        <br>


        <fieldset>
            <legend>Select Your Courses</legend>
            <select name="classes" size="6" multiple>
                <c:choose>
                    <c:when test="${attendee.classes.contains('A4 - Web Services')}">
                        <option value="A4 - Web Services" selected>A4 - Web Services</option>
                    </c:when>
                    <c:otherwise>
                        <option value="A4 - Web Services">A4 - Web Services</option>
                    </c:otherwise>
                </c:choose>

                <c:choose>
                    <c:when test="${attendee.classes.contains('A1 - J2EE Design Patterns')}">
                        <option value="A1 - J2EE Design Patterns" selected>A1 - J2EE Design Patterns</option>
                    </c:when>
                    <c:otherwise>
                        <option value="A1 - J2EE Design Patterns">A1 - J2EE Design Patterns</option>
                    </c:otherwise>
                </c:choose>

                <c:choose>
                    <c:when test="${attendee.classes.contains('A3 - Service Oriented Architectures')}">
                        <option value="A3 - Service Oriented Architectures" selected>A3 - Service Oriented Architectures</option>
                    </c:when>
                    <c:otherwise>
                        <option value="A3 - Service Oriented Architectures">A3 - Service Oriented Architectures</option>
                    </c:otherwise>
                </c:choose>

                <c:choose>
                    <c:when test="${attendee.classes.contains('A4 - Enterprise Service Bus')}">
                        <option value="A4 - Enterprise Service Bus" selected>A4 - Enterprise Service Bus</option>
                    </c:when>
                    <c:otherwise>
                        <option value="A4 - Enterprise Service Bus">A4 - Enterprise Service Bus</option>
                    </c:otherwise>
                </c:choose>

                <c:choose>
                    <c:when test="${attendee.classes.contains('A6 - Secure Messaging')}">
                        <option value="A6 - Secure Messaging" selected>A6 - Secure Messaging</option>
                    </c:when>
                    <c:otherwise>
                        <option value="A6 - Secure Messaging">A6 - Secure Messaging</option>
                    </c:otherwise>
                </c:choose>

                <c:choose>
                    <c:when test="${attendee.classes.contains('A5 - Web Services Security')}">
                        <option value="A5 - Web Services Security" selected>A5 - Web Services Security</option>
                    </c:when>
                    <c:otherwise>
                        <option value="A5 - Web Services Security">A5 - Web Services Security</option>
                    </c:otherwise>
                </c:choose>
            </select>
        </fieldset>


        <br>


        <fieldset>
            <legend>Employment Status</legend>
            
            <c:choose>
                <c:when test="${attendee.status == 'JHU Employee'}">
                    <input type="radio" name="status" value="JHU Employee" checked/>JHU Employee
                    <input type="radio" name="status" value="JHU Student"/>JHU Student
                    <input type="radio" name="status" value="Speaker"/>Speaker
                    <input type="radio" name="status" value="Other"/>Other
                </c:when>
                <c:when test="${attendee.status == 'JHU Student'}">
                    <input type="radio" name="status" value="JHU Employee"/>JHU Employee
                    <input type="radio" name="status" value="JHU Student" checked/>JHU Student
                    <input type="radio" name="status" value="Speaker"/>Speaker
                    <input type="radio" name="status" value="Other"/>Other
                </c:when>
                <c:when test="${attendee.status == 'Speaker'}">
                    <input type="radio" name="status" value="JHU Employee"/>JHU Employee
                    <input type="radio" name="status" value="JHU Student"/>JHU Student
                    <input type="radio" name="status" value="Speaker" checked/>Speaker
                    <input type="radio" name="status" value="Other"/>Other
                </c:when>
                <c:otherwise>
                    <input type="radio" name="status" value="JHU Employee"/>JHU Employee
                    <input type="radio" name="status" value="JHU Student"/>JHU Student
                    <input type="radio" name="status" value="Speaker"/>Speaker
                    <input type="radio" name="status" value="Other" checked/>Other
                </c:otherwise>
            </c:choose>
        </fieldset>

        <br>


        <fieldset>
            <legend>Additional Fees and Charges</legend>
            <c:choose>
                <c:when test="${attendee.hotel}">
                    <input type="checkbox" name="hotel" value="hotel" checked="checked"/>Hotel Accommodation (Conference Guest Special Fee - Parking Included)</br>
                </c:when>
                <c:otherwise>
                    <input type="checkbox" name="hotel" value="hotel"/>Hotel Accommodation (Conference Guest Special Fee - Parking Included)</br>
                </c:otherwise>
            </c:choose>

            <c:choose>
                <c:when test="${attendee.parking}">
                    <input type="checkbox" name="parking" value="parking" checked="checked"/>Parking Permit
                </c:when>
                <c:otherwise>
                    <input type="checkbox" name="parking" value="parking" />Parking Permit
                </c:otherwise>
            </c:choose>
        </fieldset>


        <br>

        <input class="button" type="submit" value="Compute Seminar Costs">
    </form>

</div>
</body>
</html>

