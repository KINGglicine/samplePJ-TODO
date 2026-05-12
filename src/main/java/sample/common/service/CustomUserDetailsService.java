package sample.common.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

import sample.common.dao.entity.Login;
import sample.common.dao.mapper.LoginMapper;

@Service
public class CustomUserDetailsService
        implements UserDetailsService {

    @Autowired
    private LoginMapper loginMapper;

    @Override
    public UserDetails loadUserByUsername(
            String username)
            throws UsernameNotFoundException {

        // DB検索
        Login login =
                loginMapper.findByUserName(username);

        // ユーザー存在しない
        if (login == null) {

            throw new UsernameNotFoundException(
                    "User not found");
        }

        // Spring Security用User返却
        return User.builder()

                .username(login.getUserName())

                .password(login.getPassword())

                .roles("USER")

                .build();
    }
}