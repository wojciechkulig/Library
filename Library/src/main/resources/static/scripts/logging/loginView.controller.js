angular.module('loginView').controller('loginViewController',['$window','loginService','$state','$rootScope',function($window,loginService,$state,$rootScope){
	var self = this;
	self.data = {
			grant_type:"password",
			username:"",
			password:"",
	};
	self.login = function(){
		
		loginService.login(self.data).then(function(resp){
			const storage = $window.localStorage;
			const token = resp.data;
			storage.setItem('access_token',token.access_token);
			storage.setItem('refresh_token',token.refresh_token);
			storage.setItem('user_id',token.id);
			var timeObject = new Date();
			timeObject = new Date(timeObject.getTime() + 1000*token.expires_in);
			storage.setItem('expires_in', timeObject); 
			$rootScope.$emit('localStorageUpdated',token.access_token);
			
		},function(errResponse){
			console.log(errResponse);
			$state.go('login');
		});
		$state.go('librariesList');
		
	};
	}]);