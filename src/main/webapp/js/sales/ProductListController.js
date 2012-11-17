function ProductListController($scope, basket, Products) {

    $scope.fetchData = function () {
        $scope.products = Products.query($scope.criteria,
            function (products) {
                $scope.refreshPaging(products);
            }
        );
    };

    $scope.doFilter = function () {
        $scope.criteria.pageNumber = 1;
        $scope.fetchData();
    };

    $scope.showAll = function () {
        $scope.criteria = {};
        $scope.pagination = {};
        $scope.fetchData();
    };

    $scope.nextPageState = function () {

        if ($scope.products.pageNumber < $scope.products.pageCount) {
            return 'enabled';
        }
        else {
            return 'disabled'
        }
    };

    $scope.isCurrentPage = function(page) {
        if ($scope.products.pageNumber == page) {
            return 'disabled';
        }
        else {
            return 'enabled'
        }
    }

    $scope.prevPageState = function () {
        if ($scope.products.pageNumber > 1) {
            return 'enabled';
        }
        else {
            return 'disabled'
        }
    };

    $scope.switchPage = function(page) {
        $scope.criteria.pageNumber = page;
        $scope.fetchData()
    }

    $scope.refreshPaging = function (products) {

        $scope.pageButtons = [];
        var pc = products.pageCount;

        for (var pageNo = 0; pageNo < pc; pageNo++) {
            $scope.pageButtons[pageNo] = {
                index: pageNo
            };
        }
    };

    $scope.reOrderBy = function(orderBy) {
        var currentOrder = $scope.criteria.orderBy;
        if (currentOrder == orderBy || currentOrder == null)
        {
            if ($scope.criteria.ascending == 'false')
            {
                $scope.criteria.ascending = 'true'
            }
            else
            {
                $scope.criteria.ascending = 'false';
            }
        }
        else
        {
            $scope.criteria.orderBy = orderBy;
        }
        $scope.fetchData();
    };

    $scope.addToBasket = function(product) {
        basket.add(product);
    }
    $scope.showAll();
}
