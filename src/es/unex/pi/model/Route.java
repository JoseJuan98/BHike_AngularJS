package es.unex.pi.model;

import javax.xml.bind.annotation.XmlRootElement;

import es.unex.pi.util.DateTimeHelper;

@XmlRootElement
public class Route {

	@Override
	public String toString() {
		return "Route [id=" + id + ", idu=" + idu + ", title=" + title + ", description=" + description + ", date="
				+ date + ", distance=" + distance + ", elevation=" + elevation + ", kudos=" + kudos + ", blocked="
				+ blocked + ", difficulty=" + difficulty + ", durationH=" + durationH + ", durationM=" + durationM
				+ ", dateSimple=" + dateSimple + ", timeSimple=" + timeSimple + "]";
	}
	private long id;
	private long idu;
	private String title;
	private String description;
	private String date;
	private float distance;
	private int elevation;
	private int kudos;
	private int blocked;
	
	private int difficulty;
	private int durationH;
	private int durationM;
	
	private String dateSimple;
	private String timeSimple;
	
	public String getDateSimple() {
//		if(date != null) {
//			return DateTimeHelper.dateTime2Date(date);
//		}
		return dateSimple;
	}
	public void setDateSimple(String dateSimple) {
		this.dateSimple = dateSimple;
	}
	public String getTimeSimple() {
//		if(date != null) {
//			return DateTimeHelper.date2Time(date);
//		}
		return timeSimple;
	}
	public void setTimeSimple(String timeSimple) {
		this.timeSimple = timeSimple;
	}	
	public int getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	public int getDurationH() {
		return durationH;
	}
	public void setDurationH(int durationH) {
		this.durationH = durationH;
	}
	public int getDurationM() {
		return durationM;
	}
	public void setDurationM(int durationM) {
		this.durationM = durationM;
	}

	public int getBlocked() {
		return blocked;
	}
	public void setBlocked(int blocked) {
		this.blocked = blocked;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public float getDistance() {
		return distance;
	}
	public void setDistance(float distance) {
		this.distance = distance;
	}
	public int getElevation() {
		return elevation;
	}
	public void setElevation(int elevation) {
		this.elevation = elevation;
	}
	public long getIdu() {
		return idu;
	}
	public void setIdu(long idu) {
		this.idu = idu;
	}
	public int getKudos() {
		return kudos;
	}
	public void setKudos(int kudos) {
		this.kudos = kudos;
	}

	
	
}
