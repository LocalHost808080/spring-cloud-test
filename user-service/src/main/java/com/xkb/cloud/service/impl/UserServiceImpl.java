package com.xkb.cloud.service.impl;

import com.xkb.cloud.domain.User;
import com.xkb.cloud.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by macro on 2019/8/29.
 */
@Service
public class UserServiceImpl implements UserService {

    private List<User> userList;

    @Override
    public void create(User user) {
        userList.add(user);
    }

    @Override
    public User getUser(Long id) {
        List<User> findUserList = userList.stream()
                .filter(userItem -> userItem.getId().equals(id))
                .collect(Collectors.toList());

        if (!CollectionUtils.isEmpty(findUserList)) {
            return findUserList.get(0);
        }

        return null;
    }

    @Override
    public void update(User user) {
        userList.stream()
                .filter(userItem -> userItem.getId().equals(user.getId()))
                .forEach(userItem -> {
                    userItem.setUsername(user.getUsername());
                    userItem.setPassword(user.getPassword());
                });
    }

    @Override
    public void delete(Long id) {
        User user = getUser(id);
        if (user != null) {
            userList.remove(user);
        }
    }

    @Override
    public User getByUsername(String username) {
        List<User> findUserList = userList.stream()
                .filter(userItem -> userItem.getUsername().equals(username))
                .collect(Collectors.toList());

        if (!CollectionUtils.isEmpty(findUserList)) {
            return findUserList.get(0);
        }

        return null;
    }

    @Override
    public List<User> getUserByIds(List<Long> ids) {
        return userList.stream()
                .filter(userItem -> ids.contains(userItem.getId()))
                .collect(Collectors.toList());
    }

    /*
    @PostConstruct 该注解被用来修饰一个非静态的 void（）方法。
    被 @PostConstruct 修饰的方法会在服务器加载 Servlet 的时候运行，并且只会被服务器执行一次。
    PostConstruct在构造函数之后执行，init（）方法之前执行。
     */
    @PostConstruct
    public void initData() {
        userList = new ArrayList<>();
        userList.add(new User(1L, "Alice", "123456"));
        userList.add(new User(2L, "Bob", "123456"));
        userList.add(new User(3L, "Cindy", "123456"));
        userList.add(new User(4L, "Dell", "123456"));
    }
}
