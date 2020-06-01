angular.module('BHikeApp')
.controller('mapRoutesCtrl', ['routesFactory',function(routesFactory){
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
		},
		giveKudo : function(id) {
			routesFactory.giveKudo()
		},
		disKudo : function(id) {
			routesFactory.disKudo()
		}
    }
    mapViewModel.functions.readRoutes();
}])