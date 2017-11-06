angular.module('bookDetails')
.controller('bookDetailsController',['bookService','$stateParams','$rootScope','$window',function(bookService,$stateParams,$rootScope,$window){
	var self = this;
	bookService.getBookById($stateParams.bookId).then(function(resp){
		self.book = resp.data;
	});
	bookService.getReviews($stateParams.bookId).then(function(resp){
		self.reviews = resp.data;
	})
	self.loggedIn = $window.localStorage.getItem('access_token');
	$rootScope.$on('localStorageUpdated',function(event,access_token){
		self.loggedIn = access_token;
	});	
	self.makeReservation = function(){
		var reservation = {};
		reservation.book = self.book;
		bookService.addReservation(reservation).then(function(resp){
			
		});
	};
	self.ratingOptions = [];
	for(var i =1; i<=10;i++){
		self.ratingOptions.push(i);
	}
	self.review = {
			book:self.book,
			description:"",
			rating:1,
	};
	
	self.addReview = function(){
		self.review.book = self.book,
		bookService.addReview(self.review).then(function(resp){
			return bookService.getReviews(self.book.id);
		}).then(function(resp){
			self.reviews = resp.data;
		});
	};
}]);