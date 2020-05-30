package es.unex.pi.controller.user;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.unex.pi.dao.JDBCUserDAOImpl;
import es.unex.pi.dao.UserDAO;
import es.unex.pi.model.User;

import java.util.logging.Logger;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(HttpServlet.class.getName());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("Handling GET");

		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null) {
			response.sendRedirect("pages/index.html");
		}else {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/Login.jsp");
			view.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("Hangling POST");

		Connection conn = (Connection) getServletContext().getAttribute("dbConn");
		UserDAO userDao = new JDBCUserDAOImpl();
		userDao.setConnection(conn);

		String username = request.getParameter("user");
		String passw = request.getParameter("passw");

		logger.info("User authenticaion username (" + username + ") with password (" + passw + ") ");

		User user = userDao.get(username);

		if (user != null) {
			if (user.getPassword().equals(passw)) {

				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				response.sendRedirect("pages/index.html");
				
			} else {
				logger.info("User introduced wrong password (" + passw + ")");
				request.setAttribute("messages", "Wrong password. Try again!");
				request.setAttribute("user_val", user.getUsername());
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/Login.jsp");
				view.forward(request, response);
			}
		} else {
			logger.info("User(" + username + ") doesn't exists");
			request.setAttribute("messages", "Wrong username. Try again!");
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/Login.jsp");
			view.forward(request, response);
		}

	}

}
