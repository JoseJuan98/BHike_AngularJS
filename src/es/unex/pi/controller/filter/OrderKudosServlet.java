package es.unex.pi.controller.filter;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
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
import es.unex.pi.util.SortByKudosAscTriplet;
import es.unex.pi.util.SortByKudosDescTriplet;
import es.unex.pi.util.SortByKudosRouteAsc;
import es.unex.pi.util.Triplet;

/**
 * Servlet implementation class OrderKudosServlet
 */
@WebServlet("/OrderKudosServlet.do")
public class OrderKudosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(HttpServlet.class.getName());   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderKudosServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Handling GET OrderKudosServlet");
		
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
			
			logger.info("User " + user.getUsername());

			routesUserList.add(new Triplet<Route, User, List<RoutesCategories>>(route,user,routesCategories));
		}
		
		
		List<User> listUser = new ArrayList<User>();
		listUser = userDAO.getAll();
		Iterator<User> itUser = listUser.iterator();
		Map<User,List<Route>> userRoutesMap = new HashMap<User,List<Route>>();
		
		String sortType = request.getParameter("SortType");
		
		while(itUser.hasNext()) {
			User user = itUser.next();
			routesList = routeDAO.getAllByUser(user.getId());
			
			//Sort by Kudos in a List
			if(sortType.equals("Asc")) {
				Collections.sort(routesList, new SortByKudosRouteAsc());
			}else if(sortType.equals("Desc")){
				Collections.sort(routesList, new SortByKudosRouteAsc());
			}else if(sortType.equals("Min")) {
				List<Route> routesList_aux = new ArrayList<Route>();
				for(Route rt_aux:routesList) {
					if(rt_aux.getKudos() < Integer.parseInt(request.getParameter("minkud"))) {
						routesList_aux.add(rt_aux);
					}
				}	
				routesList.removeAll(routesList_aux);
			}
			
			
			userRoutesMap.put(user, routesList);
		}
		//Sort by Kudos Asc in a Triple List
		if(sortType.equals("Asc")) {
			Collections.sort(routesUserList, new SortByKudosAscTriplet());
		}else if(sortType.equals("Desc")){
			Collections.sort(routesUserList, new SortByKudosDescTriplet());
		}else if(sortType.equals("Min")) {
			List<Triplet<Route, User, List<RoutesCategories>>> rtUsList_aux = new ArrayList<Triplet<Route,User,List<RoutesCategories>>>();
			
			for(Triplet<Route, User, List<RoutesCategories>> t_aux:routesUserList) {
				if(t_aux.getFirst().getKudos() < Integer.parseInt(request.getParameter("minkud"))){
					rtUsList_aux.add(t_aux);
				}
			}
			routesUserList.removeAll(rtUsList_aux);
		}
		
		
		
		request.setAttribute("routesList",routesUserList);
		request.setAttribute("usersMap", userRoutesMap);
		
		//The user decides to hide or show the blocked routes
		HttpSession session = request.getSession();
		String BlockRt = (String) session.getAttribute("BlockedRtDisplay");
		if(BlockRt != null) request.setAttribute("BlockedRtDisplay", BlockRt);
		
		//Attribute to display the see feature of the routes
		request.setAttribute("CheckTypeFrame", "MainPage");
		
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/ListRoutesUser.jsp");
		view.forward(request,response);
	}
}
