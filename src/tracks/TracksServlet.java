/**
 * Created by Erin on 7/3/2017.
 */
package tracks;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class TracksServlet extends HttpServlet {

    public TracksServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Declare variables
        Boolean hotel;
        Boolean parking;
        ArrayList<String> classes;
        String buttonPressed;
        Boolean pressed = false;
        String url;

        HttpSession session = request.getSession(true);

        Attendee attendee = (Attendee) session.getAttribute("attendee");

        String endSession = request.getParameter("endSession");
        if(endSession != null){
            request.getSession().invalidate();
            response.sendRedirect(request.getContextPath() + "/index.html");
        }else{

            //check if attendee exists and remove button pressed
            if(attendee != null){
                for(int i= 0; i<attendee.getClasses().size(); i++){
                    buttonPressed = request.getParameter("button" + i);
                    if(buttonPressed !=null){
                        attendee.RemoveClass(i);
                        pressed = true;

                    }
                }
            }else{
                attendee = new Attendee();
            }
            url = "/results.jsp";

            if(!pressed) {
                //get parameters from the request
                String nameInput = request.getParameter("name");
                String emailInput = request.getParameter("email");
                String[] classesInput = request.getParameterValues("classes");
                String hotelInput = request.getParameter("hotel");
                String parkingInput = request.getParameter("parking");
                String statusInput = request.getParameter("status");

                ArrayList<String> message = new ArrayList<>();

                if (nameInput.length() == 0) {
                    message.add("Name Required!");
                }
                if (emailInput.length() == 0) {
                    message.add("Email Required!");
                }
                if (classesInput == null) {
                    message.add("Must choose at least 1 class to register for!");
                }

                if (!message.isEmpty()) {
                    String[] errors = message.toArray(new String[message.size()]);
                    request.setAttribute("messages", errors);
                    url = "/error.jsp";
                }

                //change from string to boolean
                hotel = hotelInput != null;
                parking = parkingInput != null;

                classes = new ArrayList<>(Arrays.asList(classesInput));

                attendee.setName(nameInput);
                attendee.setEmail(emailInput);
                attendee.setStatus(statusInput);
                attendee.setClasses(classes);
                attendee.setHotel(hotel);
                attendee.setParking(parking);
            }

            session.setAttribute("attendee",attendee);

            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(url);
            requestDispatcher.forward(request, response);
        }
    }


}
