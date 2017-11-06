var userProfile = angular.module('userProfile',['ui.router','httpModule'])
	.config(['$stateProvider',function($stateProvider){
		$stateProvider.state('userProfile',{
			url: '/userProfile',
			template:'<user-profile></user-profile>'
		})
	}]);