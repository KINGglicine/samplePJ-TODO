package sample.common.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import sample.common.dao.entity.Task;

@Mapper
public interface TaskMapper {

    // 一覧
    List<Task> findPage(
            @Param("limit") int limit,

            @Param("offset") int offset,

            @Param("userName") String userName);

    // 件数
    int countAll(
            @Param("userName")
            String userName);

    // 詳細
    Task findById(
            @Param("id") Long id,

            @Param("userName")
            String userName);

    // 登録
    void insert(Task task);

    // 更新
    void update(Task task);

    // 削除
    void delete(
            @Param("id") Long id,

            @Param("userName")
            String userName);
}