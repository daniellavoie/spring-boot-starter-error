var app = angular.module("Errors", []);

var pollId = window.location.pathname.split("/")[2];

app.controller("ErrorsController", function($scope, $http, $location){	
	$scope.changePage = function(page){
		$location.search("page", page);
		
		$scope.loadErrors();
	}
	
	$scope.loadErrors = function(){
		$http.get("/error?" + $.param($location.search())).success(function(errorsPage){
			$scope.errorsPage = errorsPage;
		});
	};
	
	$scope.openError = function(error){
		location.href = "/error/" + error.id;
	};	

	var search = $location.search();
	if(search.page == undefined) $location.search("page", 0);
	if(search.resultPerPage == undefined) $location.search("resultPerPage", 50);
	if(search.sortBy == undefined) $location.search("sortBy", "timestamp");
	if(search.direction == undefined) $location.search("direction", "DESC");
	
	$scope.loadErrors();
});