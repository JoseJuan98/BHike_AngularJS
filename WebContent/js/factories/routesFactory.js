angular.module('BHikeApp')
.factory("routesFactory", ['$http',function($http){
	var url = 'https://localhost:8443/BHike_2/rest/routes/';
    var routesInterface = {
    	getRoutesByUser: function(){
			var url2 = url+'allByUser/';
			console.log("URL FOR allByUser", url2);
    		//promesa es un objeto de JS sin valor pero indica que eso en algun momento tendra un valor
    		//.then metodo al que la promesa llama cuando se ha resuelto para poner los datos
    		//promesa. todas las peticiones de REST son promesas,asigna un callback
    		//a la promesa.Cuando la promesa esta resuelta se llama a esta url
    		return $http.get(url2)
    			.then(function(response){
    				//devuelve los datos de la promesa 
    				return response.data;
    			});
		},
		getRoutesBySearch : function(search){
			var urlSearch = url + 'search/' + search;
			return $http.get(urlSearch)
    			.then(function(response){
    				return response.data;
    			});
		},
    	getRoute : function(id){
    		var urlid = url + id;
            return $http.get(urlid)
            	.then(function(response){
            		return response.data;
         		});
    	},
    	putRoute : function(route){
    		var urlid = url+route.id;
            return $http.put(urlid, route)
            	.then(function(response){
      				 return response;
  				});                   
    	},
    	postRoute : function(route){
    		return $http.post(url,route)
            	.then(function(response){
            		return response;
     			});
    	}, 
        deleteRoute : function(id){
    		var urlid = url+id;
            return $http.delete(urlid)
            	.then(function(response){
      				 return response;
  				}); 
        },
		setCategoryToRt : function(rtId,catId) {
			urlCats = url + rtId + '/categories/' + catId;
			return $http.post(urlCats)
				.then(function(response){
					return response;
				});
		},
        getCategoriesRoute : function(id) {
    		var urlCat = url + id + '/categories';
            return $http.get(urlCat)
            	.then(function(response){
            		return response.data;
         		});
		},
		giveKudo : function(id) {
    		var urlid = url+'giveKudo/'+id;
            return $http.put(urlid)
            	.then(function(response){
      				 return response;
  				});  
		},
		disKudo : function(id) {
    		var urlid = url+'disKudo/'+id;
            return $http.put(urlid)
            	.then(function(response){
      				 return response;
  				});  
		}
    }
    return routesInterface;
}])