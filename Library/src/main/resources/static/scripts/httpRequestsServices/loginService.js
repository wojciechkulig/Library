angular.module('httpModule')
	.factory('loginService',['$http','$httpParamSerializer',function($http,$httpParamSerializer){
		var loginService = {};
		loginService.login = function(credentials){
			var encoded = btoa('client:password');
			var req = {
					method: 'POST',
					url: "/oauth/token",
					headers: {
						"Authorization": "Basic " + encoded,
						"Content-type": "application/x-www-form-urlencoded"
					},
					data: $httpParamSerializer(credentials)
			};
			return $http(req);
	}
		return loginService;
	}]);