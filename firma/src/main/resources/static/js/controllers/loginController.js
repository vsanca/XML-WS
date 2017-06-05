/**
 * Created by Hp on 6/5/2017.
 */

angular.module('firmaApp.LoginController',[])
    .controller('LoginController', function ($localStorage, $scope, $location, LoginFactory) {

        function init() {
            $scope.user = {};
            $scope.user.username = "";
            $scope.user.password = "";
        }

        init();

        $scope.login = function () {
            LoginFactory.login($scope.user).success(function (data) {
                $localStorage.loggedUser = data;
                console.log('Logovo se');
                //$location.path(...);
            }).error(function () {
                alert('Login unsuccessful!');
            })
        };

    });
