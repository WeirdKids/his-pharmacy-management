package com.pharmacy.management.service.Impl;

import com.pharmacy.management.bean.User;
import com.pharmacy.management.dao.UserDao;
import com.pharmacy.management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 徐奥飞
 * @date 2019-11-5 8:15
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public User getByUserName(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User get(String username, String password) {
        return userDao.getByUsernameAndPassword(username, password);
    }

    @Override
    public void updateState(int id, String state) {
        userDao.updateStateById(id, state);
    }
}
