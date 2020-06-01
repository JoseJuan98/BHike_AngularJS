angular.module('BHikeApp')
.controller('kudoCtrl', ['routesFactory','$routeParams','$location',function(routesFactory,$routeParams,$location){
	var kudoViewModel = this;
    kudoViewModel.functions = {
 		where : function(route){
   			return $location.path() == route;
   		},
		giveKudo : function(id) {
			routesFactory.giveKudo(id)
				.then(function(response){
					console.log("Giving kudo to route with id: ", id);
    			}, function(response){
    				console.log("Error giving kudo.");
    			})
		},
		disKudo : function(id) {
			routesFactory.disKudo(id)
				.then(function(response){
					console.log("Disliking kudo to route with id: ", id);
    			}, function(response){
    				console.log("Error disliking kudo.");
    			})
		}
	}
	
	console.log("Entering to kudoCtrl",$location.path(),$routeParams.ID);
	
	var str = $location.path();
	console.log(str);

	if(kudoViewModel.functions.where('/giveKudo/'+$routeParams.ID)){
		console.log('INSIDE OF GIVEKUDO');
		kudoViewModel.functions.giveKudo($routeParams.ID);
	}else if(kudoViewModel.functions.where('/disKudo/'+$routeParams.ID)){
		console.log('INSIDE OF GIVEKUDO');
		kudoViewModel.functions.disKudo($routeParams.ID);
	}

	$location.path('/');
			
}]);