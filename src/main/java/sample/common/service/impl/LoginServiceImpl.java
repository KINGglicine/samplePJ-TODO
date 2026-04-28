// LoginServiceImpl.java
package sample.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sample.common.dao.entity.Login;
import sample.common.dao.mapper.LoginMapper;
import sample.common.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;

    @Override
    public Login loginCheck(Login login) {
        return loginMapper.findByUserNameAndPassword(login);
    }
    @Override
    public void insert(Login login) {
        loginMapper.insert(login);
    }
}