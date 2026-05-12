package sample.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sample.common.dao.entity.Task;
import sample.common.dao.mapper.TaskMapper;
import sample.common.service.TaskService;

@Service
public class TaskServiceImpl
        implements TaskService {

    @Autowired
    private TaskMapper taskMapper;

    // 一覧
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

    // 件数
    @Override
    public int countAll(
            String userName) {

        return taskMapper.countAll(
                userName);
    }

    // 詳細
    @Override
    public Task findById(
            Long id,
            String userName) {

        return taskMapper.findById(
                id,
                userName);
    }

    // 登録
    @Override
    public void insert(Task task) {
        taskMapper.insert(task);
    }

    // 更新
    @Override
    public void update(Task task) {
        taskMapper.update(task);
    }

    // 削除
    @Override
    public void delete(
            Long id,
            String userName) {

        taskMapper.delete(
                id,
                userName);
    }
}