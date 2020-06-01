angular.module('BHikeApp')
.controller('headerCtrl', ['usersFactory',function(usersFactory){
    var headerViewModel = this;
	headerViewModel.user={};
	headerViewModel.search='';
	headerViewModel.min=0;
    headerViewModel.functions = {
		readUser : function() {
			usersFactory.getUser()
				.then(function(response){
					headerViewModel.user = response;
					console.log("Getting user with id: ", headerViewModel.user.id," Response: ", response);
    			}, function(response){
    				console.log("Error reading user data");
    			})
		},
		emptyUser : function() {
			headerViewModel.user ={};
		}
    }
	headerViewModel.functions.readUser();
}])