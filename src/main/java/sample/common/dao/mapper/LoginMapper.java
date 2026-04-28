// LoginMapper.java
package sample.common.dao.mapper;

import org.apache.ibatis.annotations.Mapper;

import sample.common.dao.entity.Login;

@Mapper
public interface LoginMapper {

    Login findByUserNameAndPassword(Login login);
    
    int insert(Login login);

}