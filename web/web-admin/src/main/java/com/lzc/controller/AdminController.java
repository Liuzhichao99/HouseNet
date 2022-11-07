package com.lzc.controller;

import com.github.pagehelper.PageInfo;
import com.lzc.base.BaseController;
import com.lzc.entity.Admin;

import com.lzc.service.AdminService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {

    @DubboReference
    private AdminService adminService;

    @RequestMapping
    public String findPage(Model model, @RequestParam Map<String, Object> filters) {
        PageInfo<Admin> page = adminService.findPage(filters);
        model.addAttribute("page", page);
        model.addAttribute("filters", filters);
        return "admin/index";
    }

    @RequestMapping("/create")
    public String create() {
        return "admin/create";
    }

    @RequestMapping("/save")
    public String save(Admin admin, Model model) {
        //调用service处理业务逻辑

        adminService.save(admin);
        return successPage(model, "新增用户成功");
    }

    @RequestMapping("edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Admin admin = adminService.getById(id);
        model.addAttribute("admin", admin);
        return "admin/edit";
    }

    @RequestMapping("update")
    public String update(Admin admin, Model model) {
        adminService.update(admin);
        return successPage(model, "修改用户成功");
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        adminService.delete(id);
        return "redirect:/admin";
    }

}
