package com.lzc.controller;

import com.github.pagehelper.PageInfo;
import com.lzc.base.BaseController;
import com.lzc.en.HouseStatus;
import com.lzc.entity.Community;
import com.lzc.entity.Dict;
import com.lzc.entity.House;
import com.lzc.service.CommunityService;
import com.lzc.service.DictService;
import com.lzc.service.HouseService;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/house")
public class HouseController extends BaseController {
    @DubboReference
    private HouseService houseService;
    @DubboReference
    private DictService dictService;
    @DubboReference
    private CommunityService communityService;

    @RequestMapping
    public String findPage(@RequestParam Map<String, Object> filters, Model model) {
        saveData2Model(model);
        model.addAttribute("filters", filters);
        //查询房源的分页信息
        PageInfo<House> page = houseService.findPage(filters);
        model.addAttribute("page", page);
        return "house/index";
    }

    @RequestMapping("/create")
    public String create(Model model) {
        saveData2Model(model);
        return "house/create";
    }

    @RequestMapping("/save")
    public String save(House house, Model model) {
        house.setStatus(HouseStatus.UNPUBLISHED.code);
        houseService.save(house);
        return successPage(model,"新增房源成功");
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        //获取要修改的房源信息
        House house = houseService.getById(id);
        model.addAttribute("house", house);
        saveData2Model(model);
        return "house/edit";
    }

    public void saveData2Model(Model model) {
        //获取所有的小区信息
        List<Community> communityList = communityService.findAll();
        //获取所有的户型信息
        List<Dict> houseTypeList = dictService.findDictListByParentDictCode("houseType");
        //获取所有的楼层信息
        List<Dict> floorList = dictService.findDictListByParentDictCode("floor");
        //获取所有的建筑结构信息
        List<Dict> buildStructureList = dictService.findDictListByParentDictCode("buildStructure");
        //获取所有的装修情况信息
        List<Dict> decorationList = dictService.findDictListByParentDictCode("decoration");
        //获取所有的朝向信息
        List<Dict> directionList = dictService.findDictListByParentDictCode("direction");
        //获取所有的房屋用途信息
        List<Dict> houseUseList = dictService.findDictListByParentDictCode("houseUse");
        model.addAttribute("communityList", communityList);
        model.addAttribute("houseTypeList", houseTypeList);
        model.addAttribute("floorList", floorList);
        model.addAttribute("buildStructureList", buildStructureList);
        model.addAttribute("decorationList", decorationList);
        model.addAttribute("directionList", directionList);
        model.addAttribute("houseUseList", houseUseList);
    }

}
