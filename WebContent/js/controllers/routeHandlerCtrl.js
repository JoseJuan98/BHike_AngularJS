angular.module('BHikeApp')
.controller('routeHandlerCtrl', ['routesFactory','usersFactory','categoriesFactory','$routeParams','$location',function(routesFactory,usersFactory,categoriesFactory,$routeParams,$location){
	var routeHandlerViewModel = this;
	routeHandlerViewModel.route={};
	routeHandlerViewModel.categories=[];
	routeHandlerViewModel.catsRoute=[];
   	routeHandlerViewModel.functions = {
   		where : function(route){
   			return $location.path() == route;
   		},
		readUserNameEmail : function() {
			usersFactory.getUser()
				.then(function(response){
					routeHandlerViewModel.route.username= response.username;
					routeHandlerViewModel.route.email= response.email;
					console.log("Reading user with id: ",response.id," Response: ", response);
    			}, function(response){
    				console.log("Error reading user data",response);
    			})
		},
		readRoute : function(id) {
			routesFactory.getRoute(id)
				.then(function(response){
					console.log("Reading route with id: ", id," Response: ", response);
					routeHandlerViewModel.route = response;
				}, function(response){
					console.log("Error reading route",response);
					$location.path('/');
				})
		},
		readCategories : function() {
			categoriesFactory.getAllCats()
				.then(function(response){
	    			console.log("Reading all the categories", response);
	    			routeHandlerViewModel.categories = response;
	    		}, function(response){
	    			console.log("Error reading categories", response);
	    		})
		},
		getCatsRoute : function(id) {
			routesFactory.getCategoriesRoute(id)
				.then(function(response){
	    			console.log("Reading all the categories of route", response);
	    			routeHandlerViewModel.catsRoute = response;
	    		}, function(response){
	    			console.log("Error reading categories of route", response);
	    		})
		},
		updateRoute : function() {

			routesFactory.putRoute(routeHandlerViewModel.route)
				.then(function(response){
					console.log("Updating route with id:",routeHandlerViewModel.route.id," Response:", response);
				return routeHandlerViewModel.functions.setCategories($routeParams.ID);
    			}, function(response){
    				console.log("Error updating route", response);
    			})
		},	
		createRoute : function() {
			routeHandlerViewModel.functions.setDate();

			routesFactory.postRoute(routeHandlerViewModel.route)
			.then(function(response){
				console.log("Creating route. Response: ", response);
					console.log("Header:",response.headers());
					var rTurl=response.headers().location;
					console.log("Route URL:",rTurl);
					var rtId = rTurl.substring(rTurl.lastIndexOf('/') + 1);
					console.log("Route ID is: ", rtId);
				return routeHandlerViewModel.functions.setCategories(rtId);
			},function(response){
				console.log("Error creating route");
			})			
		},
		setCategories : function(routeId) {
			console.log("Setting categories of route");
	        for (var i = 0; i < routeHandlerViewModel.categories.length; i++) {
                if (routeHandlerViewModel.categories[i].Selected) {
					console.log("routeId: ", routeId, " .Category name ",routeHandlerViewModel.categories[i].name, "cat Id ", routeHandlerViewModel.categories[i].id);
					routesFactory.setCategoryToRt(routeId, routeHandlerViewModel.categories[i].id)
						.then(function(response){
							console.log("Category, set to route. Response Status:",response.status);
						}, function(response){
							console.log("Error updating route", response);
						})
                }
            }
		},
		setDate : function(){
			var date_input = new Date(routeHandlerViewModel.route.dateSimple);
			if(date_input.getDay() < 10){
				var day = "0" + date_input.getDay();
			}else{
				var day = date_input.getDay();
			}
			if((date_input.getMonth() + 1) < 10){
				var month = "0" + (date_input.getMonth() + 1);
			}else{
				var month = date_input.getMonth() + 1;
			}
			
			var year = date_input.getFullYear();
			routeHandlerViewModel.route.dateSimple = year + "-" + month + "-" + day;

			if(routeHandlerViewModel.route.timeSimple.getHours() < 10){
				var hour = "0" + routeHandlerViewModel.route.timeSimple.getHours();
			}else{
				var hour = routeHandlerViewModel.route.timeSimple.getHours();
			}
			if(routeHandlerViewModel.route.timeSimple.getMinutes() < 10){
				var min = "0" + routeHandlerViewModel.route.timeSimple.getMinutes();
			}else{
				var min = routeHandlerViewModel.route.timeSimple.getMinutes();
			}
			routeHandlerViewModel.route.timeSimple = hour + ":" + min;
			routeHandlerViewModel.route.date = (routeHandlerViewModel.route.dateSimple+" "+routeHandlerViewModel.route.timeSimple);
		},
		deleteRoute : function(id) {
			routesFactory.deleteRoute(id)
			.then(function(response){
				console.log("Deleting route. Response:", response);
			},function(response){
				console.log("Error deleting route", response);
			})
		},
		checkCats : function(name) {
			for (var i = 0; i < routeHandlerViewModel.catsRoute.length; i++) {
                if (routeHandlerViewModel.catsRoute[i].name == name) {					
					return true;
                }
			}
			return false;
		},
		routeHandlerSwitcher : function(){
			if (routeHandlerViewModel.functions.where('/createRoute')){
				console.log($location.path());
				routeHandlerViewModel.functions.createRoute();
			}
			else if (routeHandlerViewModel.functions.where('/editRoute/'+routeHandlerViewModel.route.id)){
				console.log($location.path());
				routeHandlerViewModel.functions.updateRoute();
			}
			else if (routeHandlerViewModel.functions.where('/deleteRoute/'+routeHandlerViewModel.route.id)){
				console.log($location.path());
				routeHandlerViewModel.functions.deleteRoute(routeHandlerViewModel.route.id);
			}
			else {
			console.log($location.path());
			}
			$location.path('/');
		}
	}

   	console.log("Entering routeHandlerCtrl with $routeParams.ID=",$routeParams.ID);
   	if ($routeParams.ID==undefined){
		routeHandlerViewModel.functions.readUserNameEmail();
		routeHandlerViewModel.functions.readCategories();
	}else{
		routeHandlerViewModel.categories={};
		routeHandlerViewModel.functions.readRoute($routeParams.ID);
		routeHandlerViewModel.functions.getCatsRoute($routeParams.ID);
		routeHandlerViewModel.functions.readCategories();
	} 
}]);