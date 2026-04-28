package sample.common.service;

import java.util.List;

import sample.common.dao.entity.Task;

public interface TaskService {

    List<Task> findAll();

    List<Task> findPage(int limit, int offset);

    int countAll();

    int insert(Task task);

    Task findById(Long id);

    int update(Task task);

    int delete(Long id);
}