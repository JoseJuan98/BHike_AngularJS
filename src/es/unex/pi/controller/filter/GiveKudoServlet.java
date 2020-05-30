package es.unex.pi.controller.filter;

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

import es.unex.pi.dao.JDBCRouteDAOImpl;
import es.unex.pi.dao.JDBCUserDAOImpl;
import es.unex.pi.dao.RouteDAO;
import es.unex.pi.dao.UserDAO;
import es.unex.pi.model.Route;
import es.unex.pi.model.User;

/**
 * Servlet implementation class GiveKudoServlet
 */
@WebServlet("/GiveKudoServlet.do")
public class GiveKudoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(HttpServlet.class.getName());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GiveKudoServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("Handling GET GiveKudoServlet");
		HttpSession session = request.getSession();
		User user = new User(); 
		user = (User) session.getAttribute("user");
		
		if (user == null) {
			response.sendRedirect("LoginServlet.do");
		} else {
			Connection conn = (Connection) getServletContext().getAttribute("dbConn");
			RouteDAO routeDao = new JDBCRouteDAOImpl();
			routeDao.setConnection(conn);

			Route route = routeDao.get(Long.parseLong(request.getParameter("routeID")));
			if (route != null) {

				logger.info("Hangling new route date and time: " + route.getDate());
				
				//Give 1 kudo more
				route.setKudos(route.getKudos()+1);
				
				routeDao.save(route);

				request.setAttribute("route", route);
				if (route.getIdu() == user.getId()) {
					request.setAttribute("CheckTypeRtUser", "Owner");
				} else {
					request.setAttribute("CheckTypeRtUser", "NoOwner");
					UserDAO userDao = new JDBCUserDAOImpl();
					userDao.setConnection(conn);

					User userRt = new User();
					userRt = userDao.get(route.getIdu());
					request.setAttribute("userRt", userRt);
				}
				RequestDispatcher view = request.getRequestDispatcher("WEB-INF/RouteView.jsp");
				view.forward(request, response);
			} else {
				response.sendRedirect(request.getContextPath() + "/ListRoutesServlet.do");
			}
		}
	}
}
