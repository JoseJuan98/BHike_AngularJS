package es.unex.pi.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.unex.pi.dao.CategoryDAO;
import es.unex.pi.dao.JDBCCategoryDAOImpl;
import es.unex.pi.dao.JDBCRouteDAOImpl;
import es.unex.pi.dao.JDBCRoutesCategoriesDAOImpl;
import es.unex.pi.dao.JDBCUserDAOImpl;
import es.unex.pi.dao.RouteDAO;
import es.unex.pi.dao.RoutesCategoriesDAO;
import es.unex.pi.dao.UserDAO;
import es.unex.pi.model.Route;
import es.unex.pi.model.RoutesCategories;
import es.unex.pi.model.User;
import es.unex.pi.util.Triplet;



/**
 * Servlet implementation class ListUsersNotesServlet
 */
@WebServlet("/ListRoutesServlet.do")
public class ListRoutesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(HttpServlet.class.getName());
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListRoutesServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		logger.info("Atendiendo GET");
		
		Connection conn = (Connection) getServletContext().getAttribute("dbConn");
		UserDAO userDAO = new JDBCUserDAOImpl();
		userDAO.setConnection(conn);

		RouteDAO routeDAO = new JDBCRouteDAOImpl();
		routeDAO.setConnection(conn);
		
		CategoryDAO categoryDAO = new JDBCCategoryDAOImpl();
		categoryDAO.setConnection(conn);
		
		RoutesCategoriesDAO routesCategoriesDAO = new JDBCRoutesCategoriesDAOImpl();
		routesCategoriesDAO.setConnection(conn);
		
		List<Route> routesList = routeDAO.getAll();
		
		Iterator<Route> itRouteList = routesList.iterator();

		List<Triplet<Route, User, List<RoutesCategories>>> routesUserList = new ArrayList<Triplet<Route, User, List<RoutesCategories>>>();

		while(itRouteList.hasNext()) {
			Route route = (Route) itRouteList.next();
			User user = userDAO.get(route.getIdu());
			List<RoutesCategories> routesCategories = routesCategoriesDAO.getAllByRoute(route.getId());
			
			if(user != null)logger.info("User " + user.getUsername());

			routesUserList.add(new Triplet<Route, User, List<RoutesCategories>>(route,user,routesCategories));
		}
		
		
		List<User> listUser = new ArrayList<User>();
		listUser = userDAO.getAll();
		Iterator<User> itUser = listUser.iterator();
		Map<User,List<Route>> userRoutesMap = new HashMap<User,List<Route>>();
		
		while(itUser.hasNext()) {
			User user = itUser.next();
			routesList = routeDAO.getAllByUser(user.getId());
			userRoutesMap.put(user, routesList);
		}
		
		request.setAttribute("routesList",routesUserList);
		request.setAttribute("usersMap", userRoutesMap);
		
		//Attribute to display the see feature of the routes
		request.setAttribute("CheckTypeFrame", "MainPage");
		
		HttpSession session = request.getSession();
		String BlockRt = (String) session.getAttribute("BlockedRtDisplay");
		User user = (User) session.getAttribute("user");
		
		if(BlockRt != null) request.setAttribute("BlockedRtDisplay", BlockRt);
		if(user == null) request.setAttribute("userType", "NoUser");
		
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/ListRoutesUser.jsp");
		view.forward(request,response);
		
	
	}

	
}
