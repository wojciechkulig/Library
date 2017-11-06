angular.module('httpModule').
	factory('libraryService',['$http',function($http){
		var libraryService = {};
		libraryService.getAllLibraries = function() {
			return $http.get('/libraries');
		};
		libraryService.getLibrary = function(id) {
			return $http.get('/libraries/'+id);
		};
		return libraryService;
		}]);