package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.constant.BookStoreConstants;
import com.book.constant.db.BooksDBConstants;
import com.book.model.Book;
import com.book.model.UserRole;
import com.book.service.BookService;
import com.book.service.impl.BookServiceImpl;
import com.book.util.StoreUtil;

//Servlet class handling requests to add books to the store.
public class AddBookServlet extends HttpServlet {
    BookService bookService = new BookServiceImpl();

    
    // Service method to handle both GET and POST requests.
    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        PrintWriter pw = res.getWriter();
        res.setContentType(BookStoreConstants.CONTENT_TYPE_TEXT_HTML);

        if (!StoreUtil.isLoggedIn(UserRole.SELLER, req.getSession())) {
            RequestDispatcher rd = req.getRequestDispatcher("SellerLogin.html");
            rd.include(req, res);
            pw.println("<table class=\"tab\"><tr><td>Please Login First to Continue!!</td></tr></table>");
            return;
        }
        
     // Retrieve book details from the request parameters.
        String bName = req.getParameter(BooksDBConstants.COLUMN_NAME);
        RequestDispatcher rd = req.getRequestDispatcher("SellerHome.html");
        rd.include(req, res);
        StoreUtil.setActiveTab(pw, "addbook");
        pw.println("<div class='container my-2'>");
        if(bName == null || bName.isBlank()) {
            //render the add book form;
            showAddBookForm(pw);
            return;
        } //else process the add book
        
 
        try {
            String uniqueID = UUID.randomUUID().toString();
            String bCode = uniqueID;
            String bAuthor = req.getParameter(BooksDBConstants.COLUMN_AUTHOR);
            double bPrice = Integer.parseInt(req.getParameter(BooksDBConstants.COLUMN_PRICE));
            int bQty = Integer.parseInt(req.getParameter(BooksDBConstants.COLUMN_QUANTITY));

            Book book = new Book(bCode, bName, bAuthor, bPrice, bQty);
            String message = bookService.addBook(book);
            if ("SUCCESS".equalsIgnoreCase(message)) {
                pw.println(
                        "<table class=\"tab\"><tr><td>Book Detail Updated Successfully!<br/>Add More Books</td></tr></table>");
            } else {
                pw.println("<table class=\"tab\"><tr><td>Failed to Add Books! Fill up CareFully</td></tr></table>");
                //rd.include(req, res);
            }
        } catch (Exception e) {
            e.printStackTrace();
            pw.println("<table class=\"tab\"><tr><td>Failed to Add Books! Fill up CareFully</td></tr></table>");
        }
    }
    
    private static void showAddBookForm(PrintWriter pw) {
        String form = "<table class=\"tab my-5\" style=\"width:40%;\">\r\n"
                + "        <tr>\r\n"
                + "            <td>\r\n"
                + "                <form action=\"addbook\" method=\"post\">\r\n"
                + "                    <!-- <label for=\"bookCode\">Book Code : </label><input type=\"text\" name=\"barcode\" id=\"bookCode\" placeholder=\"Enter Book Code\" required><br/> -->\r\n"
                + "                    <label for=\"bookName\" class= \"labels\">Book Name : </label> <br/>\r\n"
                + "						<input type=\"text\" name=\"name\" id=\"bookName\" class=\"inputValues\" placeholder=\"Enter Book's name\" required><br/>\r\n"
                + "                    <label for=\"bookAuthor\" class= \"labels\">Book Author : </label> <br/>\r\n"
                + "						<input type=\"text\" name=\"author\" id=\"bookAuthor\" class=\"inputValues\" placeholder=\"Enter Author's Name\" required><br/>\r\n"
                + "                    <label for=\"bookPrice\" class= \"labels\">Book Price : </label><br/>\r\n"
                + "						<input type=\"number\" name=\"price\" class=\"inputValues\" placeholder=\"Enter the Price\" required><br/>\r\n"
                + "                    <label for=\"bookQuantity\" class= \"labels\">Book Qnty : </label> <br/>\r\n"
                + "						<input type=\"number\" name=\"quantity\" id=\"bookQuantity\" class=\"inputValues\" placeholder=\"Enter the quantity\" required><br/>\r\n"
                + "                    <input class=\"btn btn-success my-2 inputValues\" type=\"submit\" value=\" Add Book \">\r\n"
                + "                </form>\r\n"
                + "            </td>\r\n"
                + "        </tr>  \r\n"
                + "        <!-- <tr>\r\n"
                + "            <td><a href=\"index.html\">Go Back To Home Page</a></td>\r\n"
                + "        </tr> -->\r\n"
                + "    </table>";
        pw.println(form);
    }
}
