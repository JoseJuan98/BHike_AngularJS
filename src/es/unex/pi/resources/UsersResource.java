package es.unex.pi.resources;

import java.sql.Connection;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import es.unex.pi.dao.JDBCUserDAOImpl;
import es.unex.pi.dao.UserDAO;
import es.unex.pi.model.User;

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
				
		User returnUser =userDao.get(user.getId());
		returnUser.setPassword("**********");
		
		logger.info("Getting USER for REST with username = "+ user.getUsername());
		
		return returnUser; 
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
} 