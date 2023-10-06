package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.constant.BookStoreConstants;
import com.book.constant.ResponseCode;
import com.book.constant.db.BooksDBConstants;
import com.book.model.Book;
import com.book.model.UserRole;
import com.book.service.BookService;
import com.book.service.impl.BookServiceImpl;
import com.book.util.StoreUtil;

public class UpdateBookServlet extends HttpServlet {
    BookService bookService = new BookServiceImpl();

    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        PrintWriter pw = res.getWriter();
        res.setContentType(BookStoreConstants.CONTENT_TYPE_TEXT_HTML);

        if (!StoreUtil.isLoggedIn(UserRole.SELLER, req.getSession())) {
            RequestDispatcher rd = req.getRequestDispatcher("SellerLogin.html");
            rd.include(req, res);
            pw.println("<table class=\"tab\"><tr><td>Please Login First to Continue!!</td></tr></table>");
            return;
        }

        RequestDispatcher rd = req.getRequestDispatcher("SellerHome.html");
        rd.include(req, res);
        StoreUtil.setActiveTab(pw, "storebooks");
        pw.println("<div class='container my-2'>");

        try {
            if (req.getParameter("updateFormSubmitted") != null) {
                String bName = req.getParameter(BooksDBConstants.COLUMN_NAME);
                String bCode = req.getParameter(BooksDBConstants.COLUMN_BARCODE);
                String bAuthor = req.getParameter(BooksDBConstants.COLUMN_AUTHOR);
                double bPrice = Double.parseDouble(req.getParameter(BooksDBConstants.COLUMN_PRICE));
                int bQty = Integer.parseInt(req.getParameter(BooksDBConstants.COLUMN_QUANTITY));

                Book book = new Book(bCode, bName, bAuthor, bPrice, bQty);
                String message = bookService.updateBook(book);
                if (ResponseCode.SUCCESS.name().equalsIgnoreCase(message)) {
                    pw.println(
                            "<table class=\"caption\"><tr><td>Book Detail Updated Successfully!</td></tr></table>");
                } else {
                    pw.println("<table class=\"caption\"><tr><td>Failed to Update Book!!</td></tr></table>");
                    // rd.include(req, res);
                }

                return;
            }

            String bookId = req.getParameter("bookId");

            if (bookId != null) {
                Book book = bookService.getBookById(bookId);
                showUpdateBookForm(pw, book);
            }

        } catch (Exception e) {
            e.printStackTrace();
            pw.println("<table class=\"tab\"><tr><td>Failed to Load Book data!!</td></tr></table>");
        }
    }

    private static void showUpdateBookForm(PrintWriter pw, Book book) {
        String form = "<table class=\"tab my-5\" style=\"width:40%;\">\r\n"
                + "        <tr>\r\n"
                + "            <td>\r\n"
                + "                <form action=\"updatebook\" method=\"post\">\r\n"
                + "                    <label for=\"bookCode\" class= \"labels\">Book Code : </label>"
                + "<input type=\"text\" name=\"barcode\" id=\"bookCode\" class= \"inputValues\" placeholder=\"Enter Book Code\" value='"
                + book.getBarcode() + "' readonly><br/>"
                
                + "                    <label for=\"bookName\" class= \"labels\">Book Name : </label> "
                + "<input type=\"text\" name=\"name\" id=\"bookName\" class= \"inputValues\" placeholder=\"Enter Book's name\" value='"
                + book.getName() + "' required><br/>\r\n"
                
                + "                    <label for=\"bookAuthor\" class= \"labels\">Book Author : </label>"
                + "<input type=\"text\" name=\"author\" id=\"bookAuthor\" class= \"inputValues\" placeholder=\"Enter Author's Name\" value='"
                + book.getAuthor() + "' required><br/>\r\n"
                
                + "                    <label for=\"bookPrice\" class= \"labels\">Book Price : </label>"
                + "<input type=\"number\" name=\"price\" class= \"inputValues\" placeholder=\"Enter the Price\" value='"
                + book.getPrice() + "' required><br/>\r\n"
                
                + "                    <label for=\"bookQuantity\" class= \"labels\">Book Qnty : </label>"
                + "<input type=\"number\" name=\"quantity\" id=\"bookQuantity\" class= \"inputValues\" placeholder=\"Enter the quantity\" value='"
                + book.getQuantity() + "' required><br/>\r\n"
                
                + "                    <input class=\"btn btn-success my-2 inputValues\" type=\"submit\" name='updateFormSubmitted' value=\" Update Book \">\r\n"
                + "                </form>\r\n"
                + "            </td>\r\n"
                + "        </tr>  \r\n"
                + "    </table>";
        pw.println(form);
    }
}
