package sample.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sample.common.dao.entity.Task;
import sample.common.dao.mapper.TaskMapper;
import sample.common.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskMapper taskMapper;

    @Override
    public List<Task> findAll() {
        return taskMapper.findAll();
    }

    @Override
    public List<Task> findPage(int limit, int offset) {
        return taskMapper.findPage(limit, offset);
    }

    @Override
    public int countAll() {
        return taskMapper.countAll();
    }

    @Override
    public int insert(Task task) {
        return taskMapper.insert(task);
    }

    @Override
    public Task findById(Long id) {
        return taskMapper.findById(id);
    }

    @Override
    public int update(Task task) {
        return taskMapper.update(task);
    }

    @Override
    public int delete(Long id) {
        return taskMapper.delete(id);
    }
}