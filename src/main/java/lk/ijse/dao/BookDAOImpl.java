package lk.ijse.dao;

import lk.ijse.entity.Book;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class BookDAOImpl implements BooksDAO {

    private Session session;

    @Override
    public Long save(Book object) {
        return (Long) session.save(object);
    }

    @Override
    public void update(Book object) {
        session.persist(object);
    }

    @Override
    public Book get(Long id) {
        return session.get(Book.class, id);
    }

    @Override
    public void delete(Book object) {
        session.delete(object);
    }

    @Override
    public List<Book> getAll() {
        String jpql="SELECT B FROM Book B";
        Query<Book> query = session.createQuery(jpql, Book.class);
        List<Book> list = query.list();
        return list;
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }
}
