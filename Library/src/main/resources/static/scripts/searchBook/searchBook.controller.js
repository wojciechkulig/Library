angular.module('searchBook')
	.controller('searchBoookController',['bookService',function(bookService){
		var self = this;
		self.title = "";	
		self.submit = function(){
			bookService.getBooksByTitle(self.title).then(function(resp){
			self.books = resp.data;
		})
	};
}]);