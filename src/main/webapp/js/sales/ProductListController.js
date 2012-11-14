function ProductListController($scope, $http) {

    $http.get('products/').success(function(data) {
        $scope.products = data
    });
}