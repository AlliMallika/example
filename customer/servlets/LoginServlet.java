package customer.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import customer.dao.LoginDAO;
import customer.dao.impl.LoginDAOImpl;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Get an instance of LoginDAO
        LoginDAO loginDAO = LoginDAOImpl.getInstance();

        // Validate the user credentials using LoginDAO
        int validationCode = loginDAO.validateLogin(username, password);

        if (validationCode == 1) { // Assuming 1 means successful login
            // Set the username as an attribute in the session for future use
            request.getSession().setAttribute("username", username);

            // Redirect to home.jsp on successful login
            response.sendRedirect("home.jsp");
        } else {
            // Redirect to loginfailed.jsp on failed login
            response.sendRedirect("loginfailed.jsp");
        }
    }
}