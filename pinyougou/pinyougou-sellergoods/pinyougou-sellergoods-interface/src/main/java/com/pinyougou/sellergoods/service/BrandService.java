package com.pinyougou.sellergoods.service;

import com.pinyougou.pojo.TbBrand;
import com.pinyougou.service.BaseService;
import com.pinyougou.vo.PageResult;

import java.util.List;
import java.util.Map;

public interface BrandService extends BaseService<TbBrand> {

    @Deprecated
    List<TbBrand> queryAll();

    /**
     * 分页查询品牌列表
     * @param page 页号
     * @param rows 页大小
     * @return
     */
    List<TbBrand> testPage(Integer page, Integer rows);

    /**
     * 根据查询条件分页查询品牌列表
     * @param page 页号
     * @param rows 页大小
     * @param brand 查询条件
     * @return 分页对象
     */
    PageResult search(Integer page, Integer rows, TbBrand brand);

    /**
     * 查询select2需要的所有品牌的数据（结构要：[{id,text}]）
     * @return select2需要的所有品牌的数据
     */
    List<Map<String,String>> selectOptionList();
}
