<%
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class CartPage extends HttpServlet {
    Cart cart = new Cart();

    public void init(ServletConfig config) {
        cart.add("Film");
        //cart.add("Film");
        cart.reset();
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String uri = request.getRequestURI();
        if( uri.equals("/webfilms/cart/add") ) {
            cart.add(request.getParameter("name"));
        }
        else if( uri.equals("/webfilms/cart/reset") ) {
            cart.reset();
        }
        PrintWriter out = response.getWriter();
%>
<!doctype html>
<html lang="ru">
    <head>
        <meta charset="utf-8">
        <title>cart</title>
        <link rel="stylesheet" href="css/styles.css">
    </head>
    <body>
    <div id="wrapper">
        <header>
        <a href="">Webfilms</a>
        <script src="cart.js"></script>
        <a id="right-bar" href=""><img src="images/cart.png"
          width="52" height="52" alt="cart logo"></a>
        <a id="right-bar" href=""><img src="images/stars2.png"
          width="52" height="52" alt="star logo"></a>
        <hr></hr>
        </header>
        <div id="content" class="font">
<%
        out.println("Last request URI was:" + uri);
        out.println(getMainPage());
        }
        public String getMainPage() {
        StringBuilder sb = new StringBuilder();
        String films[] = cart.getFilms();
        for(int i = 0; i < films.length; i++) {
            sb.append("<p>" + films[i] + "</p>\n");
        }
        sb.append("<form method=\"GET\" action=\"/webfilms/cart/add\">\n");
        sb.append("Name: <input type=\"text\" name=\"name\">\n"); 
        sb.append("<input type=\"submit\" value=\"add\">\n");
        sb.append("</form>");
        sb.append("<a href=\"/webfilms/cart/reset\">reset</a>");
        return sb.toString();
        }
}
%>
        </div>
    </div>
    <footer>2021 by Gregory Gruzdenkov, SUAI</footer>
    </body>
</html>