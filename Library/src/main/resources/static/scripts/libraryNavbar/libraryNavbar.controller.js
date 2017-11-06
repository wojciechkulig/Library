angular.module('libraryNavbar').controller('libraryNavbarController',['$rootScope','$window',function($rootScope,$window){
	var self = this;
	$rootScope.$on('localStorageUpdated',function(event,access_token){
		self.loggedIn = access_token;
	});
	self.logout = function(){
		$window.localStorage.clear();
		$rootScope.$emit('localStorageUpdated',null);
	};
}]);