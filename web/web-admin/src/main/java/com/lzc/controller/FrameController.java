package com.lzc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrameController {
    @RequestMapping("/")
    public String index() {
        return "frame/index";
    }

    @RequestMapping("/main")
    public String main() {
        return "frame/main";
    }
}
