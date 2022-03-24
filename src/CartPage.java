import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class CartPage extends HttpServlet {
    Cart cart = new Cart();

    public void init(ServletConfig config) {
        //cart.add("Film");
        //cart.add("Film");
        cart.reset();
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String uri = request.getRequestURI();
        Database db = new Database();
        String[] f = db.getFilms();
        String[] cartdb;
        String[] stardb;
        HttpSession session = request.getSession(false);
        //db.init();
        //String filmname = request.getParameter("filmname");
        String name = (String)session.getAttribute("name");
        //cart.reset();
        cart.reset();
        cartdb = db.getCart(name);
        String[] prices = db.getPrices();
        for(int i = 0; i < cartdb.length; i++) {
            cart.add(cartdb[i], prices[db.foundByName(cartdb[i])]);
        }
        if( uri.equals("/webfilms/cart/add") ) {
            //String name = (String)session.getAttribute("name");
            //cartdb = db.getCart(name);
            //index = db.foundByName(request.getParameter("name"));
            // for(int i = 0; i < cartdb.length; i++) {
            //     cart.add(cartdb[i], prices[db.foundByName(cartdb[i])]);
            // }
            cart.add(request.getParameter("name"), request.getParameter("price"));
            db.userInfoUpdate(name, cart.getFilms(), db.getStar(name));
            //String[] buf = new String(cartdb.length+1);

        }
        else if( uri.equals("/webfilms/cart/reset") ) {
            cart.reset();
            //db.resetCart(name);
            db.userInfoUpdate(name, cart.getFilms(), db.getStar(name));
            request.getRequestDispatcher("/cart/").forward(request, response);
        }
        PrintWriter out = response.getWriter();
        out.println("<html lang=\"ru\">");
        out.println("<head>\n<meta charset=\"utf-8\">\n<title>cart</title>\n<link rel=\"stylesheet\" href=\"../css/styles.css\">\n</head>\n<body>\n<div id=\"wrapper\">");
        out.println(header());
        out.println("<div id=\"content\" class=\"font\">");
        out.println("Cart:");
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
        String films[] = cart.getFilms();
        String prices[] = cart.getPrices();
        int total = 0;
        for(int i = 0; i < films.length; i++) {
            sb.append("<p><a href=\"../filmpage/?name=" + films[i] + "\">" + films[i] + " - " + prices[i] + /*" <a href=\"/webfilms/cart/remove\">remove</a>*/"$</a></p>\n");
            total += Integer.parseInt(prices[i]);
        }
        sb.append("<p>Total: " + total + "$</p>");
        sb.append("<a href=\"/webfilms/cart/reset\">remove all</a>");
        return sb.toString();
    }
}