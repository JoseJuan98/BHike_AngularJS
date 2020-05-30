package es.unex.pi.dao;

import java.sql.Connection;
import java.util.List;

import es.unex.pi.model.RoutesCategories;


public interface RoutesCategoriesDAO {

	/**
	 * set the database connection in this DAO.
	 * 
	 * @param conn
	 *            database connection.
	 */
	public void setConnection(Connection conn);

	/**
	 * Gets all the routes and the categories related to them from the database.
	 * 
	 * @return List of all the routes and the categories related to them from the database.
	 */
	
	public List<RoutesCategories> getAll();

	/**
	 *Gets all the RoutesCategory that are related to a category.
	 * 
	 * @param idct
	 *            Category identifier
	 * 
	 * @return List of all the RoutesCategory related to a category.
	 */
	public List<RoutesCategories> getAllByCategory(long idct);
	
	/**
	 * Gets all the RoutesCategory that contains an specific route.
	 * 
	 * @param idr
	 *            Route Identifier
	 * 
	 * @return List of all the RoutesCategory that contains an specific route
	 */
	public List<RoutesCategories> getAllByRoute(long idr);

	/**
	 * Gets a RoutesCategory from the DB using idr and idct.
	 * 
	 * @param idr 
	 *            Route identifier.
	 *            
	 * @param idct
	 *            Category Identifier
	 * 
	 * @return RoutesCategory with that idr and idct.
	 */
	
	public RoutesCategories get(long idr,long idct);

	/**
	 * Adds an routesCategory to the database.
	 * 
	 * @param routesCategory
	 *            RoutesCategory object with the details of the relation between the route and the category.
	 * 
	 * @return Route identifier or -1 in case the operation failed.
	 */
	
	public boolean add(RoutesCategories routesCategory);

	/**
	 * Updates an existing routesCategory in the database.
	 * 
	 * @param routesCategory
	 *            RoutesCategory object with the new details of the existing relation between the route and the category. 
	 * 
	 * @return True if the operation was made and False if the operation failed.
	 */
	
	public boolean save(RoutesCategories routesCategory);

	/**
	 * Deletes all routesCategories of a route from the database.
	 * 
	 * @param idr
	 *            Route identifier.
	 *            
	 * @param idct
	 *            Category Identifier
	 * 
	 * @return True if the operation was made and False if the operation failed.
	 */
	public boolean deleteByRoute(long idr);
	/**
	 * Deletes an routesCategory from the database.
	 * 
	 * @param idr
	 *            Route identifier.
	 *            
	 * @param idct
	 *            Category Identifier
	 * 
	 * @return True if the operation was made and False if the operation failed.
	 */
	
	public boolean delete(long idr, long idct);
}