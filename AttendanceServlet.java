import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AttendanceServlet extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String name = request.getParameter("studentName");
    String rollNo = request.getParameter("rollNo");
    String date = request.getParameter("date");
    String status = request.getParameter("status");

    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/yourDB", "root", "password");

      PreparedStatement ps = con.prepareStatement("INSERT INTO attendance(name, roll_no, date, status) VALUES(?, ?, ?, ?)");
      ps.setString(1, name);
      ps.setString(2, rollNo);
      ps.setString(3, date);
      ps.setString(4, status);

      int i = ps.executeUpdate();
      if(i > 0) {
        out.println("<h3>Attendance submitted successfully!</h3>");
      } else {
        out.println("<h3>Failed to submit attendance.</h3>");
      }

      con.close();
    } catch(Exception e) {
      out.println("Error: " + e.getMessage());
    }
  }
}
