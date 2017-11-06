var searchBook = angular.module('searchBook',['ui.router','httpModule','ngMessages']);
searchBook.config(['$stateProvider',function($stateProvider){
	$stateProvider
	.state('searchBook', {
		url:'/books',
		template:'<search-book></search-book>'
	});
}]);