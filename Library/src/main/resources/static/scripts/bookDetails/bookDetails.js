var bookDetails = angular.module('bookDetails',['ui.router','httpModule'])
	.config(['$stateProvider',function($stateProvider){
		$stateProvider.state('bookDetails',{
			template: '<book-details></book-details>',
			url:'/books/:bookId'
		})
	}]);