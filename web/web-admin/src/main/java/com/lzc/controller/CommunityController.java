package com.lzc.controller;

import com.github.pagehelper.PageInfo;
import com.lzc.base.BaseController;
import com.lzc.entity.Community;
import com.lzc.entity.Dict;
import com.lzc.service.CommunityService;
import com.lzc.service.DictService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/community")
public class CommunityController extends BaseController {

    @DubboReference
    private CommunityService communityService;

    @DubboReference
    private DictService dictService;

    @RequestMapping
    public String findPage(Model model, @RequestParam Map<String, Object> filters) {
        //获取所有的区域信息
        List<Dict> areaList = dictService.findDictListByParentDictCode("beijing");
        //获取小区的分页数据
        PageInfo<Community> page = communityService.findPage(filters);
        model.addAttribute("page", page);
        model.addAttribute("areaList", areaList);
        if (filters.size() == 0) {
            filters.put("areaId", "");
            filters.put("plateId", "");
        }
        model.addAttribute("filters", filters);
        return "community/index";
    }

    private final static String PAGE_CREATE = "community/create";
    @RequestMapping("create")
    public String create(Model model) {
        List<Dict> areaList = dictService.findDictListByParentDictCode("beijing");
        model.addAttribute("areaList", areaList);
        return PAGE_CREATE;
    }

    @PostMapping("/save")
    public String save(Community community, Model model) {
        communityService.save(community);
        return successPage(model, "添加小区成功");
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Community community = communityService.getById(id);
        model.addAttribute("community", community);
        //获取所有的区域信息
        List<Dict> areaList = dictService.findDictListByParentDictCode("beijing");
        model.addAttribute("areaList", areaList);
        return "community/edit";
    }

    @RequestMapping("/update")
    public String update(Community community, Model model) {
        communityService.update(community);
        return successPage(model, "修改小区成功");
    }

    @RequestMapping("/detele/{id}")
    public String delete(@PathVariable Long id) {
        communityService.delete(id);
        return "redirect:/community";
    }

}
