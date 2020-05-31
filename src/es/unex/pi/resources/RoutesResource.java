package es.unex.pi.resources;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import es.unex.pi.dao.CategoryDAO;
import es.unex.pi.dao.JDBCCategoryDAOImpl;
import es.unex.pi.dao.JDBCRouteDAOImpl;
import es.unex.pi.dao.JDBCRoutesCategoriesDAOImpl;
import es.unex.pi.dao.JDBCUserDAOImpl;
import es.unex.pi.dao.RouteDAO;
import es.unex.pi.dao.RoutesCategoriesDAO;
import es.unex.pi.dao.UserDAO;
import es.unex.pi.model.Category;
import es.unex.pi.model.Route;
import es.unex.pi.model.RoutesCategories;
import es.unex.pi.model.User;
import es.unex.pi.resources.exceptions.CustomBadRequestException;
import es.unex.pi.resources.exceptions.CustomNotFoundException;

@Path("/routes")
public class RoutesResource {
	@Context
	ServletContext sc;
	@Context
	UriInfo uriInfo;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Route> getRoutesJSON(@Context HttpServletRequest request) {
		Connection conn = (Connection) sc.getAttribute("dbConn");
		RouteDAO routeDao = new JDBCRouteDAOImpl();
		routeDao.setConnection(conn);

		List<Route> routes = routeDao.getAll();

		return routes;
	}

	@GET
	@Path("/allByUser/")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<User, List<Route>> getAllRoutesJSON(@Context HttpServletRequest request) {
		Connection conn = (Connection) sc.getAttribute("dbConn");
		UserDAO userDAO = new JDBCUserDAOImpl();
		userDAO.setConnection(conn);

		RouteDAO routeDAO = new JDBCRouteDAOImpl();
		routeDAO.setConnection(conn);

		List<Route> routesList = new ArrayList<Route>();

		List<User> listUser = new ArrayList<User>();
		listUser = userDAO.getAll();
		Iterator<User> itUser = listUser.iterator();
		Map<User, List<Route>> userRoutesMap = new HashMap<User, List<Route>>();

		while (itUser.hasNext()) {
			User user = itUser.next();
			routesList = routeDAO.getAllByUser(user.getId());
			userRoutesMap.put(user, routesList);
		}

		return userRoutesMap;
	}

	@GET
	@Path("/{routeid: [0-9]+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Route getRouteViewJSON(@PathParam("routeid") long routeid, @Context HttpServletRequest request) {
		Connection conn = (Connection) sc.getAttribute("dbConn");
		RouteDAO routeDao = new JDBCRouteDAOImpl();
		routeDao.setConnection(conn);

		Route route = routeDao.get(routeid);

		if (route == null)
			throw new CustomNotFoundException("Not Found route with id: " + routeid);

		return route;
	}

	@GET
	@Path("/EditRoute/{routeid: [0-9]+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Route getRouteEditJSON(@PathParam("routeid") long routeid, @Context HttpServletRequest request) {
		Connection conn = (Connection) sc.getAttribute("dbConn");
		RouteDAO routeDao = new JDBCRouteDAOImpl();
		routeDao.setConnection(conn);

		Route route = routeDao.get(routeid);

		return route;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postRoute(Route route, @Context HttpServletRequest request) {
		Connection conn = (Connection) sc.getAttribute("dbConn");
		// Set connection for the Routes
		RouteDAO routeDao = new JDBCRouteDAOImpl();
		routeDao.setConnection(conn);
		RoutesCategoriesDAO routCatDao = new JDBCRoutesCategoriesDAOImpl();
		routCatDao.setConnection(conn);

		HttpSession session = request.getSession();
		User user = new User();
		user = (User) session.getAttribute("user");

		if (user == null) {
			System.out.println("Logged to add a route.");
			return Response.status(401).build();
		}

		// I want to check if this user created this route before
		List<Route> rtCheckList = routeDao.getAllByUser(user.getId());

		boolean found = false;

		if (!rtCheckList.isEmpty()) {
			Iterator<Route> itRt = rtCheckList.iterator();
			while (itRt.hasNext()) {
				Route aux = itRt.next();
				if (aux.getTitle().equals(route.getTitle())) {
					found = true;
				}
			}
		}
		System.out.println("Route before modiying anything : " + route.toString());
		route.setIdu(user.getId());
		route.setBlocked(0); // By default they are available
		route.setKudos(0);
		route.setDate(route.getDateSimple().concat(" " + route.getTimeSimple()));
		System.err.println("Route after changing things: " + route.toString());

		Response res;

		if (user.getId() != route.getIdu()) {
			System.out.println("Errors in parameters");
			throw new CustomBadRequestException("Errors in parameters in JSON");
		}
		if (found) {
			System.out.println("This route have been created before");
			throw new CustomBadRequestException(
					"You created already this route. Try to update the information instead of creating a new one.");
		}

		long id = routeDao.add(route);

		res = Response // return 201 and Location: /routes/newid
				.created(uriInfo.getAbsolutePathBuilder().path(Long.toString(id)).build())
				.contentLocation(uriInfo.getAbsolutePathBuilder().path(Long.toString(id)).build()).build();
		return res;
	}

	@POST
	@Path("/{routeId: [0-9]+}/categories/{catId: [0-9]+}")
	public Response createCategoryRoute(@Context HttpServletRequest context, @PathParam("catId") long catId,
		@PathParam("routeId") long routeId) {		
		
		Response res;
		Connection conn = (Connection) sc.getAttribute("dbConn");
		RouteDAO routeDao = new JDBCRouteDAOImpl();
		routeDao.setConnection(conn);
		RoutesCategoriesDAO rtCatDao = new JDBCRoutesCategoriesDAOImpl();
		rtCatDao.setConnection(conn);
	
		Route route = routeDao.get(routeId);
		
		HttpSession session = context.getSession();
		User user = (User) session.getAttribute("user");
		
		if(user == null) {
			System.out.println("No session established.");
			return Response.status(401).build();
		}
		
		if (route == null || user.getId() != route.getIdu()) {
			System.out.println("Wrong route.");
			return Response.status(401).build();
		}
		
		if(rtCatDao.get(routeId, catId) != null) {
			return Response.status(400).build();
		}
		
		RoutesCategories rtCat = new RoutesCategories();
		rtCat.setIdr(routeId);
		rtCat.setIdct(catId);
		
		rtCatDao.add(rtCat);
		
		res = Response // return 201 and Location: /routes/newid
				.created(uriInfo.getAbsolutePathBuilder().path(Long.toString(routeId)).build())
				.contentLocation(uriInfo.getAbsolutePathBuilder().path(Long.toString(routeId)).build()).build();
		return res;	
	}

	@GET
	@Path("/{routeId: [0-9]+}/categories")	  
	@Produces(MediaType.APPLICATION_JSON) 
	public List<Category> getRouteCategories(@PathParam("routeId") long routeId, @Context HttpServletRequest request) throws Exception { 
	  
		Connection conn = (Connection) sc.getAttribute("dbConn");

		RoutesCategoriesDAO rtCatDao = new JDBCRoutesCategoriesDAOImpl();
		rtCatDao.setConnection(conn);
		CategoryDAO categoryDao = new JDBCCategoryDAOImpl();
		categoryDao.setConnection(conn);
		
		List<RoutesCategories> rtCats = null;
		rtCats = rtCatDao.getAllByRoute(routeId);
				
		if(rtCats == null) {
			throw new CustomBadRequestException("No session established.");
		}
		
		List<Category> categories = new ArrayList<Category>();
		
		for (RoutesCategories rtCt : rtCats) {
			categories.add(categoryDao.get(rtCt.getIdct()));
		}
		
		return categories;
	}

	@PUT
	@Path("/{routeId: [0-9]+}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response putRoute(Route routeUpdate, @PathParam("routeId") long routeId, @Context HttpServletRequest request)
			throws Exception {
		Connection conn = (Connection) sc.getAttribute("dbConn");
		RouteDAO routeDao = new JDBCRouteDAOImpl();
		routeDao.setConnection(conn);
		
		RoutesCategoriesDAO rtCatDao = new JDBCRoutesCategoriesDAOImpl();
		rtCatDao.setConnection(conn);

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		Response response = null;

		// We check that the route exists
		Route route = routeDao.get(routeUpdate.getId());
		if ((route != null) && (user.getId() == route.getIdu())) {
			if (route.getId() != routeId)
				throw new CustomBadRequestException("Error in id.");
			else {
				routeUpdate.setIdu(route.getIdu());
				routeUpdate.setBlocked(route.getBlocked()); // By default they are available
				routeUpdate.setKudos(route.getKudos());
				routeUpdate.setDate(route.getDateSimple().concat(" " + route.getTimeSimple()));
				System.out.println("Route after changing things: " + route.toString());
				
				routeDao.save(routeUpdate);
				
				rtCatDao.deleteByRoute(routeUpdate.getId());
			}
		} else
			throw new WebApplicationException(Response.Status.NOT_FOUND);

		return response;
	}
	
	@DELETE
	@Path("/{routeId: [0-9]+}")	  
	public Response deleteRoute(@PathParam("routeId") long routeId,
			  					 @Context HttpServletRequest request) {		  
		Connection conn = (Connection) sc.getAttribute("dbConn");
		RouteDAO routeDao = new JDBCRouteDAOImpl();
		routeDao.setConnection(conn);
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		Route route = routeDao.get(routeId);
		if ((route != null) && (user.getId() == route.getIdu())){
			routeDao.delete(routeId);
					return Response.noContent().build(); //204 no content 
		}
		else throw new CustomBadRequestException("Error in user or id");		
			
	}
}
