package lk.ijse.dao;

import jakarta.persistence.NoResultException;
import lk.ijse.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class UserDAOImpl implements UserDAO{

    private Session session;
    @Override
    public Long save(User object) {
        return (Long) session.save(object);
    }

    @Override
    public void update(User object) {
        session.update(object);
    }

    @Override
    public User get(Long id) {
        return session.get(User.class, id);
    }

    @Override
    public void delete(User object) {
        session.delete(object);
    }

    @Override
    public List<User> getAll() {
        String jpql = "SELECT U FROM User U";
        Query<User> query = session.createQuery(jpql, User.class);
        List<User> list = query.list();
        return list;
    }

    @Override
    public void setSession(Session session) {
        this.session=session;
    }

    @Override
    public User findByUsername(String text){
        try {
            String jpql="SELECT U FROM User U WHERE U.email=:username";
            Query<User> query = session.createQuery(jpql, User.class);
            query.setParameter("username",text);
            User user = query.getSingleResult();
            return user;
        }catch (NoResultException e){
            return null;
        }
    }
}
