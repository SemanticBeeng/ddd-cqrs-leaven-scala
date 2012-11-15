function ProductListController($scope, Products) {

        $scope.products = Products.query();
    }