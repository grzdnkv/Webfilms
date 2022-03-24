import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class FilmPage extends HttpServlet {
    //Star star = new Star();

    public void init(ServletConfig config) {
        //cart.add("Film");
        //cart.add("Film");
        //star.reset();
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String uri = request.getRequestURI();
        String name = request.getParameter("name"); // filmname
        //String filmname = request.getParameter("filmname");
        Database db = new Database();
        String films[] = db.getFilms();
        String prices[] = db.getPrices();
        String descriptions[] = db.getDescriptions();
        String purchases[] = db.getPurchases();
        String left[] = db.getLeft();
        String sellers[] = db.getSellers();
        //db.filmsUpdate(films, prices, descriptions, purchases, left, sellers);
        int index = db.foundByName(name);

        PrintWriter out = response.getWriter();
        out.println("<html lang=\"ru\">");
        out.println("<head>\n<meta charset=\"utf-8\">\n<title>" + name + "</title>\n<link rel=\"stylesheet\" href=\"../css/styles.css\">\n</head>\n<body>\n<div id=\"wrapper\">");
        out.println(header());
        out.println("<div id=\"content\" class=\"font\">");
        out.println("<div id=\"pic\"><img src=\"../images/" + name + "-logo.jpg\" width=\"400\" height=\"600\" alt=\"film logo\"></div>");
        out.println("<div id=\"filmname\">" + name + "</div>");

        out.println("<div id=\"desc\">" + descriptions[index] + "</div>");
        out.println("<div id=\"info\"><p>Purchased: " + purchases[index] + "<br />Left: " + left[index] + "<br />Seller: " + sellers[index] + "</p></div>");
        out.println("<div id=\"button\"><div id=\"inline\"><form method=\"GET\" action=\"/webfilms/cart/add\"><button id=\"addToCart\" class=\"font\" type=\"submit\" name=\"name\" value=\"" + name + "\">");
        out.println("Add to cart (" + prices[index] + "$)</button><input type=\"hidden\" name=\"price\" value=\"" + prices[index] + "\"></form></div>");
        out.println("<div id=\"inline\"><form method=\"GET\" action=\"/webfilms/star/add\"><button id=\"addToStar\" type=\"submit\" name=\"name\" value=\"" + name + "\"><img src=\"../images/stars2.png\" width=\"27\" height=\"27\" alt=\"star logo\"></button></form></div></div>");
        out.println("</div>"); //end content
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
}