package com.pinyougou.sellergoods.service;

import com.pinyougou.pojo.TbSpecification;
import com.pinyougou.service.BaseService;
import com.pinyougou.vo.PageResult;
import com.pinyougou.vo.Specification;

import java.io.Serializable;

public interface SpecificationService extends BaseService<TbSpecification> {

    PageResult search(Integer page, Integer rows, TbSpecification specification);

    void add(Specification specification);

    Specification findSpecification(Serializable id);

    void update(Specification specification);

    /**
     * 删除规格及其对应的规格选项
     * @param ids 规格id集合
     */
    void deleteSpecificationByIds(Long[] ids);
}