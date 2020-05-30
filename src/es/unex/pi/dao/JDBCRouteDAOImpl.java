package es.unex.pi.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import es.unex.pi.model.Route;

public class JDBCRouteDAOImpl implements RouteDAO {

	private Connection conn;
	private static final Logger logger = Logger.getLogger(JDBCRouteDAOImpl.class.getName());
	
	@Override
	public Route get(long id) {
		if (conn == null) return null;
		
		Route route = null;		
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Routes WHERE id ="+id);			 
			if (!rs.next()) return null; 
			route  = new Route();	 
			route.setId(rs.getInt("id"));
			route.setTitle(rs.getString("title"));
			route.setDescription(rs.getString("description"));
			route.setDate(rs.getString("date"));
			route.setDistance(rs.getFloat("distance"));
			route.setElevation(rs.getInt("elevation"));
			route.setIdu(rs.getInt("idu"));
			
			route.setDurationH(rs.getInt("duration_hour"));
			route.setDurationM(rs.getInt("duration_min"));
			route.setDifficulty(rs.getInt("difficulty"));
			
			route.setKudos(rs.getInt("kudos"));
			route.setBlocked(rs.getInt("blocked"));
			
			logger.info("fetching routes: "+route.getId()+" "+route.getTitle()+" "+route.getDescription()+ " " + route.getDate() + " " + route.getDistance() 
						+ " " + route.getElevation() + " " + route.getIdu() + " " + route.getKudos());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return route;
	}
	
	public List<Route> getAll() {

		if (conn == null) return null;
		
		ArrayList<Route> routes = new ArrayList<Route>();
		try {
			Statement stmt;
			ResultSet rs;
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Routes");
			while ( rs.next() ) {
				Route route = new Route();
				route.setId(rs.getInt("id"));
				route.setTitle(rs.getString("title"));
				route.setDescription(rs.getString("description"));
				route.setDate(rs.getString("date"));
				route.setDistance(rs.getFloat("distance"));
				route.setElevation(rs.getInt("elevation"));
				route.setIdu(rs.getInt("idu"));
				
				route.setDurationH(rs.getInt("duration_hour"));
				route.setDurationM(rs.getInt("duration_min"));
				route.setDifficulty(rs.getInt("difficulty"));
				
				route.setKudos(rs.getInt("kudos"));
				route.setBlocked(rs.getInt("blocked"));
				
				routes.add(route);
				logger.info("fetching routes: "+route.getId()+" "+route.getTitle()+" "+route.getDescription()+ " " + route.getDate() + " " + route.getDistance() 
						+ " " + route.getElevation() + " " + route.getIdu() + " " + route.getKudos());
								
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return routes;
	}
	
	public List<Route> getAllBySearchTitle(String search) {
		search = search.toUpperCase();
		if (conn == null)
			return null;

		ArrayList<Route> routes = new ArrayList<Route>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Routes WHERE UPPER(title) LIKE '%" + search + "%'");

			while (rs.next()) {
				Route route = new Route();
				
				route.setId(rs.getInt("id"));
				route.setTitle(rs.getString("title"));
				route.setDescription(rs.getString("description"));
				route.setDate(rs.getString("date"));
				route.setDistance(rs.getFloat("distance"));
				route.setElevation(rs.getInt("elevation"));
				route.setIdu(rs.getInt("idu"));
				
				route.setDurationH(rs.getInt("duration_hour"));
				route.setDurationM(rs.getInt("duration_min"));
				route.setDifficulty(rs.getInt("difficulty"));
				
				route.setKudos(rs.getInt("kudos"));
				route.setBlocked(rs.getInt("blocked"));
				
				routes.add(route);
				
				logger.info("fetching routes by text in the title: "+route.getId()+" "+route.getTitle()+" "+route.getDescription()+ " " + route.getDate() + " " + route.getDistance() 
				+  " " + route.getElevation()+ " " + route.getIdu() + " " + route.getKudos());				
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return routes;
	}
	
	public List<Route> getAllBySearchDescription(String search) {
		search = search.toUpperCase();
		if (conn == null)
			return null;

		ArrayList<Route> routes = new ArrayList<Route>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Routes WHERE UPPER(description) LIKE '%" + search + "%'");

			while (rs.next()) {
				Route route = new Route();
				
				route.setId(rs.getInt("id"));
				route.setTitle(rs.getString("title"));
				route.setDescription(rs.getString("description"));
				route.setDate(rs.getString("date"));
				route.setDistance(rs.getFloat("distance"));
				route.setElevation(rs.getInt("elevation"));
				route.setIdu(rs.getInt("idu"));
				
				route.setDurationH(rs.getInt("duration_hour"));
				route.setDurationM(rs.getInt("duration_min"));
				route.setDifficulty(rs.getInt("difficulty"));
				
				route.setKudos(rs.getInt("kudos"));
				route.setBlocked(rs.getInt("blocked"));

				routes.add(route);
				
				logger.info("fetching routes by text in the description: "+route.getId()+" "+route.getTitle()+" "+route.getDescription()+ " " + route.getDate() + " " + route.getDistance() 
				+ " " + route.getElevation()+ " " + route.getIdu() + " " + route.getKudos());				
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return routes;
	}
	
	public List<Route> getAllBySearchAll(String search) {
		search = search.toUpperCase();
		if (conn == null)
			return null;

		ArrayList<Route> routes = new ArrayList<Route>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Routes WHERE UPPER(title) LIKE '%" + search + "%' OR UPPER(description) LIKE '%" + search + "%'");

			while (rs.next()) {
				Route route = new Route();
				
				route.setId(rs.getInt("id"));
				route.setTitle(rs.getString("title"));
				route.setDescription(rs.getString("description"));
				route.setDate(rs.getString("date"));
				route.setDistance(rs.getFloat("distance"));
				route.setElevation(rs.getInt("elevation"));
				route.setIdu(rs.getInt("idu"));
				
				route.setDurationH(rs.getInt("duration_hour"));
				route.setDurationM(rs.getInt("duration_min"));
				route.setDifficulty(rs.getInt("difficulty"));
				
				route.setKudos(rs.getInt("kudos"));
				route.setBlocked(rs.getInt("blocked"));

				routes.add(route);
				
				logger.info("fetching routes by text either in the title or in the description: "+route.getId()+" "+route.getTitle()+" "+route.getDescription()+ " " + route.getDate() + " " + route.getDistance() 
				+ " " + route.getElevation()+ " " + route.getIdu() + " " + route.getKudos());				
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return routes;
	}

	
	public List<Route> getAllByUser(long idu) {
		
		if (conn == null)
			return null;

		ArrayList<Route> routes = new ArrayList<Route>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Routes WHERE idu = "+ idu);

			while (rs.next()) {
				Route route = new Route();
				
				route.setId(rs.getInt("id"));
				route.setTitle(rs.getString("title"));
				route.setDescription(rs.getString("description"));
				route.setDate(rs.getString("date"));
				route.setDistance(rs.getFloat("distance"));
				route.setElevation(rs.getInt("elevation"));
				route.setIdu(rs.getInt("idu"));
				
				route.setDurationH(rs.getInt("duration_hour"));
				route.setDurationM(rs.getInt("duration_min"));
				route.setDifficulty(rs.getInt("difficulty"));
				
				route.setKudos(rs.getInt("kudos"));
				route.setBlocked(rs.getInt("blocked"));
				
				routes.add(route);
				
				logger.info("fetching routes by text either in the title or in the description: "+route.getId()+" "+route.getTitle()+" "+route.getDescription()+ " " + route.getDate() + " " + route.getDistance() 
				+ " " + route.getElevation()+ " " + route.getIdu() + " " + route.getKudos());				
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return routes;
	}

	
	
	
	@Override
	public long add(Route route) {
		long id=-1;
		long lastid=-1;
		if (conn != null){

			Statement stmt;
			
			
			try {
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM sqlite_sequence WHERE name ='routes'");			 
				if (!rs.next()) return -1; 
				lastid=rs.getInt("seq");
								
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate("INSERT INTO routes (title,description,elevation,idu,kudos,distance,date,blocked,duration_hour,duration_min,difficulty) VALUES('"
									+route.getTitle()+"','"+route.getDescription()+"'," + route.getElevation() + "," 
									+ route.getIdu() + "," + route.getKudos()+","+ route.getDistance() +",'"+ route.getDate() +"'," + route.getBlocked() +"," + route.getDurationH() +"," + route.getDurationM() +"," + route.getDifficulty() + ")");
				
								
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM sqlite_sequence WHERE name ='routes'");			 
				if (!rs.next()) return -1; 
				id=rs.getInt("seq");
				if (id<=lastid) return -1;
											
				logger.info("CREATING Route("+id+"): "+route.getTitle()+" "+route.getDescription());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return id;
	}

	@Override
	public boolean save(Route route) {
		boolean done = false;
		if (conn != null){
			
			Statement stmt;
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate("UPDATE Routes SET title='"+route.getTitle()
				+"', description='"+route.getDescription()
				+"', elevation="+route.getElevation()
				+", idu="+route.getIdu()
				+", kudos="+route.getKudos()
				+", distance="+route.getDistance()
				+", date='"+route.getDate()
				+"', blocked="+route.getBlocked()
				+", duration_hour="+route.getDurationH()
				+", duration_min="+route.getDurationM()
				+", difficulty="+route.getDifficulty()
				+" WHERE id = "+route.getId());
				logger.info("updating Route: "+route.getId()+" "+route.getTitle()+" "+route.getDescription());
						
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
				stmt.executeUpdate("DELETE FROM Routes WHERE id ="+id);
				logger.info("deleting Route: "+id);
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
