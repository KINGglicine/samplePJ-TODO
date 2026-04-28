package sample.common.service;

import sample.common.dao.entity.Login;

public interface LoginService {

    Login loginCheck(Login login);

    void insert(Login login);
}