package es.unex.pi.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import es.unex.pi.model.Category;


public class JDBCCategoryDAOImpl implements CategoryDAO {

	private Connection conn;
	private static final Logger logger = Logger.getLogger(JDBCCategoryDAOImpl.class.getName());
	
	@Override
	public Category get(long id) {
		if (conn == null) return null;
		
		Category category = null;		
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Categories WHERE id ="+id);			 
			if (!rs.next()) return null; 
			category  = new Category();	 
			category.setId(rs.getInt("id"));
			category.setName(rs.getString("name"));
			category.setDescription(rs.getString("description"));
			
			logger.info("fetching Category by id: "+id+" -> "+category.getId()+" "+category.getName()+" "+category.getDescription());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return category;
	}
	
	
	@Override
	public Category get(String name) {
		if (conn == null) return null;
		
		Category category = null;		
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Categories WHERE name = '"+name+"'");			 
			if (!rs.next()) return null; 
			category  = new Category();	 
			category.setId(rs.getInt("id"));
			category.setName(rs.getString("name"));
			category.setDescription(rs.getString("description"));
			
			logger.info("fetching Category by name: "+category.getId()+" "+category.getName()+" "+category.getDescription());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return category;
	}
	
	
	
	public List<Category> getAll() {

		if (conn == null) return null;
		
		ArrayList<Category> categories = new ArrayList<Category>();
		try {
			Statement stmt;
			ResultSet rs;
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Categories");
			while ( rs.next() ) {
				Category category = new Category();
				category.setId(rs.getInt("id"));
				category.setName(rs.getString("name"));
				category.setDescription(rs.getString("description"));
				
				categories.add(category);
				logger.info("fetching Categories: "+category.getId()+" "+category.getName()+" "+category.getDescription());
								
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return categories;
	}
	
	public List<Category> getAllBySearchName(String search) {
		search = search.toUpperCase();
		if (conn == null)
			return null;

		ArrayList<Category> categories = new ArrayList<Category>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Categories WHERE UPPER(name) LIKE '%" + search + "%'");

			while (rs.next()) {
				Category category = new Category();
				
				category.setId(rs.getInt("id"));
				category.setName(rs.getString("name"));
				category.setDescription(rs.getString("description"));
				
				categories.add(category);
				
				logger.info("fetching categorys by text in the name: "+search+": "+category.getId()+" "+category.getName()+" "+category.getDescription());
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return categories;
	}
	

	@Override
	public long add(Category category) {
		long id=-1;
		long lastid=-1;
		if (conn != null){

			Statement stmt;
			
			
			try {
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM sqlite_sequence WHERE name ='categories'");			 
				if (!rs.next()) return -1; 
				lastid=rs.getInt("seq");
								
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate("INSERT INTO Categories (name,description) VALUES('"
									+category.getName()+"', '" + category.getDescription() + "')");
				
								
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM sqlite_sequence WHERE name ='categories'");			 
				if (!rs.next()) return -1; 
				id=rs.getInt("seq");
				if (id<=lastid) return -1;
											
				logger.info("CREATING Category("+id+"): "+category.getName());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return id;
	}

	@Override
	public boolean save(Category category) {
		boolean done = false;
		if (conn != null){
			
			Statement stmt;
			try {
				stmt = conn.createStatement();
				
				stmt.executeUpdate("UPDATE Categories SET name='"+category.getName()
				+"', description = '" + category.getDescription() + "' WHERE id = "+category.getId());
				
				logger.info("updating Category: "+category.getId()+" "+category.getName()+" "+category.getDescription());
				
				done= true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return done;
	}

	@Override
	public boolean delete(long id) {
		boolean done = false;
		if (conn != null){

			Statement stmt;
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate("DELETE FROM Categories WHERE id ="+id);
				
				logger.info("deleting Category: "+id);
				
				done= true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return done;
	}

	@Override
	public void setConnection(Connection conn) {
		this.conn = conn;
	}

	
}
