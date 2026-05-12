package sample.thymeleaf.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

import sample.common.dao.entity.Login;
import sample.common.service.LoginService;

@Controller
public class RegisterController {

    @Autowired
    private LoginService loginService;

    // 登録画面
    @GetMapping("/register")
    public String showRegisterPage(
            Model model) {

        model.addAttribute(
                "login",
                new Login());

        return "register";
    }

    // 登録処理
    @PostMapping("/register")
    public String register(

            @Valid Login login,

            BindingResult br,

            Model model) {

        // バリデーションエラー
        if (br.hasErrors()) {

            return "register";
        }

        // ユーザー名重複チェック
        Login existsUser =
                loginService.findByUserName(
                        login.getUserName());

        if (existsUser != null) {

            model.addAttribute(
                    "errorMessage",
                    "そのユーザー名は既に登録されています");

            return "register";
        }

        // 登録
        loginService.insert(login);

        return "redirect:/login";
    }
}