angular.module('leaven', ['productListServices']).
    config(['$routeProvider', function($routeProvider) {

        $routeProvider.
            when('/products/', {templateUrl: 'productList.html', controller: ProductListController}).
            otherwise({redirectTo: '/products/'});
}]);