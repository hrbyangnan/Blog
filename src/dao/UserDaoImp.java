package dao;

import entity.User;

public class UserDaoImp implements UserDao {
    @Override
    public boolean login(String name, String UserPasswd) {
        return false;
    }

    @Override
    public boolean register(User user) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
