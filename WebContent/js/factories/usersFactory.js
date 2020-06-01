angular.module('BHikeApp')
.factory('usersFactory',['$http', function($http){
	var url = 'https://localhost:8443/BHike_2/rest/user/';
    var usersInterface = {
    	getUser : function(){
    		url = url ;
            return $http.get(url)
              	.then(function(response){
        			 return response.data;
               	});
		},
		getUserById : function(id){
    		urlid = url + id;
            return $http.get(urlid)
              	.then(function(response){
        			 return response.data;
               	});
		},
	  	putRoute : function(user){
    		var urlid = url+user.id;
            return $http.put(urlid, route)
            	.then(function(response){
      				 return response;
  				});                   
    	},
		//TODO Update =====================
		//TODO Delete =====================	
    }
    return usersInterface;
}])