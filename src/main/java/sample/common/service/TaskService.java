package sample.common.service;

import java.util.List;

import sample.common.dao.entity.Task;

public interface TaskService {

    // 一覧
    List<Task> findPage(
            int limit,
            int offset,
            String userName);

    // 件数
    int countAll(String userName);

    // 詳細
    Task findById(
            Long id,
            String userName);

    // 登録
    void insert(Task task);

    // 更新
    void update(Task task);

    // 削除
    void delete(
            Long id,
            String userName);
}