'use strict';

var kiekebooApp = angular.module('kiekebooapp', ['ngRoute']);

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

kiekebooApp.controller('addPostController', function($scope, $http, $window) {
    var url = 'http://ubuntuserver:8080/kiekeboo-app-1.0-SNAPSHOT/admin/addpost';
    if(!$window.sessionStorage.token) {
        $window.location.href = '/#/login'
    }
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
                $window.location.href = '/#/admin';
            })
            .error(function(data, status) {
                console.log("login failed....")
                delete $window.sessionStorage.token;
                $scope.response = data;
                            });
    };
});

kiekebooApp.factory('authInterceptor', function($rootScope, $q, $window) {
    return {
        request: function(config) {
            config.headers = config.headers || {};
            if($window.sessionStorage.token) {
                config.headers.Authorization = 'Bearer ' + $window.sessionStorage.token;
            }
            return config;
        },
        response: function(response) {
            return response;
        }
        //response: function(response) {
        //    if(response.status === 401) {
        //        if($window.location.href == '/#/admin') {
        //            console.log("auth 401")
        //            //$rootScope.loginMessage = 'You are not authenticated, please login';
        //            //$window.location.href = '/#/login';
        //        }
        //        return response || $q.when(response);
        //    }
        //}
    }

});

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
            //controller: 'loginController'
        })
        .otherwise({ redirectTo: '/' });
    $httpProvider.defaults.headers.common['Access-Control-Allow-Headers'] = "*";
    $httpProvider.interceptors.push('authInterceptor');
}]);

