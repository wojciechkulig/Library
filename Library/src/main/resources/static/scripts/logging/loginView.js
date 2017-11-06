var login = angular.module('loginView',['ui.router','httpModule','ngMessages'])
	.config(['$stateProvider',function($stateProvider){
		$stateProvider.state('login',{
			url:'/login',
			template: '<login-view></login-view>'
		})
	}]);