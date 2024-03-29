angular.module('BHikeApp', ['ngRoute'])
.config(function($routeProvider){
	$routeProvider
    	.when("/", {
    		controller: "mapRoutesCtrl",
    		controllerAs: "mapVM",
    		templateUrl: "MapRoutesTemplate.html",
    		resolve: {
    			// produce 500 miliseconds (0,5 seconds) of delay that should be enough to allow the server
    			//does any requested update before reading the orders.
    			// Extracted from script.js used as example on https://docs.angularjs.org/api/ngRoute/service/$route
    			delay: function($q, $timeout) {
    			var delay = $q.defer();
    			$timeout(delay.resolve, 500);
    			return delay.promise;
    			}
    		}
    	})
    	.when("/createRoute", {
			controller: "routeHandlerCtrl",
			controllerAs: "routeHandlerVM",
			templateUrl: "routeHandlerTemplate.html"
    	})
        .when("/editRoute/:ID", {
			controller: "routeHandlerCtrl",
			controllerAs: "routeHandlerVM",
			templateUrl: "routeHandlerTemplate.html"
		})
	    .when("/seeRoute/:ID", {
			controller: "seeRouteCtrl",
			controllerAs: "seeRouteVM",
			templateUrl: "routeView.html"
		})
		.when("/deleteRoute/:ID", {
			controller: "routeHandlerCtrl",
			controllerAs: "routeHandlerVM",
			templateUrl: "routeHandlerTemplate.html"
        })
	    .when("/editUser/:ID", {
			controller: "userHandlerCtrl",
			controllerAs: "userHandlerVM",
			templateUrl: "userSettingsTemplate.html"
		})
		.when("/deleteUser/:ID", {
			controller: "userHandlerCtrl",
			controllerAs: "userHandlerVM",
			templateUrl: "userSettingsTemplate.html"
		})
		.when("/search/:Srch", {
			controller: "searchCtrl",
			controllerAs: "searchVM",
			templateUrl: "searchTemplate.html"
		})
		.when("/giveKudo/:ID", {
			controller: "kudoCtrl",
    		controllerAs: "mapVM",
    		templateUrl: "MapRoutesTemplate.html",
		})
		.when("/disKudo/:ID", {
			controller: "kudoCtrl",
    		controllerAs: "mapVM",
    		templateUrl: "MapRoutesTemplate.html",
		})
		.when("/orderByKudos/Asc", {
			controller: "searchCtrl",
			controllerAs: "searchVM",
			templateUrl: "searchTemplate.html"
		})
		.when("/orderByKudos/Desc", {
			controller: "searchCtrl",
			controllerAs: "searchVM",
			templateUrl: "searchTemplate.html"
		})
		.when("/showAvailable", {
			controller: "searchCtrl",
			controllerAs: "searchVM",
			templateUrl: "searchTemplate.html"
		})
		.when("/showBlocked", {
			controller: "searchCtrl",
			controllerAs: "searchVM",
			templateUrl: "searchTemplate.html"
		})
		.when("/minKudos/:N", {
			controller: "searchCtrl",
			controllerAs: "searchVM",
			templateUrl: "searchTemplate.html"
		})
		.when("/blockRoute/:ID", {
			controller: "searchCtrl",
			controllerAs: "searchVM",
			templateUrl: "searchTemplate.html"
		})
		.when("/unblockRoute/:ID", {
			controller: "searchCtrl",
			controllerAs: "searchVM",
			templateUrl: "searchTemplate.html"
		});
})

//Factories call the API REST - DAO de angular
//El app es el manin, donde definimos los path de la aplicacion. Enrutamiento