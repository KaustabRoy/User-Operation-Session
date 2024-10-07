import java.io.*;
//import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class GetUserEmail extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();
        
        String email = req.getParameter("email");
        
        try{
            HttpSession ses = req.getSession();
            ses.setAttribute("uemail", email);
            
            pw.println("<form method=post action=GetSecQuestionAns>"
                    + "Security Question:<select name=sec_question>"
                    + "<option value=pet_name>Pet Name</option>"
                    + "<option value=nick_name>Nick Name</option>"
                    + "<option value=first_sch>First School</option>"
                    + "</select>"
                    + "<br>"
                    + "Answer:<input type=text name=answer>"
                    + "<br><br>"
                    + "<input type=submit value=Submit>"
                    + "</form>");
        }catch(Exception e){
            pw.println(e);
        }
    }
}
