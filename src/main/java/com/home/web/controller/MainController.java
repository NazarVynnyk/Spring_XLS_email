package com.home.web.controller;

import com.home.service.Impl.MailService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
public class MainController {
    private static final Logger LOGGER = Logger.getLogger(MainController.class);

    private MailService mailService;

    @Autowired
    public MainController(MailService mailService) {
        this.mailService = mailService;
    }

    @GetMapping(value = "/")
    public String main() {

        return "homepage";
    }

    @PostMapping(value = "/sendMail")
    public String sendReport(HttpServletRequest request) {

        String email = request.getParameter("email");

        mailService.sendMail(email);
        return "success";

    }
}






