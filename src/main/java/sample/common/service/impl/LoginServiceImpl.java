package sample.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import sample.common.dao.entity.Login;
import sample.common.dao.mapper.LoginMapper;
import sample.common.service.LoginService;



@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Login loginCheck(Login login) {

        Login storedUser =
                loginMapper.findByUserName(login.getUserName());

        if (storedUser == null) {
            return null;
        }

        boolean matched = passwordEncoder.matches(
                login.getPassword(),
                storedUser.getPassword());

        return matched ? storedUser : null;
    }

    @Override
    public void insert(Login login) {

        login.setPassword(
                passwordEncoder.encode(login.getPassword()));

        loginMapper.insert(login);
    }
    @Override
    public Login findByUserName(String userName) {

        return loginMapper.findByUserName(userName);
    }
}