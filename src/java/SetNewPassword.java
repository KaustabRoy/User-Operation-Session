import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class SetNewPassword extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();
        
        
        String new_passwd = req.getParameter("newpasswd");
        String new_passwd_re = req.getParameter("newpasswdre");
        
        if(new_passwd.equals(new_passwd_re)){
            try{
            HttpSession ses = req.getSession();
            String email = (String)ses.getAttribute("uemail");
            
            Class.forName("oracle.jdbc.driver.OracleDriver");
            // Registering Type-4 Driver
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","manager");
            Statement stmt = con.createStatement();
            String updateQuery = "update appusers set password='"+new_passwd_re+"'where email='"+email+"'";
            
            int x = stmt.executeUpdate(updateQuery);
            if(x > 0){
                pw.println("<h1 style=color:green>Set new password successful.</h2>"
                        + "<a href=signin.html>Go to Login.</a>");
            }
            else{
                pw.println("<h1 style=color:red>Password update failed!</h2>"
                        + "<a href=forgetpass.html>Return to forget password again.</a>");
                con.close();
            }
            }catch(Exception e){
                pw.println(e);
            }
        }
        else{
            pw.println("<p style=color:red>Password does not match!</p><a href=forgetpass.html>Return to forget password.</a>");
        }
        
    }
}
