package com.pinyougou.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.sellergoods.service.BrandService;
import com.pinyougou.vo.PageResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/brand")
@RestController // 组合注解：@ResponseBody @Controller
public class BrandController {

    @Reference
    private BrandService brandService;

    /**
     * 分页查询品牌列表
     * @param page 页号
     * @param rows 页大小
     * @return
     */
    @GetMapping("/testPage")
    public List<TbBrand> testPage(Integer page, Integer rows){
        //return brandService.testPage(page, rows);
        return (List<TbBrand>)brandService.findPage(page, rows).getRows();
    }

    @GetMapping("/findAll")
    public List<TbBrand> findAll(){
        //return brandService.queryAll();
        return brandService.findAll();
    }

    /**
     * 根据分页信息查询品牌列表
     * @param page 页号
     * @param rows 页大小
     * @return 分页对象
     */
    @GetMapping("/findPage")
    public PageResult findPage(@RequestParam(value = "page", defaultValue = "1")Integer page,
                               @RequestParam(value = "rows", defaultValue =  "10")Integer rows){
        return brandService.findPage(page, rows);
    }
}
