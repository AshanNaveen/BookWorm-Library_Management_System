package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.BorrowDetailsDAO;
import lk.ijse.entity.Book;
import lk.ijse.entity.BorrowDetails;
import lk.ijse.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class BorrowDetailsDAOImpl implements BorrowDetailsDAO {

    private Session session;
    @Override
    public Long save(BorrowDetails object) {
        return (Long) session.save(object);
    }

    @Override
    public BorrowDetails getNotReurnedBookByUserAndBook(User user, Book book) {
        String jpql="FROM BorrowDetails B WHERE B.isReturned=false AND B.user=:user AND B.book=:book";
        Query<BorrowDetails> query = session.createQuery(jpql, BorrowDetails.class);
        query.setParameter("user",user);
        query.setParameter("book",book);
        BorrowDetails singleResult = query.getSingleResult();
        return singleResult;
    }

    @Override
    public void update(BorrowDetails borrowDetails) {
        session.persist(borrowDetails);
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

}
