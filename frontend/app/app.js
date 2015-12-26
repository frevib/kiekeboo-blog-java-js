var kiekebooApp = angular.module('kiekebooapp', ['ngRoute']);

kiekebooApp.config(['$routeProvider', '$httpProvider', function($routeProvider, $httpProvider) {
    $routeProvider
        .when('/', {
            templateUrl: 'app/views/blog_list.html',
            controller: 'bloglistController'
        })
        .when('/showpost/:id', {
            templateUrl: 'app/views/show_post.html',
            controller: 'showpostController'
        })
        .when('/blog_list', {
            templateUrl: 'app/views/blog_list.html',
            controller: 'bloglistController'
        })
        .when('/admin', {
            templateUrl: 'app/views/admin.html'
        })
        .otherwise({ redirectTo: '/' });
    $httpProvider.defaults.headers.common['Access-Control-Allow-Headers'] = "*";
}]);


kiekebooApp.controller('bloglistController', function($scope, $http) {
    var url = 'http://ubuntuserver:8080/kiekeboo-app-1.0-SNAPSHOT/blog/getlatestblogposts';
    // Limit size of contents text of front page
    $scope.limitText = 30;
    $http.get(url)
        .success(function(data, status, headers, config) {
            $scope.response = data;
        })
        .error(function(data, status, headers, config) {
            $scope.response = data;
        });
});

kiekebooApp.controller('addPostController', function($scope, $http) {
    $scope.postBlogItem = function() {
        var data = {
                title: $scope.title,
                contents: $scope.contents
            }
        $http.post('http://ubuntuserver:8080/kiekeboo-app-1.0-SNAPSHOT/blog/postblogitem', data)
            .success(function(data, status) {
                $scope.response = data;
                $scope.status = status;
            })
            .error(function(data, status) {
                $scope.response = data;
                $scope.status = status;
            });
    }
});

kiekebooApp.controller('showpostController', function($scope, $http, $routeParams) {
    var url = 'http://ubuntuserver:8080/kiekeboo-app-1.0-SNAPSHOT/blog/getblogpost/' + $routeParams.id;
    $http.get(url)
        .success(function(data, status, headers, config) {
            $scope.response = data;
            $scope.status = status;
        })
        .error(function(data, status, headers, config) {
            $scope.response = data;
            $scope.status = status;
        });
});