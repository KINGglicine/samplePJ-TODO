// LoginController.java
package sample.thymeleaf.web;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import sample.common.dao.entity.Login;
import sample.common.service.LoginService;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(Login login, Model model, HttpSession session) {
    	
    	System.out.println("userName=" + login.getUserName());
    	System.out.println("password=" + login.getPassword());

        Login user = loginService.loginCheck(login);

        if (user != null) {
            session.setAttribute("loginUser", user);
            return "redirect:/tasks";
        }

        model.addAttribute("errorMsg", "ユーザー名またはパスワードが違います");
        return "login";
    }
}