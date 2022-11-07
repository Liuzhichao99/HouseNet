package com.lzc.controller;

import com.lzc.base.BaseController;
import com.lzc.entity.Role;
import com.lzc.service.RoleService;
import com.github.pagehelper.PageInfo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * Date:2022/10/29
 * Author:ybc
 * Description:
 * 列表功能-->/role
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

    @DubboReference
    private RoleService roleService;

    @RequestMapping
    public String findPage(Model model, @RequestParam Map<String, Object> filters){
        //调用service处理业务逻辑
        PageInfo<Role> page = roleService.findPage(filters);
        //在请求域中共享分页信息和filters
        model.addAttribute("page", page);
        model.addAttribute("filters", filters);
        //跳转到role/index.html
        return "role/index";
    }

    public String findAll(Model model){
        //调用service处理业务逻辑
        List<Role> roleList = roleService.findAll();
        //将角色集合在请求域中共享
        model.addAttribute("list", roleList);
        //跳转到role/index.html
        return "role/index";
    }

    @RequestMapping("/create")
    public String create(){
        return "role/create";
    }

    @RequestMapping("/save")
    public String save(Role role, Model model){
        //调用service处理业务逻辑
        roleService.save(role);
        return successPage(model, "新增角色成功");
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        //调用service处理业务逻辑，通过id查询角色信息
        Role role = roleService.getById(id);
        //将要修改的角色信息在请求域中共享
        model.addAttribute("role", role);
        //跳转到修改页面
        return "role/edit";
    }

    @RequestMapping("/update")
    public String update(Role role, Model model){
        //调用service处理业务逻辑
        roleService.update(role);
        return successPage(model, "修改角色成功");
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        //调用service处理业务逻辑
        roleService.delete(id);
        //重定向到列表功能
        return "redirect:/role";
    }

}
