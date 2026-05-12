package sample.common.dao.mapper;

import org.apache.ibatis.annotations.Mapper;

import sample.common.dao.entity.Login;

@Mapper
public interface LoginMapper {

    // ユーザー登録
    void insert(Login login);

    // username検索
    Login findByUserName(String userName);
}