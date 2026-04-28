package sample.common.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import sample.common.dao.entity.Task;

@Mapper
public interface TaskMapper {

    List<Task> findAll();

    List<Task> findPage(int limit, int offset);

    int countAll();

    Task findById(Long id);

    int insert(Task task);

    int update(Task task);

    int delete(Long id);
}