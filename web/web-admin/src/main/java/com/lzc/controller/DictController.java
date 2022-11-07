package com.lzc.controller;

import com.lzc.entity.Dict;
import com.lzc.result.Result;
import com.lzc.service.DictService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("dict")
public class DictController {

    @DubboReference
    private DictService dictService;

    @RequestMapping
    private String index() {
        return "dict/index";
    }

    @RequestMapping("/findZnodes")
    @ResponseBody
    public Result findZnodes(@RequestParam(defaultValue = "0") Long parentId) {
        List<Map<String, Object>> znodes = dictService.findZnodes(parentId);
        return Result.ok(znodes);
    }

    @RequestMapping("/findDictListByParentId/{parentId}")
    @ResponseBody
    public Result findDictListByParentId(@PathVariable Long parentId) {
        List<Dict> dictList = dictService.findDictListByParentId(parentId);
        return Result.ok(dictList);
    }

}
