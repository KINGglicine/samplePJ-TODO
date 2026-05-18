package sample.thymeleaf.web;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.validation.Valid;

import sample.common.dao.entity.Task;
import sample.common.service.TaskService;
import sample.common.dao.form.TaskForm;

@Controller
public class TaskController {

    private static final int PAGE_SIZE = 10;

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // 一覧
    @GetMapping("/tasks")
    public String list(

            @RequestParam(
                    name = "page",
                    defaultValue = "1")
            int page,

            Authentication auth,

            Model model) {

        int offset = (page - 1) * PAGE_SIZE;

        // ログインユーザー
        String loginUser =
                auth.getName();

        model.addAttribute(
                "taskList",

                taskService.findPage(
                        PAGE_SIZE,
                        offset,
                        loginUser));

        int totalCount =
                taskService.countAll(
                        loginUser);

        int totalPage =
                (int) Math.ceil(
                        (double) totalCount / PAGE_SIZE);

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
                new TaskForm());

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

        Task task =
                taskService.findById(
                        id,
                        loginUser);

        TaskForm form = new TaskForm();

        form.setId(task.getId());
        form.setTitle(task.getTitle());
        form.setContent(task.getContent());

        model.addAttribute(
                "taskForm",
                form);

        return "tasks/form-edit";
    }

    // 更新
    @PostMapping("/tasks/update")
    public String update(
            @ModelAttribute("taskForm") @Valid TaskForm form,
            BindingResult br,
            Authentication auth,
            Model model) {

        if (br.hasErrors()) {
            model.addAttribute("taskForm", form);
            return "tasks/form-edit";
        }

        Task task = new Task();
        task.setId(form.getId());
        task.setTitle(form.getTitle());
        task.setContent(form.getContent());
        task.setUserName(auth.getName());

        taskService.update(task);

        return "redirect:/tasks";
    }

    // 登録
    @PostMapping("/tasks/create")
    public String create(

            @Valid TaskForm form,

            BindingResult br,

            Authentication auth) {

        if (br.hasErrors()) {
            return "tasks/form-new";
        }

        Task task = new Task();

        task.setTitle(form.getTitle());
        task.setContent(form.getContent());
        task.setUserName(auth.getName());

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