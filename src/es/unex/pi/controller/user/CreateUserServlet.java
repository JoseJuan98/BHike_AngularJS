package es.unex.pi.controller.user;

import java.io.IOException;
import java.sql.Connection;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import es.unex.pi.dao.JDBCUserDAOImpl;
import es.unex.pi.dao.UserDAO;
import es.unex.pi.model.User;

/**
 * Servlet implementation class CreateUserServlet
 */
@WebServlet("/CreateUserServlet.do")
public class CreateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(HttpServlet.class.getName());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateUserServlet() {
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
		if (session.getAttribute("user") != null)
			response.sendRedirect("ListRoutesServlet.do");
		else {
			request.setAttribute("CheckType", "Create");
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/UserSettings.jsp");
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		logger.info("Handling POST");
		// Set connection
		Connection conn = (Connection) getServletContext().getAttribute("dbConn");
		UserDAO userDao = new JDBCUserDAOImpl();
		userDao.setConnection(conn);

		User user = new User();

		user = userDao.get(request.getParameter("usern"));
		request.setAttribute("CheckType", "Create");
		
		if (user == null && request.getParameter("usern") != null) {
			String passw = request.getParameter("passw");
			String passw2 = request.getParameter("passw2");

			// Password is stored only if both passwords matches
			if (passw != null && passw.equals(passw2)) {
				user = new User();
				logger.info("Creating user with username: " + request.getParameter("usern") +" email: "+ request.getParameter("email") +" and password: "+ passw);
				// Obtain parameter of form
				user.setUsername(request.getParameter("usern"));
				user.setEmail(request.getParameter("email"));
				user.setPassword(passw);

				logger.info("Creating user with username: " + user.getUsername() +" email: "+user.getEmail()+" and password: "+user.getPassword());

				// If user can log after creating directly
//				HttpSession session = request.getSession();
//				session.setAttribute("user", user);

				// Store user in the DB
				userDao.add(user);
			
				user = null;
				
				request.removeAttribute("CheckType");
				
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/Login.jsp");
				view.forward(request, response);
			}else {
				logger.info("Fail creating user with username: " + request.getParameter("usern") +". Passwords didn't match");
				request.setAttribute("errormsg", "Passwords didn't match. Try again.");
				RequestDispatcher view = request.getRequestDispatcher("WEB-INF/UserSettings.jsp");
				view.forward(request, response);
			}
		} else {
			request.setAttribute("errormsg", "That username is taken. Try another.");
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/UserSettings.jsp");
			view.forward(request, response);
		}
	}
}
