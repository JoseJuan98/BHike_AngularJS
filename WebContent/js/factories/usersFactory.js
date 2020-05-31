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
    	}		
    }
    return usersInterface;
}])