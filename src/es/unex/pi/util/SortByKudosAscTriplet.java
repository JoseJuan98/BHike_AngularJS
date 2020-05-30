package es.unex.pi.util;

import java.util.Comparator;
import java.util.List;

import es.unex.pi.model.Route;
import es.unex.pi.model.RoutesCategories;
import es.unex.pi.model.User;

public final class SortByKudosAscTriplet implements Comparator<Triplet<Route, User, List<RoutesCategories>>> {

	@Override
	public int compare(Triplet<Route, User, List<RoutesCategories>> t1,
			Triplet<Route, User, List<RoutesCategories>> t2) {
		return t1.getFirst().getKudos() - t2.getFirst().getKudos();
	}

}
