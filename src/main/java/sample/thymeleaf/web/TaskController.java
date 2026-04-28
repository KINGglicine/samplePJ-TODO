package sample.thymeleaf.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sample.common.dao.entity.Task;
import sample.common.service.TaskService;

@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/tasks")
    public String list(
            @RequestParam(name = "page", defaultValue = "1") int page,
            Model model) {

        int limit = 7;
        int offset = (page - 1) * limit;

        model.addAttribute("taskList",
                taskService.findPage(limit, offset));

        int totalCount = taskService.countAll();

        int totalPage =
                (int) Math.ceil((double) totalCount / limit);

        model.addAttribute("currentPage", page);
        model.addAttribute("totalPage", totalPage);

        return "tasks/list";
    }

    @GetMapping("/tasks/form-new")
    public String showCreateForm() {
        return "tasks/form-new";
    }

    @GetMapping("/tasks/edit/{id}")
    public String showEdit(@PathVariable Long id, Model model) {
        model.addAttribute("task", taskService.findById(id));
        return "tasks/form-edit";
    }

    @PostMapping("/tasks/update")
    public String update(Task task) {
        taskService.update(task);
        return "redirect:/tasks";
    }
    @PostMapping("/tasks/create")
    public String create(Task task) {
        taskService.insert(task);
        return "redirect:/tasks";
    }
    
    @PostMapping("/tasks/delete/{id}")
    public String delete(@PathVariable Long id) {
        taskService.delete(id);
        return "redirect:/tasks";
    }
}