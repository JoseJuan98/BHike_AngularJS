package es.unex.pi.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import es.unex.pi.model.RoutesCategories;

public class JDBCRoutesCategoriesDAOImpl implements RoutesCategoriesDAO {

	private Connection conn;
	private static final Logger logger = Logger.getLogger(JDBCRoutesCategoriesDAOImpl.class.getName());

	@Override
	public List<RoutesCategories> getAll() {

		if (conn == null) return null;
						
		ArrayList<RoutesCategories> routesCategoriesList = new ArrayList<RoutesCategories>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM routescategories");
						
			while ( rs.next() ) {
				RoutesCategories routesCategories = new RoutesCategories();
				routesCategories.setIdr(rs.getInt("idr"));
				routesCategories.setIdct(rs.getInt("idct"));
						
				routesCategoriesList.add(routesCategories);
				logger.info("fetching all routesCategories: "+routesCategories.getIdr()+" "+routesCategories.getIdct());
					
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return routesCategoriesList;
	}

	@Override
	public List<RoutesCategories> getAllByCategory(long idct) {
		
		if (conn == null) return null;
						
		ArrayList<RoutesCategories> routesCategoriesList = new ArrayList<RoutesCategories>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM RoutesCategories WHERE idct="+idct);

			while ( rs.next() ) {
				RoutesCategories routesCategories = new RoutesCategories();
				routesCategories.setIdr(rs.getInt("idr"));
				routesCategories.setIdct(rs.getInt("idct"));

				routesCategoriesList.add(routesCategories);
				logger.info("fetching all routesCategories by idr: "+routesCategories.getIdr()+"->"+routesCategories.getIdct());
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return routesCategoriesList;
	}
	
	@Override
	public List<RoutesCategories> getAllByRoute(long idr) {
		
		if (conn == null) return null;
						
		ArrayList<RoutesCategories> routesCategoriesList = new ArrayList<RoutesCategories>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM RoutesCategories WHERE idr="+idr);

			while ( rs.next() ) {
				RoutesCategories routesCategories = new RoutesCategories();
				routesCategories.setIdr(rs.getInt("idr"));
				routesCategories.setIdct(rs.getInt("idct"));
							
				routesCategoriesList.add(routesCategories);
				logger.info("fetching all routesCategories by idct: "+routesCategories.getIdct()+"-> "+routesCategories.getIdr());
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return routesCategoriesList;
	}
	
	
	@Override
	public RoutesCategories get(long idr,long idct) {
		if (conn == null) return null;
		
		RoutesCategories routesCategories = null;		
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM RoutesCategories WHERE idr="+idr+" AND idct="+idct);			 
			if (!rs.next()) return null;
			routesCategories= new RoutesCategories();
			routesCategories.setIdr(rs.getInt("idr"));
			routesCategories.setIdct(rs.getInt("idct"));

			logger.info("fetching routesCategories by idr: "+routesCategories.getIdr()+"  and idct: "+routesCategories.getIdct());
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return routesCategories;
	}
	
	

	@Override
	public boolean add(RoutesCategories routesCategories) {
		boolean done = false;
		if (conn != null){
			
			Statement stmt;
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate("INSERT INTO RoutesCategories (idr,idct) VALUES('"+
									routesCategories.getIdr()+"','"+
									routesCategories.getIdct()+"')");
						
				logger.info("creating RoutesCategories:("+routesCategories.getIdr()+" "+routesCategories.getIdct());
				done= true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return done;
	}

	@Override
	public boolean save(RoutesCategories routesCategories) {
		boolean done = false;
		if (conn != null){

			Statement stmt;
			try {
				stmt = conn.createStatement();
				
				ResultSet rs = stmt.executeQuery("SELECT * FROM RoutesCategories WHERE idr="+routesCategories.getIdr());			 
				if (!rs.next()) 
					return false;
				long idct = rs.getInt("idct");

				stmt.executeUpdate("UPDATE RoutesCategories SET idct="+routesCategories.getIdct()
				+" WHERE idr = "+routesCategories.getIdr() + " AND idct = " + idct);
				
				logger.info("updating RoutesCategories:("+routesCategories.getIdr()+" "+routesCategories.getIdct());
				
				done= true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return done;
	}
	@Override
	public boolean deleteByRoute(long idr) {
		boolean done = false;
		if (conn != null){

			Statement stmt;
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate("DELETE FROM RoutesCategories WHERE idr ="+idr);
				logger.info("deleting RoutesCategories: "+idr);
				done= true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return done;
	}
	@Override
	public boolean delete(long idr, long idct) {
		boolean done = false;
		if (conn != null){

			Statement stmt;
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate("DELETE FROM RoutesCategories WHERE idr ="+idr+" AND idct="+idct);
				logger.info("deleting RoutesCategories: "+idr+" , idct="+idct);
				done= true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return done;
	}

	@Override
	public void setConnection(Connection conn) {
		// TODO Auto-generated method stub
		this.conn = conn;
	}
	
}
