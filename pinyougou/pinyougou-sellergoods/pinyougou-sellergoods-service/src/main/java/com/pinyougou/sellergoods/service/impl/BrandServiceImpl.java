package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinyougou.mapper.BrandMapper;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.sellergoods.service.BrandService;
import com.pinyougou.service.impl.BaseServiceImpl;
import com.pinyougou.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service(interfaceClass = BrandService.class)
public class BrandServiceImpl extends BaseServiceImpl<TbBrand> implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public List<TbBrand> queryAll() {
        return brandMapper.queryAll();
    }

    @Override
    public List<TbBrand> testPage(Integer page, Integer rows) {
        //设置分页
        PageHelper.startPage(page, rows);
        //return brandMapper.selectAll();
        return brandMapper.select(null);
    }

    @Override
    public PageResult search(Integer page, Integer rows, TbBrand brand) {
        //1、设置分页
        PageHelper.startPage(page, rows);

        //2、创建查询查询
        Example example = new Example(TbBrand.class);

        Example.Criteria criteria = example.createCriteria();

        if(!StringUtils.isEmpty(brand.getFirstChar())){//根据首字母查询
            criteria.andEqualTo("firstChar", brand.getFirstChar());
        }

        if(!StringUtils.isEmpty(brand.getName())){//根据名称模糊查询
            criteria.andLike("name", "%" + brand.getName() + "%");
        }

        //3、查询
        List<TbBrand> list = brandMapper.selectByExample(example);

        PageInfo<TbBrand> pageInfo = new PageInfo<>(list);

        //4、返回分页对象
        return new PageResult(pageInfo.getTotal(), pageInfo.getList());
    }

    @Override
    public List<Map<String, String>> selectOptionList() {

        return brandMapper.selectOptionList();
    }
}
