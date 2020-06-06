angular.module('BHikeApp')
.controller('userHandlerCtrl', ['usersFactory','$routeParams','$location','$window',function(usersFactory,$routeParams,$location,$window){
    var userViewModel = this;
	userViewModel.user={};
	userViewModel.confirmPassw={};
    userViewModel.functions = {
		
   		where : function(route){
   			return $location.path() == route;
		   },
		readUser : function() {
			usersFactory.getUser()
				.then(function(response){
					userViewModel.user = response;
					console.log("Getting user with id: ", userViewModel.user.id," Response: ", response);
				}, function(response){
					console.log("Error reading user data");
				})
		},
		emptyUser : function() {
			userViewModel.user ={};
		},
		updateUser : function() {
			if(userViewModel.user.password != userViewModel.confirmPassw){
				alert("The passwords doesn't coincide");
			}else{
				usersFactory.putUser(userViewModel.user)
					.then(function(response){
						console.log("Updating user with id:",userViewModel.user.id," Response:", response);
					}, function(response){
						console.log("Error updating user", response);
					})
			}
		},
		deleteUser : function(id) {
			if(userViewModel.user.password != userViewModel.confirmPassw){
				alert("The passwords doesn't coincide");
			}else{
				usersFactory.deleteUser(id)
				.then(function(response){
					console.log("Deleting user. Response:", response);
					$window.location.href="https://localhost:8443/BHike_2/LoginServlet.do";
					console.log($window.location.href);
					//return userViewModel.functions.emptyUser();
				},function(response){
					console.log("Error deleting user", response);
				})
			}
		},
		userHandlerSwitcher : function(){
			if (userViewModel.functions.where('/editUser/'+userViewModel.user.id)){
				console.log($location.path());
				userViewModel.functions.updateUser();
				$location.path('/');
			}
			else if (userViewModel.functions.where('/deleteUser/'+userViewModel.user.id)){
				console.log($location.path());
				userViewModel.functions.deleteUser(userViewModel.user.id);				
				console.log($window.location.href);
			}
			else {
				console.log($location.path());
				$location.path('/');
			}
			
		}
    }
	console.log("Entering userHandlerCtrl with $routeParams.ID=",$routeParams.ID);

	userViewModel.functions.readUser();
 
}])