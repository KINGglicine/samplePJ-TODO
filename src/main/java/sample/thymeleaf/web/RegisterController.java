package sample.thymeleaf.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import sample.common.dao.entity.Login;
import sample.common.service.LoginService;

@Controller
public class RegisterController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(Login login) {

        loginService.insert(login);

        return "redirect:/login";
    }
}