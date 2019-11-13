package com.pharmacy.management.service;

import com.pharmacy.management.bean.User;

/**
 * @author 徐奥飞
 * @date 2019-11-5 8:10
 */

public interface UserService {
    public User getByName(String username);
    public User get(String username, String password);
}
