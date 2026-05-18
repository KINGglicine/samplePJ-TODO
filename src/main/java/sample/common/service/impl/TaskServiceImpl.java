package sample.common.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sample.common.dao.entity.Task;
import sample.common.dao.mapper.TaskMapper;
import sample.common.service.TaskService;
import sample.common.exception.TaskNotFoundException;

@Service
@Transactional(readOnly = true)
public class TaskServiceImpl implements TaskService {

    private final TaskMapper taskMapper;

    public TaskServiceImpl(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    @Override
    @Transactional
    public void insert(Task task) {
        taskMapper.insert(task);
    }

    @Override
    @Transactional
    public void update(Task task) {

        Task current =
                taskMapper.findById(
                        task.getId(),
                        task.getUserName());

        if (current == null) {
        	throw new TaskNotFoundException(
        	        "Task not found");
        }

        taskMapper.update(task);
    }

    @Override
    @Transactional
    public void delete(Long id, String userName) {

        Task current =
                taskMapper.findById(
                        id,
                        userName);

        if (current == null) {
        	throw new TaskNotFoundException(
        	        "Task not found");
        }

        taskMapper.delete(id, userName);
    }
    
    @Override
    public Task findById(Long id, String userName) {
        return taskMapper.findById(id, userName);
    }
    
    @Override
    public int countAll(String userName) {
        return taskMapper.countAll(userName);
    }
    
    @Override
    public List<Task> findPage(
            int limit,
            int offset,
            String userName) {

        return taskMapper.findPage(
                limit,
                offset,
                userName);
    }
}