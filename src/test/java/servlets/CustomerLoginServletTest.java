package servlets;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.book.model.UserRole;
import com.book.service.UserService;

public class CustomerLoginServletTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	  @Test
	    public void testDoPostInvalidLogin() throws Exception {
	        // Arrange
	        when(request.getParameter("username")).thenReturn("invalidUsername");
	        when(request.getParameter("password")).thenReturn("invalidPassword");
	        when(request.getRequestDispatcher("CustomerLogin.html")).thenReturn(requestDispatcher);
	        when(UserService.login(UserRole.CUSTOMER, "invalidUsername", "invalidPassword", request.getSession())).thenReturn(null);

	        // Act
	        customerLoginServlet.doPost(request, response);

	        // Assert
	        // Add assertions as needed based on the expected behavior
	    }

}
