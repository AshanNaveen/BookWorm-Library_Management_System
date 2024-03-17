package lk.ijse.dao.custom;

import lk.ijse.entity.User;

public interface UserDAO extends CrudDAO<User,Long>{

    User findByUsername(String text);
}
