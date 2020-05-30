package es.unex.pi.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RoutesCategories {

	private long idr;
	private long idct;
	
	public long getIdr() {
		return idr;
	}
	
	public void setIdr(long idr) {
		this.idr = idr;
	}
	
	public long getIdct() {
		return idct;
	}
	
	public void setIdct(long idct) {
		this.idct = idct;
	}
}
