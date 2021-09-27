package com.learn.rest.webservices.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<>();
    private static int usersCount = 3;

    static {
        users.add(new User(1, "Himanshu", new Date()));
        users.add(new User(2, "Hemant", new Date()));
        users.add(new User(3, "Rahul", new Date()));
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(++usersCount);
        }
        users.add(user);
        return user;
    }

    public User findOne(int id) {
        Optional<User> user = users.stream().filter(u1 -> u1.getId() == id).findFirst();
        return user.isPresent() ? user.get() : null;
    }

    public User deleteById(int id) {
        Iterator<User> iterator = users.iterator();
        while(iterator.hasNext()) {
            User user = iterator.next();
            if(user.getId() == id) {
                iterator.remove();
                return user;
            }
        }
        return null;
    }

    public int getUsersCount() {
        return usersCount;
    }
}
