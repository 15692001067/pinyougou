//定义处理器
app.controller("brandController", function ($scope, $http, $controller, brandService) {

    //继承baseController的方法
    $controller("baseController", {$scope:$scope});

    $scope.findAll = function () {
        brandService.findAll().success(function (response) {
            $scope.list = response;
        });
    };

    //根据页号和页大小查询品牌列表
    $scope.findPage = function (page, rows) {
        brandService.findPage().success(function (response) {
            //设置列表数据
            $scope.list = response.rows;
            //更新分页组件的总记录数
            $scope.paginationConf.totalItems = response.total;
        });
    };

    //保存
    $scope.save = function () {
        var obj;
        if($scope.entity.id != null) {
            obj = brandService.update($scope.entity);
        } else {
            obj = brandService.add($scope.entity);
        }

        obj.success(function (response) {
            if(response.success){
                //重新刷新列表
                $scope.reloadList();
            } else {
                alert(response.message);
            }
        });
    };

    //根据主键查询
    $scope.findOne = function (id) {
        brandService.findOne(id).success(function (response) {
            $scope.entity = response;
        });
    };

    //批量删除
    $scope.delete = function () {
        if($scope.selectedIds.length < 1){
            alert("请先选择要删除的记录");
            return;
        }
        if(confirm("确定要删除选中的记录吗")){
            brandService.delete($scope.selectedIds).success(function (response) {
                if(response.success){
                    //刷新列表
                    $scope.reloadList();
                    //清空选择的数组
                    $scope.selectedIds = [];
                } else {
                    alert(response.message);
                }

            });
        }
    };

    //搜索条件
    $scope.searchEntity = {};

    //分页查询品牌列表
    $scope.search = function (page, rows) {
        brandService.search(page, rows, $scope.searchEntity).success(function (response) {
            $scope.list = response.rows;
            $scope.paginationConf.totalItems = response.total;
        });
    };

});
