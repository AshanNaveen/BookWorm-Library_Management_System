package lk.ijse.dao.custom;

import lk.ijse.dao.SuperDAO;

import java.util.List;

public interface CrudDAO<T, ID> extends SuperDAO {

    ID save(T object);

    void update(T object);

    T get(ID id);

    void delete(T object);

    List<T> getAll();
}
