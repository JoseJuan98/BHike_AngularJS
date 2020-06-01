angular.module('BHikeApp')
.controller('searchCtrl', ['routesFactory','$routeParams','$location',function(routesFactory,$routeParams,$location){
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
	    			searchViewModel.routes = response;
	    		}, function(response){
	    			console.log("Error reading routes");
	    		})
		}
	}
	//searchViewModel.search = $routeParams.Srch;
	console.log("Entering to search Ctrl",$location.path());
	console.log('/search/'+searchViewModel.search);
	//searchViewModel.functions.readRoutes(searchViewModel.search);
	
	var str = $location.path();

	if($routeParams.Srch!=undefined){
		if(searchViewModel.functions.where('/search/'+$routeParams.Srch)){
			searchViewModel.search = $routeParams.Srch;
			console.log("Entering to search Ctrl",$location.path());
			console.log('/search/'+searchViewModel.search);
			searchViewModel.functions.readRoutes(searchViewModel.search);
		}
	}else{
		console.log('Undefined parameter Search');
	}
}]);