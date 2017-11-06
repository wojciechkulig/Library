angular.module('libraryDetails')
	.controller('libraryDetailsController',['libraryService','$stateParams',function(libraryService,$stateParams){
		var self = this;
		libraryService.getLibrary($stateParams.libraryId).then(function(resp){
			self.library = resp.data;
		});
	}]);