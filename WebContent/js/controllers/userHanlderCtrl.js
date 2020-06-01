angular.module('BHikeApp')
.controller('userHandlerCtrl', ['usersFactory','$routeParams','$location',function(usersFactory,$routeParams,$location){
    var userViewModel = this;
	userViewModel.user={};
	userViewModel.confirmPassw={};
    userViewModel.functions = {
		
   		where : function(user){
   			return $location.path() == user;
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
		},//TODO =================================
		deleteUser : function(id) {
			if(userViewModel.user.password != userViewModel.confirmPassw){
				alert("The passwords doesn't coincide");
			}else{
				usersFactory.deleteUser(id)
				.then(function(response){
					console.log("Deleting user. Response:", response);
					$location.path('/');
				},function(response){
					console.log("Error deleting user", response);
				})
			}
		},//TODO =========================
		userHandlerSwitcher : function(){
			if (userViewModel.functions.where('/editUser/'+userViewModel.user.id)){
				console.log($location.path());
				userViewModel.functions.updateUser();
				$location.path('/');
			}
			else if (userViewModel.functions.where('/deleteUser/'+userViewModel.user.id)){
				console.log($location.path());
				userViewModel.functions.deleteUser(userViewModel.user.id);
				$location.path('/');
			}
			else {
				console.log($location.path());
				$location.path('/');
			}
			
		}
    }
	console.log("Entering routeHandlerCtrl with $routeParams.ID=",$routeParams.ID);

	userViewModel.functions.readUser();
 
}])