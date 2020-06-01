package es.unex.pi.resources;

import java.sql.Connection;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import es.unex.pi.dao.JDBCRouteDAOImpl;
import es.unex.pi.dao.JDBCUserDAOImpl;
import es.unex.pi.dao.RouteDAO;
import es.unex.pi.dao.UserDAO;
import es.unex.pi.model.Route;
import es.unex.pi.model.User;
import es.unex.pi.resources.exceptions.CustomBadRequestException;

@Path("/user")
public class UsersResource {
	private static final Logger logger = Logger.getLogger(HttpServlet.class.getName());
	
	  @Context
	  ServletContext sc;
	  @Context
	  UriInfo uriInfo;
  
	  @GET
	  @Produces(MediaType.APPLICATION_JSON)
	  public User getUserJSON(@Context HttpServletRequest request) {
		Connection conn = (Connection) sc.getAttribute("dbConn");
		UserDAO userDao = new JDBCUserDAOImpl();
		userDao.setConnection(conn);
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		logger.info("Getting USER by DAO with username = "+ user.getUsername());
				
		//User returnUser = userDao.get(user.getId());
		logger.info("Getting USER for REST with username = "+ user.getUsername());
		
		return user; 
	  }
	  
	  @GET
	  @Path("/{userId: [0-9]+}")
	  @Produces(MediaType.APPLICATION_JSON)
	  public User getUserByIdJSON(@PathParam("userId") long userId,@Context HttpServletRequest request) {
		Connection conn = (Connection) sc.getAttribute("dbConn");
		UserDAO userDao = new JDBCUserDAOImpl();
		userDao.setConnection(conn);
		
		User user = userDao.get(userId); 
		
		logger.info("Getting USER by DAO with username = "+ user.getUsername());
				
		User returnUser =userDao.get(user.getId());
		returnUser.setPassword("**********");
		
		logger.info("Getting USER for REST with username = "+ user.getUsername());
		
		return returnUser; 
	  }
	  
	  @PUT
	  @Path("/{userId: [0-9]+}")
	  @Consumes(MediaType.APPLICATION_JSON)
	  public Response putUser(User userUpdate, @PathParam("userId") long userId, @Context HttpServletRequest request) throws Exception {
		  Connection conn = (Connection) sc.getAttribute("dbConn");
		  UserDAO userDao = new JDBCUserDAOImpl();
		  userDao.setConnection(conn);
		  
		  HttpSession session = request.getSession();
		  User user = (User) session.getAttribute("user");
		  
		  Response response = null;
		  
		  if(user != null ) {
			  session.setAttribute("user", userUpdate);
		  }else {
			  throw new CustomBadRequestException("No user session.");
		  }
		  
		  if(user.getId() == userUpdate.getId()) {
			  userDao.save(userUpdate);
		  }else {
			  throw new CustomBadRequestException("You are not allowed to do this.");
		  }
		  
		  
		  
		  return response;
	  }
	  
	  
	  

	  
	  
	  
} 

