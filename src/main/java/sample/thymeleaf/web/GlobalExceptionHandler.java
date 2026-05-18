package sample.thymeleaf.web;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import sample.common.exception.TaskNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TaskNotFoundException.class)
    public String handleTaskNotFound(

            TaskNotFoundException e,

            Model model) {

        model.addAttribute(
                "message",
                e.getMessage());

        return "error/404";
    }
}