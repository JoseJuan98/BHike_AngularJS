angular.module('BHikeApp')
.controller('seeRouteCtrl', ['usersFactory','routesFactory','$routeParams','$location',function(usersFactory,routesFactory,$routeParams,$location){
    var routeViewModel = this;
	routeViewModel.route={};
	routeViewModel.user={};
    routeViewModel.functions = {
    	readRoute : function(id) {
    		routesFactory.getRoute(id)
    			.then(function(response){
	    			console.log("Reading route with id: ", id);
					routeViewModel.route = response;
					return routeViewModel.functions.readUser(routeViewModel.route.idu);
	    		}, function(response){
	    			console.log("Error reading routes");
	    		})
		},
		readUser : function(id) {
			usersFactory.getUserById(id)
			.then(function(response){
	    			console.log("Reading route with id: ", id);
					routeViewModel.route = response;
	    		}, function(response){
	    			console.log("Error reading routes");
	    		})
		}
    }
	if ($routeParams.ID==undefined) $location.path('/');
	else routeViewModel.functions.readRoute($routeParams.ID);
}])