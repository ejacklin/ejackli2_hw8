package tracks;

import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Erin on 7/29/2017.
 */
public class EmailServlet extends HttpServlet {

    public EmailServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(true);


            Attendee attendee = (Attendee) session.getAttribute("attendee");
            ArrayList classes = attendee.getClasses();

            String to = attendee.getEmail();
            String from = "ejackli2hw6@gmail.com";
            String subject = "Software Development Seminar Registration Confirmation";
            String body = "<html>"
                    + "<head>"
                    + "<h1 style=\"padding-left: 140px\">JOHNS HOPKINS ANNUAL SOFTWARE DEVELOPMENT SEMINAR</h1>"
                    + "<hr width=\"99%\" color=\"#C4C2C2\"></hr><br>"
                    + "</head>"
                    + "<body>"
                    + "<h3>" + attendee.getName() + "</h3><br>"
                    + "You have been confirmed for the Johns Hopkins Annual Software Development Seminar.<br>"
                    + "You are registered as a <b>" + attendee.getStatus() + ".</b><br><br>"
                    + "If you need to update your registration information, please contact the conference committee at <br>"
                    + "<a href=\"mailto:registration@seminar.jhu.edu\">registration@seminar.jhu.edu</a>" +
                    "    or at (410) 410-4100.<br><br>"
                    + "  <table class=\"cTable\" style=\"width:50%\">" +
                    "        <tr>" +
                    "            <th>Courses</th>" +
                    "            <th>Cost</th>" +
                    "        </tr>";
            for (int i = 0; i < attendee.getClasses().size(); i++) {
                body += "<tr valign=\"middle\"><td>" + classes.get(i) + "</td>"
                        + "<td>$" + attendee.getClassCost() + ".00</td></tr>";
            }

            body += "<tr> </tr>";
            if (attendee.getHotel()) {
                body += "<tr valign=\"middle\">"
                        + "<td>Hotel Accommodation (parking included)</td>" +
                        "  <td>$185.00</td>" +
                        "</tr>";
            }
            if (!attendee.getHotel() && attendee.getParking()) {
                body += "        <tr valign=\"middle\">" +
                        "            <td>Parking</td>" +
                        "            <td>$10.00</td>" +
                        "        </tr>";
            }

            body += "        <tr valign=\"middle\">" +
                    "            <td><strong>Total</strong></td>" +
                    "            <td><strong>$" + attendee.getTotalCost() + ".00</strong></td>" +
                    "        </tr>" +
                    "    </table>"
                    + "</body>"
                    + "</html>";


            try {
                MailUtilGmail.sendMail(to, from, subject, body, true);
            } catch (MessagingException e) {
                System.out.println("ERROR: Unable to send email." + "\n"
                        + "ERROR MESSAGE: " + e.getMessage());
            }
              String url = "/confirmed.jsp";


        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(url);
        requestDispatcher.forward(req, resp);

    }
}
