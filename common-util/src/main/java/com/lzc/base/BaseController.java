package com.lzc.base;

import org.springframework.ui.Model;

/**
 * Date:2022/10/31
 * Author:ybc
 * Description:
 */
public class BaseController {

    public String successPage(Model model, String messagePage){
        model.addAttribute("messagePage", messagePage);
        return "common/successPage";
    }

}
