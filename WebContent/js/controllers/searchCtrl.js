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
		},
		readRoutesMinKudos : function(min) {
			routesFactory.getRoutesMinKudos(min)
				.then(function(response){
					console.log("Reading all routes greater than: ",min," : ", response);					
	    			searchViewModel.routes = response;
	    		}, function(response){
	    			console.log("Error reading routes greater than",min,"routes");
	    		})
		},
		readRoutesByAvailability : function(ava) {
			routesFactory.getRoutesByAvailability(ava)
				.then(function(response){
					console.log("Reading all routes with availability: ",ava," : ", response);					
					searchViewModel.routes = response;
				}, function(response){
					console.log("Error reading routes with availibility ",ava);
				})
		},
		updateAvailability : function(ava) {
			if( ava != 1){
				routesFactory.blockRoute($routeParams.ID)
					.then(function(response){
						console.log("Reading all routes with availability: ",ava," : ", response);					
						searchViewModel.routes = response;
					}, function(response){
						console.log("Error reading routes with availibility ",ava);
					})
			}else{
				routesFactory.unblockRoute($routeParams.ID)
					.then(function(response){
						console.log("Reading all routes with availability: ",ava," : ", response);					
						searchViewModel.routes = response;
					}, function(response){
						console.log("Error reading routes with availibility ",ava);
					})
			}

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
			searchViewModel.search='by ascending order';
			searchViewModel.functions.readRoutesOrdered('Asc');
		}else if(searchViewModel.functions.where('/orderByKudos/Desc')){
			searchViewModel.search='by descending order';
			searchViewModel.functions.readRoutesOrdered('Desc');
		}else if(searchViewModel.functions.where('/showAvailable')){
			searchViewModel.search='avalaible';
			searchViewModel.functions.readRoutesByAvailability(0);
		}else if(searchViewModel.functions.where('/showBlocked')){
			searchViewModel.search='blocked';
			searchViewModel.functions.readRoutesByAvailability(1);
		}else if(searchViewModel.functions.where('/minKudos/'+$routeParams.N)){
			searchViewModel.search='kudos greater than '+$routeParams.N;
			searchViewModel.functions.readRoutesMinKudos($routeParams.N);
		}else if(searchViewModel.functions.where('/blockRoute/'+$routeParams.ID)){
			searchViewModel.search='kudos greater than '+$routeParams.ID;
			searchViewModel.functions.updateAvailability(0);
			$location.path('/');
		}else if(searchViewModel.functions.where('/unblockRoute/'+$routeParams.ID)){
			searchViewModel.search='kudos greater than '+$routeParams.ID;
			searchViewModel.functions.updateAvailability(1);
			$location.path('/');			
		}
		
	}
}]);