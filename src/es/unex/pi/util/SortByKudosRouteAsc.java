package es.unex.pi.util;

import java.util.Comparator;

import es.unex.pi.model.Route;

public class SortByKudosRouteAsc implements Comparator<Route>{

	@Override
	public int compare(Route arg0, Route arg1) {
		return arg0.getKudos() - arg1.getKudos();
	}
}
