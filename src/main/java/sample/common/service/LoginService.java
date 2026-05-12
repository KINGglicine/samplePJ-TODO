package sample.common.service;

import sample.common.dao.entity.Login;

public interface LoginService {

    Login loginCheck(Login login);
    
    Login findByUserName(String userName);

    void insert(Login login);
}