angular.module('BHikeApp')
.controller('seeRouteCtrl', ['routesFactory','$routeParams','$location',function(routesFactory,$routeParams,$location){
    var mapViewModel = this;
    mapViewModel.routes= new Map();
    mapViewModel.functions = {
    	readRoutes : function() {
    		routesFactory.getRoutesByUser()
    			.then(function(response){
	    			console.log("Reading all the routes: ", response);
	    			mapViewModel.routes = response;
	    		}, function(response){
	    			console.log("Error reading routes");
	    		})
		}
    }
	mapViewModel.functions.readRoutes();
	if ($routeParams.ID==undefined) $location.path('/');
	else routeHandlerViewModel.functions.readRoute($routeParams.ID);
}])