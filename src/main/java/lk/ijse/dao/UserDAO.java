package lk.ijse.dao;

import lk.ijse.entity.User;

public interface UserDAO extends CrudDAO<User,Long>{

    User findByUsername(String text);
}
