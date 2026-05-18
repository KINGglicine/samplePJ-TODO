package sample.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sample.common.dao.entity.Login;
import sample.common.dao.mapper.LoginMapper;
import sample.common.service.LoginService;

@Service
@Transactional(readOnly = true)
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void insert(Login login) {

        // パスワード暗号化
        login.setPassword(
                passwordEncoder.encode(
                        login.getPassword()));

        loginMapper.insert(login);
    }
    
    @Override
    public Login findByUserName(String userName) {
        return loginMapper.findByUserName(userName);
    }
    
    @Override
    public Login loginCheck(Login login) {

        Login dbLogin =
                loginMapper.findByUserName(
                        login.getUserName());

        // ユーザーなし
        if (dbLogin == null) {
            return null;
        }

        // パスワード一致
        if (passwordEncoder.matches(
                login.getPassword(),
                dbLogin.getPassword())) {

            return dbLogin;
        }

        return null;
    }
    
}