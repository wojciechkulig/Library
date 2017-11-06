angular.module('httpModule')
.factory('userService',['$http','authService',function($http,authService){
	var userService = {};
	userService.getUser = function(userId){
		  return authService.getAuthorizationHeader().then(authHeader => { 
			    return $http.get("/users/"+userId, {headers: authHeader}); 
			  }); 
	};
	return userService;
	}]);