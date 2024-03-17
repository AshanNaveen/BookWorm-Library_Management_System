package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.BorrowBookBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.*;
import lk.ijse.dao.custom.impl.BookDAOImpl;
import lk.ijse.dao.custom.impl.BorrowDetailsDAOImpl;
import lk.ijse.dao.custom.impl.UserDAOImpl;
import lk.ijse.dto.BorrowBookDTO;
import lk.ijse.entity.Book;
import lk.ijse.entity.BorrowDetails;
import lk.ijse.entity.User;
import lk.ijse.util.DateTimeUtil;
import lk.ijse.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class BorrowBookBOImpl implements BorrowBookBO {

    BorrowDetailsDAO borrowDetailsDAO = (BorrowDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BorrowBook);
    BooksDAO booksDAO = (BooksDAO) DAOFactory.daoFactory.getDAO(DAOFactory.DAOTypes.Book);

    UserDAO userDAO = (UserDAO) DAOFactory.daoFactory.getDAO(DAOFactory.DAOTypes.User);

    @Override
    public void borrowBook(BorrowBookDTO dto) {
        Session session = SessionFactoryConfig.getInstance().getSession();

        Transaction transaction = session.beginTransaction();

        booksDAO.setSession(session);
        Book book = booksDAO.get(dto.getBookId());
        book.setAvailable(false);


        userDAO.setSession(session);
        User user = userDAO.get(dto.getUserId());


        BorrowDetails borrowDetails = new BorrowDetails();

        borrowDetails.setBook(book);
        borrowDetails.setUser(user);
        borrowDetails.setDueTimestamp(DateTimeUtil.getDueTimestamp(dto.getBorrowedTimestamp()));
        borrowDetails.setReturned(false);

        book.getBorrowDetails().add(borrowDetails);
        user.getBorrowDetails().add(borrowDetails);

        booksDAO.save(book);
        userDAO.save(user);


        borrowDetailsDAO.setSession(session);
        borrowDetailsDAO.save(borrowDetails);
        transaction.commit();


        session.close();
    }

    @Override
    public boolean updateReturnedBook(Long userId, Long bookId) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        try {
            Transaction transaction = session.beginTransaction();
            borrowDetailsDAO.setSession(session);
            userDAO.setSession(session);
            booksDAO.setSession(session);
            Book book = booksDAO.get(bookId);
            User user = userDAO.get(userId);
            BorrowDetails borrowDetails = borrowDetailsDAO.getNotReurnedBookByUserAndBook(user,book);
            borrowDetails.setReturned(true);
            book.setAvailable(true);
            borrowDetailsDAO.update(borrowDetails);
            booksDAO.update(book);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            session.close();
            return false;
        }
    }
}
