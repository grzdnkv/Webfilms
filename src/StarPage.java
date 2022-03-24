import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class StarPage extends HttpServlet {
    Star star = new Star();

    public void init(ServletConfig config) {
        //cart.add("Film");
        //cart.add("Film");
        star.reset();
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String uri = request.getRequestURI();
        Database db = new Database();
        //String[] f = db.getFilms();
        String[] cartdb;
        String[] stardb;
        HttpSession session = request.getSession(false);
        String name = (String)session.getAttribute("name");
        star.reset();
        stardb = db.getStar(name);
        for (int i = 0; i < stardb.length; i++){
            star.add(stardb[i]);
        }
        //String filmname = request.getParameter("filmname");
        if( uri.equals("/webfilms/star/add") ) {
            star.add(request.getParameter("name"));
            db.userInfoUpdate(name, db.getCart(name), star.getFilms());
        }
        else if( uri.equals("/webfilms/star/reset") ) {
            star.reset();
            //db.resetStar(name);
            db.userInfoUpdate(name, db.getCart(name), star.getFilms());
            request.getRequestDispatcher("/star/").forward(request, response);
        }
        PrintWriter out = response.getWriter();
        out.println("<html lang=\"ru\">");
        out.println("<head>\n<meta charset=\"utf-8\">\n<title>Starred</title>\n<link rel=\"stylesheet\" href=\"../css/styles.css\">\n</head>\n<body>\n<div id=\"wrapper\">");
        out.println(header());
        out.println("<div id=\"content\" class=\"font\">");
        out.println("Starred:");
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
        String films[] = star.getFilms();
        for(int i = 0; i < films.length; i++) {
            sb.append("<p><a href=\"../filmpage/?name=" + films[i] + "\">" + films[i] + /*" <a href=\"/webfilms/cart/remove\">remove</a>*/"</a></p>\n");
        }
        sb.append("<p></p>");
        sb.append("<a href=\"/webfilms/star/reset\">remove all</a>");
        return sb.toString();
    }
}