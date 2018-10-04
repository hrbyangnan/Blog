package dao;

import entity.User;

public interface UserDao{
    public boolean login(String name, String UserPasswd);
    public boolean register(User user);
    public boolean delete(int id);

}