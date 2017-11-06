angular.module('userProfile').controller('userProfileController',['userService','$window',function(userService,$window){
	var self = this;
	var userId = $window.localStorage.getItem('user_id');
	userService.getUser(userId).then(function(resp){
		self.user = resp.data;
	});
}]);