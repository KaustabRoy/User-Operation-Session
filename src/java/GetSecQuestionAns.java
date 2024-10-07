import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class GetSecQuestionAns extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();
        
        
        String security_question = req.getParameter("sec_question");
        String answer = req.getParameter("answer");
        
        try{
            HttpSession ses = req.getSession();
            String email = (String)ses.getAttribute("uemail");
            
            Class.forName("oracle.jdbc.driver.OracleDriver");
            // Registering Type-4 Driver
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","manager");
            Statement stmt = con.createStatement();
            String fetchQuery = "select * from appusers where email='"+email+"' and sec_answer='"+answer+"'";
            
            ResultSet rs = stmt.executeQuery(fetchQuery);
            if(rs.next()){
                    pw.println("<h2 style=color:blue>Set New Password</h2>"
                            + "<form method=post action=SetNewPassword>"
                            + "NEW PASSWORD: <input type=password name=newpasswd>"
                            + "<br><br>"
                            + "ReENTER NEW PASSWORD: <input type=password name=newpasswdre>"
                            + "<br><br>"
                            + "<input type=submit value=Submit>"
                            + "</form>");
            }
            else{
                    pw.println("<p style=color:red>Wrong Answer.</p>"
                            + "<a href=forgetpass.html>Return to forget password.</a>");
                }
            
        }catch(Exception e){
            pw.println(e);
        }
    }
}
