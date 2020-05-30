package es.unex.pi.dao;

import java.sql.Connection;
import java.util.List;

import es.unex.pi.model.Category;


public interface CategoryDAO {

	/**
	 * set the database connection in this DAO.
	 * 
	 * @param conn
	 *            database connection.
	 */
	public void setConnection(Connection conn);
	
	/**
	 * Gets a category from the DB using id.
	 * 
	 * @param id
	 *            Category Identifier.
	 * 
	 * @return Category object with that id.
	 */
	public Category get(long id);

	/**
	 * Gets a category from the DB using name.
	 * 
	 * @param name
	 *            Category name.
	 * 
	 * @return Category object with that name.
	 */
	public Category get(String name);

	
	/**
	 * Gets all the categories from the database.
	 * 
	 * @return List of all the categories from the database.
	 */
	public List<Category> getAll();
	
	/**
	 * Gets all the categories from the database that contain a text in the name.
	 * 
	 * @param search
	 *            Search string .
	 * 
	 * @return List of all the categories from the database that contain a text in the name.
	 */	
	public List<Category> getAllBySearchName(String search);


	/**
	 * Adds a category to the database.
	 * 
	 * @param category
	 *            Category object with the category details.
	 * 
	 * @return Category identifier or -1 in case the operation failed.
	 */
	
	public long add(Category category);

	/**
	 * Updates an existing category in the database.
	 * 
	 * @param category
	 *            Category object with the new details of the existing category.
	 * 
	 * @return True if the operation was made and False if the operation failed.
	 */
	
	
	public boolean save(Category category);

	/**
	 * Deletes a category from the database.
	 * 
	 * @param id
	 *            Category identifier.
	 * 
	 * @return True if the operation was made and False if the operation failed.
	 */
	
	public boolean delete(long id);
}
