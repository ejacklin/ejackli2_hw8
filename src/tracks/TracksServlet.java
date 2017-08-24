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
import java.io.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;



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
        String pdf = request.getParameter("printPDF");
        String excel = request.getParameter("printExcel");
        if(endSession != null){
            request.getSession().invalidate();
            response.sendRedirect(request.getContextPath() + "/index.html");
        }else if(pdf != null){

            response.setContentType("application/pdf");
            Document document = new Document();
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            try {
                PdfWriter.getInstance(document, buffer);
            } catch (DocumentException e) {
                e.printStackTrace();
            }

            document.open();



            try {
                document.add(new Paragraph("JOHNS HOPKINS ANNUAL SOFTWARE DEVELOPMENT SEMINAR"));
                document.add(Chunk.NEWLINE);
                document.add(new Chunk(attendee.getName()));
                document.add(Chunk.NEWLINE);
                document.add(Chunk.NEWLINE);
                document.add(new Chunk("You are registered for the following courses as a " + attendee.getStatus() + ":"));
                document.add(Chunk.NEWLINE);
                PdfPTable table = new PdfPTable(2);
                table.addCell("Courses");
                table.addCell("Cost");
                for(int aw = 0; aw < attendee.getClasses().size(); aw++){
                    table.addCell(attendee.getClasses().get(aw).toString());
                    table.addCell("$" + attendee.getClassCost().toString());
                }
                if(attendee.getHotel()){
                    table.addCell("Hotel Accomodation");
                    table.addCell("$185");
                }else if(attendee.getParking()){
                    table.addCell("Parking Fee");
                    table.addCell("$10");
                }

                table.addCell("Total:");
                table.addCell("$" + attendee.getTotalCost().toString());

                document.add(table);




            } catch (DocumentException e) {
                e.printStackTrace();
            }
            document.close();
            DataOutput output = new DataOutputStream(response.getOutputStream());
            byte[] bytes = buffer.toByteArray();
            response.setContentLength(bytes.length);
            for (int i = 0; i < bytes.length; i++)
            {
                output.writeByte(bytes[i]);
            }
        }else if(excel != null){
            OutputStream out = null;
            try
            {

                response.setContentType("application/vnd.ms-excel");

                response.setHeader("Content-Disposition",
                        "attachment; filename=Registration.xls");

                WritableWorkbook w = Workbook.createWorkbook(response.getOutputStream());
                WritableSheet s = w.createSheet("Courses", 0);

                s.addCell(new Label(0, 0, "Hello World"));


                s.addCell(new Label(0, 0,"JOHNS HOPKINS ANNUAL SOFTWARE DEVELOPMENT SEMINAR"));

                s.addCell(new Label(0, 2,attendee.getName()));


                s.addCell(new Label(0, 4,"You are registered for the following courses as a " + attendee.getStatus() + ":"));


                s.addCell(new Label(0, 4,"Courses"));
                s.addCell(new Label(1, 4,"Cost"));


                int row = 5;
                for(int aw = 0; aw < attendee.getClasses().size(); aw++) {

                    s.addCell(new Label(0,row+aw,attendee.getClasses().get(aw).toString()));
                    s.addCell(new Label(1,row+aw,"$" + attendee.getClassCost().toString()));
                }

                row = row + attendee.getClasses().size() +1;
                if(attendee.getHotel()){
                    s.addCell(new Label(0,row,"Hotel Accomodation"));
                    s.addCell(new Label(1,row,"$185"));
                }else if(attendee.getParking()){
                    s.addCell(new Label(0,row,"Parking Fee"));
                    s.addCell(new Label(1,row,"$10"));
                }

                s.addCell(new Label(0,row + 2,"Total:"));
                s.addCell(new Label(1,row + 2,"$" + attendee.getTotalCost().toString()));


                w.write();
                w.close();

            } catch (Exception e)
            {
                throw new ServletException("Exception in Excel Sample Servlet", e);
            } finally
            {
                if (out != null)
                    out.close();
            }
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
