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
		},
		readRoutesOrdered : function(order) {
			routesFactory.getRoutesOrdered(order)
				.then(function(response){
					console.log("Reading all routes in order: ",order," : ", response);					
	    			searchViewModel.routes = response;
	    		}, function(response){
	    			console.log("Error reading routes in order",order,"routes");
	    		})
		}
	}
	//var str = $location.path();

	if($routeParams.Srch!=undefined){
		if(searchViewModel.functions.where('/search/'+$routeParams.Srch)){
			searchViewModel.search = $routeParams.Srch;
			console.log("Entering to search Ctrl",$location.path());
			console.log('/search/'+searchViewModel.search);
			searchViewModel.functions.readRoutes(searchViewModel.search);
		}
	}else{
		console.log('Undefined parameter Search');
		if(searchViewModel.functions.where('/orderByKudos/Asc')){
			searchViewModel.functions.readRoutesOrdered('Asc');
		}else if(searchViewModel.functions.where('/orderByKudos/Desc')){
			searchViewModel.functions.readRoutesOrdered('Desc');
		}else if(searchViewModel.functions.where('/showAvailable')){

		}else if(searchViewModel.functions.where('/showBlocked')){

		}else if(searchViewModel.functions.where('/minKudos/'+$routeParams.N)){

		}
		
	}
}]);