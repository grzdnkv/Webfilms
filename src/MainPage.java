import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class MainPage extends HttpServlet {
    //Star star = new Star();

    public void init(ServletConfig config) {
        //cart.add("Film");
        //cart.add("Film");
        //star.reset();
        //Database db = new Database();
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String uri = request.getRequestURI();
        //String filmname = request.getParameter("filmname");
        // if( uri.equals("/webfilms/star/add") ) {
        //     star.add(request.getParameter("name"));
        // }
        // else if( uri.equals("/webfilms/star/reset") ) {
        //     star.reset();
        // }
        Database db = new Database();
        PrintWriter out = response.getWriter();
        out.println("<html lang=\"ru\">");
        out.println("<head>\n<meta charset=\"utf-8\">\n<title>Main Page</title>\n" +
                "<link rel=\"stylesheet\" href=\"../css/styles.css\">\n</head>\n<body>\n<div id=\"wrapper\">");
        out.println(header());
        out.println("<div id=\"content\" class=\"font\">");
        out.println(getMainPage(db));
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
    public String getMainPage(Database db) {
        StringBuilder sb = new StringBuilder();
        String films[] = db.getFilms();
        sb.append("<div id=\"catalog\">");
        for(int i = 0; i < films.length; i++) {
            sb.append("<div id=\"catalog\"><form method=\"GET\" action=\"../filmpage/\"><input type=\"image\" src=\"../images/" + films[i] + "-logo.jpg\" width=\"200\" height=\"300\">");
            sb.append("<input type=\"hidden\" name=\"name\" value=\"" + films[i] + "\"></form></div>\n");
        }
        sb.append("</div>");
        sb.append("<p></p>");
        return sb.toString();
    }
}