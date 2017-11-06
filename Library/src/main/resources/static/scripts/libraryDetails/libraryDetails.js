var libraryDetails = angular.module('libraryDetails',['ui.router','httpModule']);
libraryDetails.config(['$stateProvider',function($stateProvider){
	$stateProvider
	.state('libraryDetails', {
		url:'/libraries/:libraryId',
		template:'<library-details></library-details>'
	});
}]);
	