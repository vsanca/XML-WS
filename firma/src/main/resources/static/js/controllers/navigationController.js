/**
 * Created by Marko on 6/13/2017.
 */
angular.module('firmaApp.NavigationController',[])
    .controller('NavigationController', function ($scope, $location, $rootScope, $mdDialog, $localStorage, $mdToast, LoginFactory) {

        if($localStorage.curNav == null)
            $scope.currentNavItem = 'Pocetna';
        else
            $scope.currentNavItem = $localStorage.curNav;

        $scope.saveNav = function (data) {
            $localStorage.curNav = data;
        }

        if($localStorage.tip != null){
            $scope.showFirma = true;
        }

        if($localStorage.tip != null)
            $scope.showOdjaviSe = true;



        $scope.odjaviSe = function () {
            $localStorage.logged = null;
            $localStorage.tip = null;
            $localStorage.curNav = 'Pocetna';
            $scope.currentNavItem = 'Pocetna';
            $location.path("/");

            $scope.showOdjaviSe = false;
            $scope.showFirma = false;

            $mdToast.show(
                $mdToast.simple()
                    .textContent('Успешно сте се одјавили!')
                    .hideDelay(3000)
                    .position('top center')
                    .theme('success-toast')
            );
        };

        $scope.openPrijavaDijalog = function(ev) {
            $mdDialog.show({
                controller: LoginController,
                templateUrl: 'html/login.html',
                parent: angular.element(document.body),
                targetEvent: ev,
                clickOutsideToClose:true
            })
                .then(function(answer) {
                    if(answer == 'Firma')
                        $scope.showFirma = true;

                    $scope.showOdjaviSe = true;
                });
        }


        function LoginController($scope, $mdDialog) {
            $scope.user = {username:null, password:null};
            $scope.login = function(){
                LoginFactory.login($scope.user).success(function(data){
                    if(data){
                        $localStorage.logged = data;
                        $localStorage.tip = "Firma";

                        $mdToast.show(
                            $mdToast.simple()
                                .textContent('Успешно сте се пријавили!')
                                .hideDelay(3000)
                                .position('top center')
                                .theme('success-toast')
                        );

                        $mdDialog.hide("Firma");
                    }})
                    .error(function(data){
                        $scope.user.password = '';
                        $mdToast.show(
                            $mdToast.simple()
                                .textContent('Погрешно кориснички име или лозинка!')
                                .hideDelay(3000)
                                .position('top center')
                                .theme('error-toast')
                        );
                    });
            };

            $scope.cancel = function() {
                $mdDialog.cancel();
            };
        }
    });