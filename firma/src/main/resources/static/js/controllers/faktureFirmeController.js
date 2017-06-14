/**
 * Created by Marko on 6/14/2017.
 */
angular.module('firmaApp.FaktureFirmeController',[])
    .controller('FaktureFirmeController', function ($scope, $localStorage, $location, $rootScope, $mdToast, FirmaFactory, ProizvodFactory, $mdDialog) {

        if($localStorage.tip != "Firma")
            $location.path("/");

        $scope.fakture = [];

        var webSocket;
        $scope.openSocket = function(){
            if(webSocket !== undefined && webSocket.readyState !== WebSocket.CLOSED){
                console.log("WebSocket is already opened.");
                return;
            }

            webSocket = new WebSocket("ws://localhost:9000/firma/" + $localStorage.logged.user.id);

            webSocket.onopen = function(event){
                if(event.data === undefined)
                    return;

                console.log(event.data);
            };

            webSocket.onmessage = function(event){
                console.log(event.data);
                $scope.fakture = event.data;
            };

            webSocket.onclose = function(event){
                console.log("Connection closed");
            };
        }
        $scope.openSocket();


    });