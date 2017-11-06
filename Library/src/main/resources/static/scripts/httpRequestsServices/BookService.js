angular.module('httpModule')
	.factory('bookService',['$http','authService',function($http,authService){
		var bookService = {};
		bookService.getBooksByTitle = function(title){
			return $http.get('/books?title='+title);
		};
		bookService.getBookById = function(id){
			return $http.get('/books/'+id);
		};
		bookService.addReservation = function(reservation){
			return authService.getAuthorizationHeader().then(authHeader =>{
				return $http.post('/books/'+reservation.book.id+'/reservations',reservation,{headers: authHeader});
			});
		};
		
		bookService.getReviews = function(id){
			return $http.get('/books/'+id+'/reviews');
		}
		
		bookService.addReview = function(review){
			return authService.getAuthorizationHeader().then(authHeader =>{
				return $http.post('/books/'+review.book.id+'/reviews',review,{headers: authHeader});
			});
		};
		return bookService;
		}]);