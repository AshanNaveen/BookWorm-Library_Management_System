package lk.ijse.dao;

import java.util.List;

public interface CrudDAO<T, ID> extends SuperDAO{

    ID save(T object);

    void update(T object);

    T get(ID id);

    void delete(T object);

    List<T> getAll();
}
