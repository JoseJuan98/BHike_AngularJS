angular.module('BHikeApp')
.factory('categoriesFactory',['$http', function($http){
	var url = 'https://localhost:8443/BHike_2/rest/categories/';
    var catsInterface = {
    	getAllCats : function(){
            return $http.get(url)
              	.then(function(response){
        			 return response.data;
               	});
    	}			
    }
    return catsInterface;
}])