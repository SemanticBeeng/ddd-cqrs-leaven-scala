angular.module('leaven', ['productListServices']).
    config(['$routeProvider', function($routeProvider) {

        $routeProvider.
            when('/products/', {templateUrl: 'template/sales/productList.html', controller: ProductListController}).
            otherwise({redirectTo: '/products/'});
}]);