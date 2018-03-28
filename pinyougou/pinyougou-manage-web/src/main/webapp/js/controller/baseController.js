//定义base处理器；后续可以被其它的处理器继承
app.controller("baseController", function ($scope) {

    // 初始化分页参数
    $scope.paginationConf = {
        currentPage:1,// 当前页号
        totalItems:10,// 总记录数
        itemsPerPage:10,// 页大小
        perPageOptions:[10, 20, 30, 40, 50],// 可选择的每页大小
        onChange: function () {// 当上述的参数发生变化了后触发
            $scope.reloadList();
        }
    };

    //重新加载
    $scope.reloadList = function () {
        $scope.search($scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage);
    };

    //当前选中的id数组
    $scope.selectedIds = [];

    $scope.updateSelection = function ($event, id) {
        //$event可以获取当前点击的元素

        if($event.target.checked){
            //如果是勾选的则需要将id加入数组
            $scope.selectedIds.push(id);
        } else {
            //如果是没有勾选则需要将id从数组中删除
            var idx = $scope.selectedIds.indexOf(id);
            //splice删除从那个索引号的元素，删除几个;参数1：索引号，参数2：删除个数
            $scope.selectedIds.splice(idx, 1);
        }

    };

    //将一个json格式字符串的某个属性的值遍历出来并使用 , 分隔返回字符串
    $scope.jsonToString = function (jsonStr, key) {
        var str = "";
        var jsonArray = JSON.parse(jsonStr);
        for (var i = 0; i < jsonArray.length; i++) {
            var obj = jsonArray[i];
            if(i > 0){
                str += ",";
            }
            str += obj[key];
        }
        return str;
    };
});
