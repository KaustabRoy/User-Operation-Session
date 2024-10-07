import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginUser extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();

        String email = req.getParameter("email");
        String passwd = req.getParameter("passwd");
        
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            // Registering Type-4 Driver
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","manager");
            Statement stmt = con.createStatement();
            String fetchQuery = "select * from appusers where email='"+email+"' and password='"+passwd+"'";
            
            ResultSet rs = stmt.executeQuery(fetchQuery);
            if(rs.next()){
                pw.println("<h2 style=color:green>Welcome - "+ rs.getString(1) +" !</h2>");
                pw.println("<p>Name is: " + rs.getString(1)+ "</p>");
                pw.println("<p>Email is: " + rs.getString(2) + "</p>");
            }
            else{
                pw.println("<h2 style=color:red>Login Failed!</h2>");
            }
        }catch(Exception e){
            pw.println(e);
        }
    }
}
