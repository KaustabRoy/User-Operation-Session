import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class RegisterUser extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();
        
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String passwd = req.getParameter("passwd");
        String security_question = req.getParameter("sec_question");
        String answer = req.getParameter("answer");
        String contact = req.getParameter("contact");
        
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            // Registering Type-4 Driver
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","manager");
            Statement stmt = con.createStatement();
            String insertQuery = "INSERT INTO appusers VALUES('"+name+"','"+email+"','"+passwd+"','"+security_question+"','"+answer+"','"+contact+"')";
            
            int x = stmt.executeUpdate(insertQuery);
            if(x > 0){
                pw.println("<h1 style=color:green>Insert successful</h2>"
                        + "<a href=signin.html>Go to Login</a>");
            }
            else{
                pw.println("<h1 style=color:red>Insert failed!</h2>"
                        + "<a href=signup.html>Register again.</a>");
                con.close();
            }
        }catch(Exception e){
            pw.println(e);
        }
    }
}
