var libraryApp = angular.module('LibraryApp',[
	"ui.router","ngResource","libraryNavbar","auth","librariesList","libraryDetails","searchBook","bookDetails","loginView","userProfile"]);
libraryApp.config(['$stateProvider','$urlRouterProvider',function($stateProvider,$urlRouterProvider){
	$urlRouterProvider.otherwise('/libraries');
	$stateProvider
		.state('librariesList', {
			url:'/libraries',
			template:'<libraries-list></libraries-list>'
		});
}]);
