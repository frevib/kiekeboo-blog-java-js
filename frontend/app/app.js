var kiekebooApp = angular.module('kiekebooapp', ['ngRoute']);

kiekebooApp.config(['$routeProvider', '$httpProvider', function($routeProvider, $httpProvider) {
    $routeProvider
        .when('/', {
            templateUrl: 'app/views/blog_list.html',
            controller: 'bloglistController'
        })
        .when('/showpost/:blogpostId', {
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
        .when('/login', {
            templateUrl: 'app/views/login.html'
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
    var url = 'http://ubuntuserver:8080/kiekeboo-app-1.0-SNAPSHOT/blog/postblogitem';
    $scope.postBlogItem = function() {
        var data = {
                title: $scope.title,
                contents: $scope.contents
            };
        $http.post(url, data)
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
    var url = 'http://ubuntuserver:8080/kiekeboo-app-1.0-SNAPSHOT/blog/getblogpost/' + $routeParams.blogpostId;
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

kiekebooApp.controller('loginController', function($scope, $http, $window) {
    var url = 'http://ubuntuserver:8080/kiekeboo-app-1.0-SNAPSHOT/login/login';
    $scope.submit = function() {
        var data = {
            username: $scope.user.username,
            password: $scope.user.password
        };
        $http.post(url, data)
            .success(function(data, status) {
                console.log("awesome login!");
                $window.sessionStorage.token = data.value;
            })
            .error(function(data, status) {
                console.log("login failed....")
                delete $window.sessionStorage.token;
            });
    };

});