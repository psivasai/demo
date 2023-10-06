package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.constant.BookStoreConstants;
import com.book.constant.db.UsersDBConstants;
import com.book.model.User;
import com.book.model.UserRole;
import com.book.service.UserService;
import com.book.service.impl.UserServiceImpl;



public class CustomerLoginServlet extends HttpServlet {

    UserService authService = new UserServiceImpl();

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        PrintWriter pw = res.getWriter();
        res.setContentType(BookStoreConstants.CONTENT_TYPE_TEXT_HTML);
        String uName = req.getParameter(UsersDBConstants.COLUMN_USERNAME);
        String pWord = req.getParameter(UsersDBConstants.COLUMN_PASSWORD);
        User user = authService.login(UserRole.CUSTOMER, uName, pWord, req.getSession());

        try {

            if (user != null) {

                RequestDispatcher rd = req.getRequestDispatcher("CustomerHome.html");
                rd.include(req, res);
                pw.println("    <div class = \"mainHeading\" id=\"topmid\"><h1>Welcome to the <br>Retro Reads</h1></div>\r\n"
                        + "    <br>\r\n"
                        + "    <p class = \"caption\" >Welcome "+user.getFirstName()+", Happy Learning !!</p>\r\n"
              
                        + "    ");

            } else {

                RequestDispatcher rd = req.getRequestDispatcher("CustomerLogin.html");
                rd.include(req, res);
                pw.println("<table class=\"tab\"><tr><td>Incorrect UserName or PassWord</td></tr></table>");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}