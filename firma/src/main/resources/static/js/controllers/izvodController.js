/**
 * Created by Marko on 6/15/2017.
 */
angular.module('firmaApp.IzvodController',[])
    .controller('IzvodController', function ($scope, $localStorage, $location, $rootScope, $mdToast, IzvodFactory, $mdDialog) {

        if($localStorage.tip != "Firma")
            $location.path("/");

        $scope.datum = null;
        $scope.izvod = null;
        $scope.zahtevDTO = {"fid": null, "date": null};
        $scope.getIzvod = function(){
            $scope.zahtevDTO.fid = $localStorage.logged.firma.id;
            $scope.zahtevDTO.date = $scope.datum;
            IzvodFactory.salji_zahtev_za_izvod($scope.zahtevDTO).success(function (data) {
                $scope.izvod = data;
            });
        }

        $scope.openStavkaDialog = function(ev, stavka) {
            $mdDialog.show({
                controller: stavkeController,
                templateUrl: 'html/stavkaIzvoda.html',
                parent: angular.element(document.body),
                targetEvent: ev,
                clickOutsideToClose:true,
                locals:{stavka: stavka}
            })
        }

        function stavkeController($scope, $mdDialog, stavka) {
            $scope.s = stavka;

            $scope.cancel = function() {
                $mdDialog.cancel();
            };
        }
    });