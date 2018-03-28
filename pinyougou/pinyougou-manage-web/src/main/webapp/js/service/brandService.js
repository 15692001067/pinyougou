//定义业务对象
app.service("brandService", function ($http) {
    this.findAll = function () {
        return $http.get("../brand/findAll.do");
    };


    //根据页号和页大小查询品牌列表
    this.findPage = function (page, rows) {
        return $http.get("../brand/findPage.do?page=" + page + "&rows=" + rows);
    };

    //新增
    this.add = function (entity) {
        return $http.post("../brand/add.do", entity);
    };
    //更新
    this.update = function (entity) {
        return $http.post("../brand/update.do", entity);
    };

    //根据主键查询
    this.findOne = function (id) {
        return $http.get("../brand/findOne.do?id=" + id);
    };

    //批量删除
    this.delete = function (selectedIds) {
        return $http.get("../brand/delete.do?ids=" + selectedIds);
    };

    //分页查询品牌列表
    this.search = function (page, rows, searchEntity) {
        return $http.post("../brand/search.do?page=" + page + "&rows=" + rows, searchEntity);
    };
});