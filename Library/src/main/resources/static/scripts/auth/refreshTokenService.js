angular.module('auth').factory('authService',['$http','$q','$window',function($http,$q,$window){
	const storage = $window.localStorage;
	let cacheToken = {};
	return {
		getAuthorizationHeader() {
			if(cacheToken.access_token && new Date(cacheToken.expires_in) > new Date()){
				return $q.when({'Authorization': 'Bearer' + cacheToken.access_token});
			} else{
				cacheToken.access_token = storage.getItem('access_token');
				cacheToken.refresh_token = storage.getItem('refresh_token');
				cacheToken.expires_in = storage.getItem('expires_in');
				if(cacheToken.access_token && new Date(cacheToken.expires_in) > new Date()){
					return $q.when({'Authorization': 'Bearer '+ cacheToken.access_token});
				}else{
					return $http.post('/oauth/token',{'token': cacheToken.refresh_token}).then(
						response => {
							const token = response.data;
							cacheToken.access_token = token.access_token;
							storage.setItem('access_token',cacheToken.access_token);
			                cacheToken.refresh_token = token.refresh_token;
			                storage.setItem('refresh_token', cacheToken.refresh_token);
			                var timeObject = new Date();
			                timeObject = new Date(timeObject.getTime()+token.expires_in*1000);
			                cacheToken.expires_in = timeObject;
			                storage.setItem('expires_in', cacheToken.expires_in); 
			                return $q.when({'Authorization': 'Bearer ' + cacheToken.access_token});
						},
						err => {
							console.log('Error refreshing token'+ err)
						}
					);
				}
			}
		}
	}
}]);