import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class LoginPage extends HttpServlet {
    //Star star = new Star();

    public void init(ServletConfig config) {
        //cart.add("Film");
        //cart.add("Film");
        //star.reset();
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String uri = request.getRequestURI();
        //String name = (String)session.getAttribute("name");
        HttpSession session = request.getSession(false);
        //String filmname = request.getParameter("filmname");
        
        PrintWriter out = response.getWriter();
        out.println("<html lang=\"ru\">");
        out.println("<head>\n<meta charset=\"utf-8\">\n<title>Login</title>\n<link rel=\"stylesheet\" href=\"../css/styles.css\">\n</head>\n<body>\n<div id=\"wrapper\">");
        out.println(header());
        out.println("<div id=\"content\" class=\"font\">");
        if (session != null) {
            String name = (String)session.getAttribute("name");
            out.println("<div>Now logined as: " + name +"</div>");
            out.println("<div><form action=\"/webfilms/profile/logout\" method=\"GET\"><input type=\"submit\" value=\"logout\"></form></div>");
        } else {
            out.println("<form action=\"/webfilms/profile/userlogin\" method=\"GET\"><table width=\"100%\" cellspacing=\"0\" cellpadding=\"4\"><tr><td align=\"right\" width=\"100\">Name:</td><td><input type=\"text\" name=\"name\"></td></tr><tr><td align=\"right\" width=\"100\">Password:</td><td><input type=\"password\" name=\"password\"></td></tr><tr><td></td><td><input type=\"submit\" value=\"login\"></td></tr></table></form>");
        }
        //out.println("Starred:"); //!
        out.println(getMainPage());
        out.println("</div>");
        out.println("</div>\n<footer>2021 by Gregory Gruzdenkov, SUAI</footer>\n</body>\n</html>");
    }
    public String header() {
        StringBuilder sb = new StringBuilder();
        sb.append("<header>\n");
        sb.append("<a href=\"../main/\">Webfilms</a>\n");
        sb.append("<a id=\"right-bar\" href=\"../cart/\"><img src=\"../images/cart.png\" width=\"52\" height=\"52\" alt=\"cart logo\"></a>\n");
        sb.append("<a id=\"right-bar\" href=\"../star/\"><img src=\"../images/stars2.png\" width=\"52\" height=\"52\" alt=\"star logo\"></a>\n");
        sb.append("<a id=\"right-bar\" href=\"../login/\"><img src=\"../images/login.png\" width=\"52\" height=\"52\" alt=\"login logo\"></a>\n");
        sb.append("<hr></hr>\n");
        sb.append("</header>\n");
        return sb.toString();
    }
    public String getMainPage() {
        StringBuilder sb = new StringBuilder();
        //sb.append("<form action=\"LoginServlet\" method=\"post\">Name:<input type=\"text\" name=\"name\"><br>Password:<input type=\"password\" name=\"password\"><br><input type=\"submit\" value=\"login\"></form>");
        sb.append("<p></p>");
        //sb.append("<a href=\"/webfilms/star/reset\">remove all</a>");
        return sb.toString();
    }
}