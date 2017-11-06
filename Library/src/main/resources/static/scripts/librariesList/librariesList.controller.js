angular.module('librariesList')
	.controller('librariesListController',['libraryService',function(libraryService){
		var self = this;
		libraryService.getAllLibraries().then(function(resp){
			self.libraries = resp.data;
		});
	}]);