package sample.thymeleaf.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    // ログイン画面表示
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }
}