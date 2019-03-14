package com.emigroup.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
public class IndexController {
    @RequestMapping("/")
    public String index() {
        return "forward:/emi.html";
    }

}
