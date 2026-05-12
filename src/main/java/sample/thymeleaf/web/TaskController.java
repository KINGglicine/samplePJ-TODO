package sample.thymeleaf.web;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

import sample.common.dao.entity.Task;
import sample.common.service.TaskService;

@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;

    // 一覧
    @GetMapping("/tasks")
    public String list(

            @RequestParam(
                    name = "page",
                    defaultValue = "1")
            int page,

            Authentication auth,

            Model model) {

        int limit = 7;
        int offset = (page - 1) * limit;

        // ログインユーザー
        String loginUser =
                auth.getName();

        model.addAttribute(
                "taskList",

                taskService.findPage(
                        limit,
                        offset,
                        loginUser));

        int totalCount =
                taskService.countAll(
                        loginUser);

        int totalPage =
                (int) Math.ceil(
                        (double) totalCount / limit);

        model.addAttribute(
                "currentPage",
                page);

        model.addAttribute(
                "totalPage",
                totalPage);

        return "tasks/list";
    }

    // 新規画面
    @GetMapping("/tasks/form-new")
    public String showCreateForm(
            Model model) {

        model.addAttribute(
                "task",
                new Task());

        return "tasks/form-new";
    }

    // 編集画面
    @GetMapping("/tasks/edit/{id}")
    public String showEdit(

    		@PathVariable("id") Long id,

            Authentication auth,

            Model model) {

        String loginUser =
                auth.getName();

        model.addAttribute(
                "task",

                taskService.findById(
                        id,
                        loginUser));

        return "tasks/form-edit";
    }

    // 更新
    @PostMapping("/tasks/update")
    public String update(

            @Valid Task task,

            BindingResult br,

            Authentication auth) {

        // バリデーションエラー
        if (br.hasErrors()) {

            return "tasks/form-edit";
        }

        String loginUser =
                auth.getName();

        task.setUserName(loginUser);

        taskService.update(task);

        return "redirect:/tasks";
    }

    // 登録
    @PostMapping("/tasks/create")
    public String create(

            @Valid Task task,

            BindingResult br,

            Authentication auth) {

        // バリデーションエラー
        if (br.hasErrors()) {

            return "tasks/form-new";
        }

        // ログインユーザー
        String loginUser =
                auth.getName();

        // 強制セット
        task.setUserName(loginUser);

        taskService.insert(task);

        return "redirect:/tasks";
    }

    // 削除
    @PostMapping("/tasks/delete/{id}")
    public String delete(

    		@PathVariable("id") Long id,

            Authentication auth) {

        String loginUser =
                auth.getName();

        taskService.delete(
                id,
                loginUser);

        return "redirect:/tasks";
    }
}