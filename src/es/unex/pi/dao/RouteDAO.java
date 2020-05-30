package es.unex.pi.dao;

import java.sql.Connection;
import java.util.List;

import es.unex.pi.model.Route;


public interface RouteDAO {

	/**
	 * set the database connection in this DAO.
	 * 
	 * @param conn
	 *            database connection.
	 */
	public void setConnection(Connection conn);
	
	/**
	 * Gets a route from the DB using id.
	 * 
	 * @param id
	 *            Route Identifier.
	 * 
	 * @return Route object with that id.
	 */
	public Route get(long id);

	/**
	 * Gets all the notes from the database.
	 * 
	 * @return List of all the routes from the database.
	 */
	public List<Route> getAll();
	
	/**
	 * Gets all the routes from the database that contain a text in the title.
	 * 
	 * @param search
	 *            Search string .
	 * 
	 * @return List of all the routes from the database that contain a text either in the title.
	 */	
	public List<Route> getAllBySearchTitle(String search);


	/**
	 * Gets all the routes from the database that belong to a user.
	 * 
	 * @param idu
	 *            User identifier.
	 * 
	 * @return List of all the routes from the database that belong to a user
	 */	
	public List<Route> getAllByUser(long idu);
	
	
	/**
	 * Gets all the routes from the database that contain a text in the description.
	 * 
	 * @param search
	 *            Search string .
	 * 
	 * @return List of all the routes from the database that contain a text in the description.
	 */	
	public List<Route> getAllBySearchDescription(String search);
	
	/**
	 * Gets all the routes from the database that contain a text either in the title or in the description.
	 * 
	 * @param search
	 *            Search string .
	 * 
	 * @return List of all the routes from the database that contain a text either in the title or in the description.
	 */	
	public List<Route> getAllBySearchAll(String search);

	/**
	 * Adds a route to the database.
	 * 
	 * @param route
	 *            Route object with the route details.
	 * 
	 * @return Route identifier or -1 in case the operation failed.
	 */
	
	public long add(Route route);

	/**
	 * Updates an existing route in the database.
	 * 
	 * @param route
	 *            Route object with the new details of the existing route.
	 * 
	 * @return True if the operation was made and False if the operation failed.
	 */
	public boolean save(Route route);

	/**
	 * Deletes a route from the database.
	 * 
	 * @param id
	 *            Route identifier.
	 * 
	 * @return True if the operation was made and False if the operation failed.
	 */
	public boolean delete(long id);
}
