package ru.job4j.models;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface Store {
    public void add(User user);

    public void update(Integer index,String name);

    public void delete(Integer index);

    public List<User> findAll();

    public User findById(Integer index);
}
