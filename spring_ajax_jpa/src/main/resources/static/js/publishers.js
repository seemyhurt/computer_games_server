var app = angular.module('publishers', []);

app.controller("PublishersController", function ($scope, $http) {
    
    $scope.getPublishers = function () {
        $http.get('/ComputerGames/publishers')
            .then(function (response) {
                $scope.publishersList = response.data;
            }, function (error) {
                console.error(error.data);
            });
    };

    $scope.addPublisher = function() {

        const params = new URLSearchParams();

        params.append('name', $scope.newPublisher.name);
        params.append('country', $scope.newPublisher.country);

        console.log(params.toString());

        $http({
            method: 'POST',
            url: '/ComputerGames/publishers',
            data: params.toString(),
            headers: { 'Content-Type': 'application/x-www-form-urlencoded'}
        })
        .then(function(response) {
            console.log(response.data);
            $scope.getPublishers();
        }, function(error) {
            console.error(error.data);
        });
    };

    $scope.deletePublisher = function(id) {
        $http.delete('/ComputerGames/publishers/' + id)
            .then(function(response) {
                console.log(response.data);
                $scope.getPublishers();
            }, function(error) {
                console.error(error.data);
            });
    }

    $scope.getPublishers();
});