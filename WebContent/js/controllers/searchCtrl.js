angular.module('BHikeApp')
.controller('searchCtrl', ['routesFactory','$routeParams',function(routesFactory,$routeParams){
	var searchViewModel = this;
	searchViewModel.search={};
    searchViewModel.routes= [];
    searchViewModel.functions = {
 		where : function(route){
   			return $location.path() == route;
   		},
    	readRoutes : function(search) {
    		routesFactory.getRoutesBySearch(search)
    			.then(function(response){
					console.log("Reading all the routes: ", response);
					searchViewModel.search = $routeParams.Srch;
	    			searchViewModel.routes = response;
	    		}, function(response){
	    			console.log("Error reading routes");
	    		})
		},
		routeHandlerSwitcher : function(){
			if (routeHandlerViewModel.functions.where('/search')){
				console.log($location.path());
				searchViewModel.functions.readRoutes();
				$location.path('/');
			}
			else if (routeHandlerViewModel.functions.where('/searchByX/'+searchViewModel.search)){
				console.log($location.path());
				routeHandlerViewModel.functions.updateRoute();
				$location.path('/');
			}
			else {
				console.log($location.path());
				$location.path('/');
			}
			
		}
    }
    searchViewModel.functions.readRoutes($routeParams.Srch);
}])