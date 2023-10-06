package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.util.StoreUtil;

// Servlet class handling requests related to the 'About' section.
public class AboutServlet extends HttpServlet {

    // Service method to handle both GET and POST requests.
    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        // Get PrintWriter object to send response back to the client.
        PrintWriter pw = res.getWriter();
        
        // Set the active tab for the navigation using the StoreUtil class.
        StoreUtil.setActiveTab(pw, "about");
        
        // Display an iframe with the content from a personal website.
        pw.println("<iframe src=\"https://flowcv.me/padmakshi\" class=\"holds-the-iframe\"\r\n"
                + "        title=\"My Personal Website\" width=\"100%\" height=\"100%\"></iframe>");
    }
}
