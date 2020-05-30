package es.unex.pi.util;

import java.util.Comparator;

import es.unex.pi.model.Route;

public class SortByKudosRouteDesc implements Comparator<Route>{

	@Override
	public int compare(Route arg0, Route arg1) {
		return arg1.getKudos() - arg0.getKudos();
	}



}
