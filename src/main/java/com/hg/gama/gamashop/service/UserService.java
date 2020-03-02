package com.hg.gama.gamashop.service;

import com.hg.gama.gamashop.jpadao.UserRepositoryre;
import com.hg.gama.gamashop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("userService")
public class UserService {

    @Autowired
    UserRepositoryre userRepositoryre;

    public User findUserById(Integer userId) {
        return (User) userRepositoryre.findUserById(userId);
    }
    public User getUserByUsername(String username){
        return userRepositoryre.findByUsername(username);
    }
}
